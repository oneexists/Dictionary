package com.trackers.dictionary.business.facade;

public class Result extends DataTransfer {
	public enum ResultCode {
		VOCABULARY_NOT_FOUND, FLASHCARD_NOT_FOUND, OPERATION_COMPLETED, OPERATION_FAILED;		
	}
	private ResultCode resultCode;
	
	public ResultCode getResultCode() {
		return resultCode;
	}
	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

}
