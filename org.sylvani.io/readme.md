Core apis for the audio and voice api

AudioAPI should enable inclusion of AudioSources and AudioOutput.
- Possible sources could be 
  - Files
    - UPNP AV Stores
    - Audio samples in file system
    - Some Openhab sound system (like ringtones for info events)
    - Stored / cached audio samples (like for ok or error messages)
  - Microfones
    - Remote via webservices (like browser)
    - Computer Local Mic
    - Mic devices like alexa box
    - Smartphone Apps
    - Voice Synthezisers
    - Com Systems (Doorbell)
- Possible outputs could be 
  - Files
  - Stored Messages
  - Speech To Text or Speech to intend
  - Speakers  
    - Speakers local or remote or Thing speaker channels
    
- Comunication could go via audio events

    PlaybackCommand
    RecordCommand
    AudioAddedEvent
        
- A kind of Audio Context could be used for registering source and drain servies

  
