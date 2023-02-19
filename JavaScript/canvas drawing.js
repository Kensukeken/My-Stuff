<!DOCTYPE html>
<html>
<head>
  <title>Canvas Drawing</title>
</head>
<body>
  <h1>Canvas Drawing</h1>
  <canvas id="canvas" width="500" height="500"></canvas>
  <script>
    const canvas = document.getElementById("canvas");
    const context = canvas.getContext("2d");

    let isDrawing = false;
    let lastX = 0;
    let lastY = 0;

    function startDrawing(event) {
      isDrawing = true;
      lastX = event.clientX - canvas.offsetLeft;
      lastY = event.clientY - canvas.offsetTop;
    }

    function stopDrawing() {
      isDrawing = false;
    }

    function draw(event) {
      if (!isDrawing) return;

      const x = event.clientX - canvas.offsetLeft;
      const y = event.clientY - canvas.offsetTop;

      context.beginPath();
      context.moveTo(lastX, lastY);
      context.lineTo(x, y);
      context.stroke();

      lastX = x;
      lastY = y;
    }

    canvas.addEventListener("mousedown", startDrawing);
    canvas.addEventListener("mouseup", stopDrawing);
    canvas.addEventListener("mousemove", draw);
  </script>
</body>
</html>
