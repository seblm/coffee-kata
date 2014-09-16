$(document).ready(function () {
	//Solution 1 webRTC
	//see http://www.html5rocks.com/en/tutorials/getusermedia/intro/
    var video = document.querySelector('video');
    var canvas = document.querySelector('canvas');
    var ctx = canvas.getContext('2d');
    var localMediaStream = null;
    document.querySelector('#shot').addEventListener('click', snapshot, false);    
	
   navigator.getMedia = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia);
   
   if(navigator.getMedia){
	    navigator.getMedia({video:true, audio:false}, onSuccess, onError);	   
   }else{
	   fallback();
   }

    
   function fallback(e) {
	   video.src = 'fallbackvideo.webm';
   }
   function onSuccess(stream) {
	   video.src = window.URL.createObjectURL(stream);
	   localMediaStream = stream;
   }
   function onError(error) {
       console.log(error);
   }   
    
    
    function snapshot() {
    	console.log("snapshot");
	    if (localMediaStream) {
	      ctx.drawImage(video, 0, 0);
	      // "image/webp" works in Chrome.
	      // Other browsers will fall back to image/png.
	      $('img').src = canvas.toDataURL('image/webp');
	    }
	 }   
    
	
      //solution 2 using scriptcam.js  
    //see http://www.scriptcam.com/docs.cfm
//	$("#webcam").scriptcam({
//		path: 'vendor/'
//	});	
	
});
