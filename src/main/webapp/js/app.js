navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

var videoMode = "webcam";

var constraints = {audio: false, video: true};
var video = document.querySelector("video");
var imgField = document.querySelector("#resultForIPWebCam");
var canvas = document.querySelector("canvas");
var ctx = canvas.getContext("2d");

video.addEventListener('click', snapshot, false);		
document.querySelector("#snapshot").addEventListener('click', snapshot, false);


if(navigator.getUserMedia){
    navigator.getUserMedia(constraints, successCallback, errorCallback);	   
}else{
	videoMode = "ip";
   fallback();
}

function successCallback(stream) {
    window.stream = stream; // stream available to console
    if (window.URL) {
        video.src = window.URL.createObjectURL(stream);
    } else {
        video.src = stream;
    }
}

function fallback(e) {
	var urlField = document.querySelector("#urlTxt");
	
	document.querySelector("#url").classList.remove("hidden");
	document.querySelector("#url").display="inline-block;"
	var startButton = document.querySelector("#startIPWebcam");

	startButton.addEventListener('click', function(){
		
		imgField.src = urlField.value;
	}, false);
	video.classList.add("hidden");	
}



function errorCallback(error) {
    console.log("navigator.getUserMedia error: ", error);
    fallback();
}

function snapshot() {
    if (window.stream) {
        ctx.drawImage(video, 0, 0, 640, 480, 0, 0, 300, 150);
        // "image/webp" works in Chrome.
        // Other browsers will fall back to image/png.
        document.querySelector('img').src = canvas.toDataURL('image/webp');
    }else{
    	ctx.drawImage(imgField, 0, 0);
    	//TODO find a solution : security error with canvas.toDataURL('"image/png"');
    	//see http://stackoverflow.com/questions/20424279/canvas-todataurl-securityerror

    	if(urlField.value != ""){
    		document.querySelector('.snapshotResult').src = "http://192.168.0.13:8080/photo.jpg";
    	}else{//TODO temp ! just for test working--> FIXME
    		document.querySelector('.snapshotResult').src = "http://camera1.mairie-brest.fr/axis-cgi/jpg/image.cgi";	
    	}
    	
    	
    }
}

