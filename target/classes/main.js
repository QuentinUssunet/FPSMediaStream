let video = document.querySelector('video');
let canvas = document.querySelector('canvas');
let context = canvas.getContext('2d');

let streaming = false;
let width = 640;
let height;
let vc = null;

let socket = new WebSocket(serviceLocation);

function onOpen(event){
}

function opencvIsReady() {
	
	socket.onopen=onOpen;
    socket.addEventListener('message',function(event){
    	
    	requestAnimationFrame(processVideo);
    });
    
    let mediaStream = new MediaStream();
    
    if (streaming) return;
    navigator.mediaDevices.getUserMedia({ video: true, audio: false })
    .then(function(s) {
	        stream = s;
	        video.srcObject = s;
	        video.play();
    })
    .catch(function(err) {
        console.log("An error occured! " + err);
    });
    
    video.addEventListener("canplay", function(ev){
        if (!streaming) {
        	height = video.videoHeight / (video.videoWidth/width);
            video.setAttribute("width", width);
            video.setAttribute("height", height);
            streaming = true;
            vc = new cv.VideoCapture(video);
        }
        startVideoProcessing();        
    }, false);
    
}

let src = null;
let dst = null;

function startVideoProcessing() {
    if (!streaming) { console.warn("Please startup your webcam"); return; }
    stopVideoProcessing();
    src = new cv.Mat(height, width, cv.CV_8UC4);
    dst = new cv.Mat(height, width, cv.CV_8UC1);
    requestAnimationFrame(processVideo);    	
}

function stopVideoProcessing(){
    if (src != null && !src.isDeleted()) src.delete();
    if (dst != null && !dst.isDeleted()) dst.delete();
}

function processVideo(){
    vc.read(src);
    cv.imshow("canvas", src);    
    var canvasData = canvas.toDataURL('image/png',1);
    var decodeAstring = atob(canvasData.split(',')[1]);
    var charArray =[];
    for(var i=0; i<decodeAstring.length;i++){
        charArray.push(decodeAstring.charCodeAt(i));
    }
    socket.send( new Blob([new Uint8Array(charArray)],{
        type:'image/png'
    }));    
}


