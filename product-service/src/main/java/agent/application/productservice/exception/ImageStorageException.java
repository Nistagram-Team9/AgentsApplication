package agent.application.productservice.exception;

public class ImageStorageException extends Exception{
	public ImageStorageException() {
		
	}
	
	
	public ImageStorageException(String message) {
		super(message);
		
	}
	
    public ImageStorageException(String message, Throwable cause) {
        super(message, cause);
    }


}

