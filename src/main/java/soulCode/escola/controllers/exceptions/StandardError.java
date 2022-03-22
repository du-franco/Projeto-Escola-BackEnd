package soulCode.escola.controllers.exceptions;

public class StandardError {
	
	private String data;
	private Integer status;
	private String error;
	public StandardError(String data, Integer status, String error) {
		super();
		this.data = data;
		this.status = status;
		this.error = error;
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
}
