$(document).ready(function () {
	//http://arstechnica.com/business/2012/01/hands-on-building-an-html5-photo-booth-with-chromes-new-webcam-api/	
	video = document.getElementById("live")

    navigator.webkitGetUserMedia("video",
        function(stream) {
          video.src = window.webkitURL.createObjectURL(stream)
        },
        function(err) {
          console.log("Unable to get video stream!")
        }
    )	
	
    http://www.scriptcam.com/docs.cfm
	$("#webcam").scriptcam({
		path: 'vendor/'
	}
					
	);
	
	
});
