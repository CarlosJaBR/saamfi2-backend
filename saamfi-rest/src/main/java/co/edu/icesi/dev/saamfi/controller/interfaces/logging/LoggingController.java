package co.edu.icesi.dev.saamfi.controller.interfaces.logging;

import java.io.IOException;

public interface LoggingController {

	public String getLastLog() throws IOException;

	String getLogBytes(String bytes) throws IOException;
}
