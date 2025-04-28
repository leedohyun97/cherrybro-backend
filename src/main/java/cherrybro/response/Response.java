package cherrybro.response;

import lombok.Data;

@Data
public class Response<T> {
	
    private int status;
    private String message;
    private T data;
    
    public Response() {
        this.status = 0;
        this.message = "";
    }
}
