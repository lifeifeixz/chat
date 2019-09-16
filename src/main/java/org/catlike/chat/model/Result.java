package org.catlike.chat.model;

public class Result {
	private String msg;
	
	private Result(){
		
	}
	
	public static Result result=new Result();

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static Result getResult() {
		return result;
	}

	public static void setResult(Result result) {
		Result.result = result;
	}
	
	
}
