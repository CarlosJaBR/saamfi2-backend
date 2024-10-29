package co.edu.icesi.dev.saamfi.controller.implementation.logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

import org.hibernate.cfg.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.logging.LoggingController;

@RestController
@RequestMapping("/logs/")
@CrossOrigin(origins = "*")
public class LoggingControllerImpl implements LoggingController {

	@Override
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('Query-server-logs')")
	public String getLastLog() throws IOException {
		String catalinalog = Environment.getProperties().getProperty("catalina.home") + File.separator + "logs"
				+ File.separator + "catalina.out";
		Process process = new ProcessBuilder("tail", "-c 20000", catalinalog).start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		String ret = "";
		while ((line = br.readLine()) != null) {
			ret += line + "\n";
		}
		return ret;
	}

	@Override
	@GetMapping("/{bytes}")
	@PreAuthorize("hasAnyRole('Query-server-logs')")
	public String getLogBytes(@PathVariable String bytes) throws IOException {
		String catalinalog = Environment.getProperties().getProperty("catalina.home") + File.separator + "logs"
				+ File.separator + "catalina.out";
		Process process = new ProcessBuilder("tail", "-n " + bytes, catalinalog).start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		String ret = InetAddress.getLocalHost().getHostName() + " "
				+ Environment.getProperties().getProperty("catalina.home") + "\n";
		while ((line = br.readLine()) != null) {
			ret += line + "\n";
		}
		return ret;
	}

}
