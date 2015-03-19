package com.lob.protocol;

public class DeletedStatus extends LobObject {
	int deleted;
	public String getMessage() {
		return String.valueOf(deleted);
	}	
}
