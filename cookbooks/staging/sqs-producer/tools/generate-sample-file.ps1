param(
  [int]$Lines = 1000000,
  [string]$OutFile = ".\sample-data\messages.txt",
  [int]$Parts = 1
)

New-Item -ItemType Directory -Path ".\sample-data" -Force | Out-Null

if ($Parts -le 1) {
  "Generating $Lines lines to $OutFile"
  $sw = [System.IO.StreamWriter]::new($OutFile, $false)
  for ($i=1; $i -le $Lines; $i++) {
    $sw.WriteLine("{""id"":$i,""payload"":""message-$i""}")
  }
  $sw.Close()
} else {
  $per = [Math]::Ceiling($Lines / $Parts)
  for ($p=0; $p -lt $Parts; $p++) {
    $start = $p * $per + 1
    $end = [Math]::Min(($p+1) * $per, $Lines)
    $file = ".\sample-data\messages-part-$('{0:d3}' -f $p).txt"
    "Generating lines $start..$end to $file"
    $sw = [System.IO.StreamWriter]::new($file, $false)
    for ($i=$start; $i -le $end; $i++) {
      $sw.WriteLine("{""id"":$i,""payload"":""message-$i""}")
    }
    $sw.Close()
  }
}
