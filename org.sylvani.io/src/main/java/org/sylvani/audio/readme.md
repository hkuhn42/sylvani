This is a generic api for sound 

These only define interfaces and an abstraction layer to different audio apis
There are two reasons for not using the Java Sound API is
a) it is horrible for so many reasons 
   (among them: nobody seems to know why target is for input and source is for output and there is an audioInputStream but no AudioOutputStream)
b) it only defines constants and values for formats and codes it supports :(
c) it is not part of compact profile 2 which is the target for eclipse smarthome so any api using java sound can only be an extension
d) it does not offer a easy to use way to choose the outout or input source / targets
e) java itself offers a second sound related api with the java media framework