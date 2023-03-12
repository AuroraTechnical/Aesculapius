filename = "sample.txt"
lines = []

with open(filename, "r") as f:
    for line in f:
        line = line.strip()  # 行末の改行コードを除去
        lines.append(line)

print("読み込んだ行数:", len(lines))
print("先頭の3行:", lines[:3])
