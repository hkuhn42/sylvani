   function sendToServer(blob) {
	    
	    var request = new XMLHttpRequest();
	    
	    $.ajax({
	        type: 'POST',
	        url: ' /voice/relay/invokeVoiceCommand',
	        data: blob,
	        contentType: 'application/octet-stream',
        	processData: false
        }).done(function(data) {
               console.log(data);
        });
  }
   
   function downsampleBuffer(buffer, rate) {
	    if (rate == sampleRate) {
	        return buffer;
	    }
	    if (rate > sampleRate) {
	        throw "downsampling rate show be smaller than original sample rate";
	    }
	    var sampleRateRatio = sampleRate / rate;
	    var newLength = Math.round(buffer.length / sampleRateRatio);
	    var result = new Float32Array(newLength);
	    var offsetResult = 0;
	    var offsetBuffer = 0;
	    while (offsetResult < result.length) {
	        var nextOffsetBuffer = Math.round((offsetResult + 1) * sampleRateRatio);
	        var accum = 0, count = 0;
	        for (var i = offsetBuffer; i < nextOffsetBuffer && i < buffer.length; i++) {
	            accum += buffer[i];
	            count++;
	        }
	        result[offsetResult] = accum / count;
	        offsetResult++;
	        offsetBuffer = nextOffsetBuffer;
	    }
	    return result;
	}