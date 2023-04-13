
function drawOriginLines(canvas){
    ctx = canvas.getContext("2d");
    ctx.beginPath();
    ctx.moveTo(0, canvas.height / 2)
    ctx.lineTo(canvas.width, canvas.height / 2)
    ctx.closePath();
    ctx.lineWidth = 2;
    ctx.strokeStyle = "black";
    ctx.stroke();
    
    ctx.beginPath();
    ctx.moveTo(canvas.width / 2, 0)
    ctx.lineTo(canvas.width / 2, canvas.height)
    ctx.closePath();
    ctx.lineWidth = 2;
    ctx.strokeStyle = "black";
    ctx.stroke();
}

function drawRandomLine(canvas, right, up, dim){
   const factor = 5;
    ctx = canvas.getContext("2d");
    randomX = right - Math.floor(Math.random() * 100);
    randomY = up - Math.floor(Math.random() * 100);

    ctx.beginPath();
    ctx.moveTo(canvas.drawX, canvas.drawY);
    randomX = randomX > 0 ? factor: -factor;
    lineColor = randomX > 0 ? "green":"red";
    randomY = randomY > 0 ? factor: -factor;

    canvas.drawX += randomX;
    if (dim > 1){
        canvas.drawY += randomY
    }
    ctx.lineTo(canvas.drawX, canvas.drawY)
    ctx.closePath();

    ctx.lineWidth = 5;
    ctx.strokeStyle = lineColor;
    ctx.stroke();
    console.log("This is working");


}



function randomWalk(canvas, right, up, dim){
    ctx = canvas.getContext("2d");
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawOriginLines(canvas);
    canvas.drawX = canvas.width / 2
    canvas.drawY = canvas.height / 2
    var right = parseFloat(document.getElementById("rightChance").value);
    canvas.interval = setInterval(drawRandomLine, 1, canvas, right, up, dim)
}

function resetWalk(){
    var dim = document.getElementById("dimen").value;
    var right = document.getElementById("rightChance").value;
    var up = document.getElementById("upChance").value;
    var canvas = document.getElementById("myCanvas")
    clearInterval(canvas.interval);
    if (right <= 100 && right >= 0 && up <= 100 && up >= 0){
        document.getElementById("leftChance").innerHTML = 100 - right
        document.getElementById("downChance").innerHTML = 100 - up
        if (dim == 1 || dim == 2){
            randomWalk(canvas, right, up, dim);
        }
    }

}

function init(){
    var canvas = document.getElementById("myCanvas");
    var ctx = canvas.getContext("2d");
    ctx.fillStyle = "#CCCCCC";
    ctx.fillRect(0, 0, 1250, 1000);
    canvas.interval = null;
    canvas.drawX = canvas.width / 2
    canvas.drawY = canvas.height / 2
} 

(function () {
    init();
})();