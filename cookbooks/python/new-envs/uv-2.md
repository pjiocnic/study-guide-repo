https://www.youtube.com/watch?v=QxLXhE1fxc4

```bash
mkdir new-project
cd new-project
curl -LsSf https://astral.sh/uv/install.sh | sh
source $HOME/.local/bin/env
uv init
uv add numpy
uv add jupyterlab
uv run jupyter lab

How to run 3rdparty UV project

git clone https://github.com/ShawhinT/AI-Builders-Bootcamp-6.git
cd AI-Builders-Bootcamp-6/session-1
uv sync
uv run jupyter lab
```