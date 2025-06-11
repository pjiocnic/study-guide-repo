import re
from pathlib import Path


last_tf_batch_files = [
    "./1.mdx",
    "./2.mdx",
    "./3.mdx",
    "./4.mdx",
    "./5.mdx",
    "./6.mdx",
    "./7.mdx"
]


# def clean_mdx_file(input_path):

#     content = Path(input_path).read_text(encoding="utf-8")

#     # Remove JSX tags like <Quiz />, <Callout type="info">...</Callout>
#     cleaned = re.sub(r"<[^>]+>", "", content)

#     # Normalize multiple blank lines
#     cleaned = re.sub(r"\n{2,}", "\n\n", cleaned)

#     return cleaned.strip()


def clean_mdx(content):

    # Remove JSX tags like <Quiz />, <Callout type="info">...</Callout>
    cleaned = re.sub(r"<[^>]+>", "", content)

    # Normalize multiple blank lines
    cleaned = re.sub(r"\n{2,}", "\n\n", cleaned)

    return cleaned.strip()


last_tf_converted_paths = []
for file_path in last_tf_batch_files:
    path = Path(file_path)
    text = path.read_text(encoding="utf-8")
    cleaned = clean_mdx(text)
    output_path = path.with_suffix(".md")
    output_path.write_text(cleaned, encoding="utf-8")
    last_tf_converted_paths.append(output_path)

# [file.name for file in last_tf_converted_paths]
