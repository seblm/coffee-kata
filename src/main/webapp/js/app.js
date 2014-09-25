navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

var videoMode = "webcam";

var constraints = {audio: false, video: true};
var video = document.querySelector("video");
var imgField = document.querySelector(".ipWebcamResult");
var canvas = document.querySelector("canvas");
var ctx = canvas.getContext("2d");
var snapshotBtn = document.querySelector(".snapshot");

video.addEventListener('click', snapshot, false);
snapshotBtn.addEventListener('click', snapshot, false);
document.querySelector(".btn-cancel").addEventListener('click', cancelPicture, false);
document.querySelector(".btn-confirm").addEventListener('click', savePicture, false);


if (navigator.getUserMedia) {
    navigator.getUserMedia(constraints, successCallback, errorCallback);
} else {
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
    var urlVideo = document.querySelector("#urlVideo");
    var urlPhoto = document.querySelector("#urlPhoto");

    document.querySelector("#url").classList.remove("hidden");
    document.querySelector("#url").display = "inline-block;"
    var startButton = document.querySelector(".startIPWebcam");

    startButton.addEventListener('click', function () {
        //snapshotBtn.classList.remove("hidden");
        snapshotBtn.classList.add("visible");
        imgField.src = urlVideo.value;
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
    } else {
        ctx.drawImage(imgField, 0, 0);
        //TODO find a solution : security error with canvas.toDataURL('"image/png"');
        //see http://stackoverflow.com/questions/20424279/canvas-todataurl-securityerror

        if (urlPhoto.value) {
            document.querySelector('.snapshotResult').src = urlPhoto.value;
            //document.querySelector('.snapshotResult').src = "http://192.168.0.13:8080/photo.jpg";
        } else {//TODO temp ! just for test working--> FIXME
            document.querySelector('.snapshotResult').src = "http://camera1.mairie-brest.fr/axis-cgi/jpg/image.cgi";
        }


    }
}

function cancelPicture(){
	document.querySelector('.snapshotResult').src = "";
}
function savePicture () {
	var that = this;
	var options = {
		// Change this to your own url.
		url: 'rest/photos/save'
	};
	

	$.ajax({
		url: options.url,
		type: 'POST',
		dataType: 'json',
		data: { 'picture': document.querySelector('.snapshotResult').src },
		complete: function(xhr, textStatus) {
		},
		success: function(response, textStatus, xhr) {
			console.log('Response: ', response);

			if (response.status_code === 200) {
				console.log(response);
				document.querySelector('.snapshotResult').src = response;
			}
		},
		error: function(xhr, textStatus, errorThrown) {
			console.log('Error: ', errorThrown);
		}
	});
}


