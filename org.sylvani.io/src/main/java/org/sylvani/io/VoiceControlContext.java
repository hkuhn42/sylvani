/**
 *
 */
package org.sylvani.io;

import org.sylvani.io.interpreter.ICommandInterpreter;
import org.sylvani.io.voice.IRecognitionService;
import org.sylvani.io.voice.ISyntheziserService;

/**
 * @author hkuhn
 *
 */
public class VoiceControlContext {

    private ICommandInterpreter commandInterpreter;
    private IRecognitionService recognitionService;
    private ISyntheziserService syntheziserService;

    public ICommandInterpreter getCommandInterpreter() {
        return commandInterpreter;
    }

    public void setCommandInterpreter(ICommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public IRecognitionService getRecognitionService() {
        return recognitionService;
    }

    public void setRecognitionService(IRecognitionService recognitionService) {
        this.recognitionService = recognitionService;
    }

    public ISyntheziserService getSyntheziserService() {
        return syntheziserService;
    }

    public void setSyntheziserService(ISyntheziserService syntheziserService) {
        this.syntheziserService = syntheziserService;
    }

}
