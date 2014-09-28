var WebcamHandler = {
	webcam : null,
    
	init : function (type) {
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;
		if (navigator.getUserMedia) {
				navigator.getUserMedia({audio: false, video: true}, 
						this.onSuccess , 
						this.onError);
		} else {
			this.onError();
		};
		return this;
		
    },
    
    onSuccess : function(stream) {
    	WebcamHandler.webcam = WebcamLive.init(stream);
    }, 
    onError : function(error) {
        console.log("navigator.getUserMedia error: ", error);
        WebcamHandler.webcam = IPWebcamLive.init();
    },
    
    snapshot : function(){
    	this.webcam.snapshot();
    },
    
    savePicture : function(){
	    console.log(this.webcam.restUrlForSave);
	    console.log(this.webcam.dataToSend);

        postData(this.webcam.restUrlForSave, data)
        .done(function(this.webcam.dataToSend){
                document.querySelector('#savedPicture').value = response;
        })
        .fail(function(error){
            console.log(error);
         });


/*
		$.ajax({
			url: this.webcam.restUrlForSave,
			type: 'POST',
			data: this.webcam.dataToSend,
			complete: function(xhr, textStatus) {
			},
			success: function(response, textStatus, xhr) {
				console.log('Response: ', response);

				if (response.status_code === 200) {
					console.log(response);
					document.querySelector('#savedPicture').value = response;
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				console.log('Error: ', errorThrown);
			}
		});
*/

    }
}

 