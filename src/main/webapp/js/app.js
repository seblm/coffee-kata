var videoMode = "webcam";
var command = null;

var snapshotBtn = document.querySelector(".snapshot");
snapshotBtn.addEventListener('click', takeAPicture, false);
var snapshotResult = document.querySelector('.snapshotResult');

document.querySelector(".btn-cancel").addEventListener('click', cancelPicture, false);
document.querySelector(".btn-confirm").addEventListener('click', savePicture, false);

//the user has chosen a command
var cmdButtons = document.querySelectorAll(".btn-command");
[].forEach.call(cmdButtons, function(btn) {
    btn.addEventListener('click', function(){
        chooseCmd(btn.classList.item(2));
    }, false);
});


//when user validate the command
document.querySelector(".order-cmd").addEventListener('click', orderCmd, false);

WebcamHandler.init();

function takeAPicture(){
	WebcamHandler.snapshot();
}


function cancelPicture(){
	document.querySelector('.snapshotResult').src = "";
}
function savePicture () {
	console.log("saving picture");
	WebcamHandler.savePicture();
}

function chooseCmd(cmd){
    command = cmd;
    document.querySelector(".order-cmd").classList.remove("hidden");
    document.querySelector(".photobooth").classList.add("hidden");
}

function orderCmd(){
    url = "/rest/photos/check";
    data = {"colorimetry":"COLOR","format":"PORTRAIT", "money":0};


    postData(url, data)
    .done(function(result){
        document.querySelector(".order-cmd").classList.add("hidden");
        document.querySelector(".photobooth").classList.remove("hidden");
    })
    .fail(function(error){
        console.log(error);
        //TODO temp fix rest call
        document.querySelector(".order-cmd").classList.add("hidden");
        document.querySelector(".photobooth").classList.remove("hidden");

     });

}


function postData(url, data){
		return $.ajax({
			url: url,
			type: 'POST',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			data: data,
		});
}
function getData(url){
		return $.ajax({
			url: url,
			type: 'GET',
			dataType: 'json',
			contentType: "application/json; charset=utf-8"
		});

}



