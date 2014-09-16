$(document).ready(function () {
	//Solution 1 webRTC
	//see http://www.html5rocks.com/en/tutorials/getusermedia/intro/	
	video = document.getElementById("live")	
   navigator.getMedia = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia);

    navigator.getMedia(
        // constraints
        {video:true, audio:false},

        // success callback
        function (mediaStream) {
            var video = document.getElementsByTagName('video')[0];
            video.src = window.URL.createObjectURL(mediaStream);
            video.play();
        },   
        //handle error
        function (error) {
            console.log(error);
        })  	
	
      //solution 2 using scriptcam.js  
    //see http://www.scriptcam.com/docs.cfm
	$("#webcam").scriptcam({
		path: 'vendor/'
	}
					
	);
	
	
});
