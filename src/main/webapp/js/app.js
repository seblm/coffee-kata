navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

var constraints = {audio: false, video: true};
var video = document.querySelector("video");
var canvas = document.querySelector("canvas");
var ctx = canvas.getContext("2d");

function successCallback(stream) {
    window.stream = stream; // stream available to console
    if (window.URL) {
        video.src = window.URL.createObjectURL(stream);
    } else {
        video.src = stream;
    }
}

function errorCallback(error) {
    console.log("navigator.getUserMedia error: ", error);
}

function snapshot() {
    if (window.stream) {
        ctx.drawImage(video, 0, 0, 640, 480, 0, 0, 300, 150);
        // "image/webp" works in Chrome.
        // Other browsers will fall back to image/png.
        document.querySelector('img').src = canvas.toDataURL('image/webp');
    }
}

video.addEventListener('click', snapshot, false);

navigator.getUserMedia(constraints, successCallback, errorCallback);
