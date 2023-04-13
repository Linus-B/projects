
window.addEventListener( "DOMContentLoaded", pageLoadedMain);
// Will store the json from the api request
var info;

function pageLoadedMain() {
    // Load in the information
    getPictureOnline();
    // Add listeners for page
    addButtonListeners();
    
}

function addButtonListeners(){
    const butt1 = document.getElementById("pictureButton");
    butt1.addEventListener("click", changePic);

    const butt2 = document.getElementById("DescriptionButton");
    butt2.addEventListener("click", changeDescription);
}

function changePic(){
    const imgEl = document.getElementById("space-pic");
    imgEl.src = info["hdurl"];
}

function changeDescription(){
    const desEl = document.getElementById("description");
    // Lets you toggle between on and off. 
    // I could've also toggled class, and made one class visible. I didn't though. 
    if (this.innerHTML == "Get Description"){
        desEl.innerHTML = info["explanation"];
        this.innerHTML = "Hide description";
    } else {
        this.innerHTML = "Get Description";
        desEl.innerHTML = "";
    }
}

// Loads the API request
function responseReceived(xhr){
    if (xhr.status === 200) {
        console.log(xhr.response);
        info = xhr.response;
    } else {
        console.log("Status error");
    }
}

function getPictureOnline(){
    // url of the request
    const url = "https://go-apod.herokuapp.com/apod";

    // On load, do that function
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function () {
        responseReceived(xhr)
    });

    xhr.responseType = "json";
    xhr.open("GET", url);
    xhr.send();
}

