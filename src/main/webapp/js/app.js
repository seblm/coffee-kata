var videoMode = "webcam";

var snapshotBtn = document.querySelector(".snapshot");
snapshotBtn.addEventListener('click', takeAPicture, false);
var snapshotResult = document.querySelector('.snapshotResult');

document.querySelector(".btn-cancel").addEventListener('click', cancelPicture, false);
document.querySelector(".btn-confirm").addEventListener('click', savePicture, false);

WebcamHandler.init();

//init();

//
//
//function init(){
//    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;
//	if (navigator.getUserMedia) {
//			navigator.getUserMedia({audio: false, video: true}, 
//									onSuccess , 
//									onError);
//	} else {
//		onError();
//	};
//}
//
//
//function onSuccess(stream) {
//	WebcamFactory.webcam = WebcamLive.init(stream);
//}
//
//function onError(error) {
//    console.log("navigator.getUserMedia error: ", error);
//    WebcamFactory.webcam = IPWebcamLive.init();
//}
	
function takeAPicture(){
	WebcamHandler.snapshot();
}


function cancelPicture(){
	document.querySelector('.snapshotResult').src = "";
}
function savePicture () {
	console.log("saving picture");
	WebcamHandler.savePicture();
//	var that = this;
//	var options = {
//		// Change this to your own url.
//		url: 'rest/photos/save'
//	};
//	
//
//	$.ajax({
//		url: options.url,
//		type: 'POST',
//		dataType: 'json',
//		data: { 'picture': document.querySelector('.snapshotResult').src },
//		complete: function(xhr, textStatus) {
//		},
//		success: function(response, textStatus, xhr) {
//			console.log('Response: ', response);
//
//			if (response.status_code === 200) {
//				console.log(response);
//				document.querySelector('.snapshotResult').src = response;
//			}
//		},
//		error: function(xhr, textStatus, errorThrown) {
//			console.log('Error: ', errorThrown);
//		}
//	});
}


