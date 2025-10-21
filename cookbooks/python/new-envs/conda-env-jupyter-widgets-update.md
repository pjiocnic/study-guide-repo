
## Version check

```bash
jupyter --version
```

## To update the widgets

```bash
conda install -c conda-forge \
  "jupyter_server>=2,<3" \
  "notebook>=7,<8" \
  "jupyterlab>=4,<5" \
  "ipywidgets>=8,<9" \
  "jupyterlab_widgets>=3,<4" \
  "widgetsnbextension>=4,<5"
```

- Test

```py
import ipywidgets as w
w.IntSlider()
```