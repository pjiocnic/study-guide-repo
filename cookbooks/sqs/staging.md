Got it—here are clean, copy-pasteable ways to push a file of messages to SQS from the command line. Each line in your file becomes one message.

# Prereqs

* `aws` CLI configured (`aws configure`)
* Your queue URL in an env var: `export QURL='https://sqs.<region>.amazonaws.com/<acct>/<queue>'`
* Input file (one message per line), e.g., `messages.txt`

---

## Option A — simplest (one message at a time)

### macOS/Linux (bash/zsh)

```bash
export QURL='https://sqs.<region>.amazonaws.com/<acct>/<queue>'
while IFS= read -r line || [[ -n "$line" ]]; do
  aws sqs send-message --queue-url "$QURL" --message-body "$line" >/dev/null
done < messages.txt
```

### Windows PowerShell

```powershell
$QURL = 'https://sqs.<region>.amazonaws.com/<acct>/<queue>'
Get-Content -Path .\messages.txt | ForEach-Object {
  aws sqs send-message --queue-url $QURL --message-body $_ | Out-Null
}
```

---

## Option B — faster (batch 10 at a time)

> SQS `SendMessageBatch` supports up to **10** messages per request.

### macOS/Linux (needs `jq`)

```bash
export QURL='https://sqs.<region>.amazonaws.com/<acct>/<queue>'
BATCH=10
buf=""
count=0

flush() {
  [ "$count" -eq 0 ] && return
  entries=$(printf "%s" "$buf" | jq -R -s '
    split("\n")[:-1]
    | to_entries
    | map({Id: ((.key+1)|tostring), MessageBody: .value})
  ')
  aws sqs send-message-batch --queue-url "$QURL" --entries "$entries" >/dev/null
  buf=""; count=0
}

while IFS= read -r line || [[ -n "$line" ]]; do
  buf+="$line"$'\n'
  count=$((count+1))
  [ "$count" -eq "$BATCH" ] && flush
done < messages.txt
flush
```

### Windows PowerShell (no extra tools)

```powershell
function Chunk($arr, $size) {
  for ($i=0; $i -lt $arr.Count; $i += $size) {
    ,$arr[$i..([Math]::Min($i+$size-1, $arr.Count-1))]
  }
}

$QURL = 'https://sqs.<region>.amazonaws.com/<acct>/<queue>'
$lines = Get-Content .\messages.txt
foreach ($chunk in (Chunk $lines 10)) {
  $entries = @()
  for ($i=0; $i -lt $chunk.Count; $i++) {
    $entries += @{ Id = "m$($i+1)"; MessageBody = $chunk[$i] }
  }
  $json = $entries | ConvertTo-Json -Compress
  aws sqs send-message-batch --queue-url $QURL --entries $json | Out-Null
}
```

---

## FIFO queues (MessageGroupId / dedup)

* If your **FIFO** queue has **content-based deduplication enabled**, you can reuse the scripts above by adding a MessageGroupId.
* If **not** enabled, you must supply a `MessageDeduplicationId` per message.

### Bash (FIFO with content-based dedup enabled)

```bash
export QURL='https://sqs.<region>.amazonaws.com/<acct>/<queue>.fifo'
export GROUP='my-group-1'
while IFS= read -r line || [[ -n "$line" ]]; do
  aws sqs send-message \
    --queue-url "$QURL" \
    --message-group-id "$GROUP" \
    --message-body "$line" >/dev/null
done < messages.txt
```

### Bash (FIFO without content-based dedup; use a hash as dedup id)

```bash
export QURL='https://sqs.<region>.amazonaws.com/<acct>/<queue>.fifo'
export GROUP='my-group-1'
while IFS= read -r line || [[ -n "$line" ]]; do
  DEDUP=$(printf '%s' "$line" | shasum | awk '{print $1}')
  aws sqs send-message \
    --queue-url "$QURL" \
    --message-group-id "$GROUP" \
    --message-deduplication-id "$DEDUP" \
    --message-body "$line" >/dev/null
done < messages.txt
```

---

## Tips

* **Limits:** message body ≤ 256 KB; batches ≤ 10 messages.
* **Throughput:** batching is much faster; you can run multiple shells in parallel over file splits for higher throughput (e.g., `split -n l/8 messages.txt part_` then run 8 instances).
* **Attributes:** add `--message-attributes` if you need typed attributes (StringValue/DataType).
* **Newlines/quotes:** the scripts read raw lines; if your lines contain quotes or JSON, they’re sent as-is.

If you want, I can also give you a tiny Java (JDK 1.8) sender you can run as a CLI for even higher throughput and resumability.
