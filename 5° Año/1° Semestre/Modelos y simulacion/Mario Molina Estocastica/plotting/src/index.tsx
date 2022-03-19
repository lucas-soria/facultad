import functionPlot from "function-plot";

let contentsBounds = document.body.getBoundingClientRect();
let width = 500;
let height = 220;
let ratio = contentsBounds.width / width;
width *= ratio;
height *= ratio;

functionPlot({
  target: "#root",
  width,
  height,
  yAxis: { domain: [-1, 9] },
  grid: true,
  
  data: [
    {
      fn: "x^2",
      derivative: {
        fn: "2 * x",
        updateOnMouseMove: true
      }
    },
    {
      fn: "sin(x)",
      nSamples: 200
    },
    {
      fnType: 'points',
      points: [[1,1], [2,2]]
    }
  ]
});
