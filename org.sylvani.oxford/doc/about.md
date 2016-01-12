Sylvani is a voice command interface for openhab2 


The current prototype supports turning lights on and off
Voice Recognition and synthesizing is done via Microsoft Oxford REST API

The prototype defines a multi layer architecture with services for 
a) audio capture and playback 
   currently supporting a dashboard tile using web audio with recorder.js and a java commandline / swing client
b) voice recognition and synthesizing and
   (currently supporting Microsoft Oxford API and Sphinx4 but can also support Amazon Alexa, Google Voice API ) 
c) textual command interpretation and response [can also be directly accessed in case of smart clients with local text to voice]
   (current implementation does only direct text interpretation but i am  playing around or planing to to so with lucene, open nlp and intent recognition from    oxford and    alexa ) 


current flow:

start audio capture (button)
send audio to server (post)
send audio to recognition service (rest or local)
send textual result to command interpreter
create matching openhab2 events
send textual response to synthesizing service (or load buffered default response)
send audio to client
playback audio on client




