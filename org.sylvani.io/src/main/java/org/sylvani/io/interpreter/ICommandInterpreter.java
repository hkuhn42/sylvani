/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.io.interpreter;

/**
 * Definition of an textual command interpreter implementations should handle either unstructured (like direct text to speech) or 
 * structured (like intents) issued textual commands
 *
 * @author Harald Kuhn (hkuhn42) initial api
 */
public interface ICommandInterpreter {

	/**
	 * Handle a textual command (like turn the head light on) and respond with a textual response 
	 * 
	 * @param command the command to handle
	 * @return a textual response
	 */
	public String handleCommand(String command);

}
