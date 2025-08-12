import argparse, os

def write_file(path, start, end):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w", encoding="utf-8") as f:
        for i in range(start, end+1):
            f.write(f'{{"id":{i},"payload":"message-{i}"}}' + "\n")

if __name__ == "__main__":
    ap = argparse.ArgumentParser()
    ap.add_argument("--lines", type=int, default=1000000)
    ap.add_argument("--out", type=str, default="sample-data/messages.txt")
    ap.add_argument("--parts", type=int, default=1)
    args = ap.parse_args()

    if args.parts <= 1:
        write_file(args.out, 1, args.lines)
    else:
        per = (args.lines + args.parts - 1) // args.parts
        for p in range(args.parts):
            start = p * per + 1
            end = min((p+1) * per, args.lines)
            path = f"sample-data/messages-part-{p:03d}.txt"
            write_file(path, start, end)
