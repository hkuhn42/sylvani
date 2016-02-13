An experimental implementation of a voice command api for eclipse smarthome / openhab2 which defines:
- an audio api
- interfaces and sample implementations for voice to text and text to voice 
- an interface and a sample implementaiton for a text command interpreter
- a webservice api for utilizing these to issue voice commands (speech to text to action to text to speech)
- clients for web and commandline to use this service


Current parts:
- org.sylvani.io 	 
  Core API for audio, voice2text, text2voice and text commands   
  Webservice API for audio & text based commands
    
- org.openhab.binding.sylvani.audio   
  Implementation of AudioSource and AudioOutput Interfaces for local audio system based on java sound api

-	org.sylvani.client  
  Command line client for Websservice API

- org.sylvani.hal 	
  Sample implementation of CommandInterpreter (currently static mapped) 
	Possible implementations could either build an fulltext index  or a nlp model from the things and channels in the bus to optimize output

-	org.sylvani.mary  
  Sample implementation of Recognizer based on mary TTS

-	org.sylvani.openhab.ui   
  Openhab UI Plugin based on recoderJS as client of webservie

- org.sylvani.oxford 	
  Sample implementaitons of recognizer and syntheizer based on microsoft oxford

-	org.sylvani.sphinx   
  Sample implementation of recognizer based on sphinx
