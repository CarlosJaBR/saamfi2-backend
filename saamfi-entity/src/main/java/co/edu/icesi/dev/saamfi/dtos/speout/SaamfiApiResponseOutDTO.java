package co.edu.icesi.dev.saamfi.dtos.speout;

/**
 * Class to generate API responses
 *
 * @author Luis Miguel Paz
 *
 */
public class SaamfiApiResponseOutDTO {
	private String timestamp;
	private String message;

	public SaamfiApiResponseOutDTO(String timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
