package com.greatwqs.ssm2api.common.exception;

/**
 * 
 * 自定义异常
 * @author greatwqs
 *
 */
public class HiException extends Exception {
    
    private static final long serialVersionUID = 10001L;
    
    private int code;
    private String message;
    
    public HiException(HiStatus hiStatus) {
        super(hiStatus.getMessage());
        this.code = hiStatus.getCode();
        this.message = hiStatus.getMessage();
    }

	public HiException(HiStatus hiStatus, String message) {
		super(message);
		this.code = hiStatus.getCode();
		this.message = message;
	}
	
    public int getCode() {
        return code;
    }
    
    @Override
	public String getMessage() {
        return message;
    }
}
