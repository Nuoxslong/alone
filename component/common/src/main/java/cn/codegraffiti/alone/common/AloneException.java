package cn.codegraffiti.alone.common;

public class AloneException extends RuntimeException {


    public AloneException(String message) {
        super(message);
    }

    public AloneException(Throwable cause) {
        super(cause);
    }

    public AloneException(String message, Throwable cause) {
        super(message, cause);
    }


}
