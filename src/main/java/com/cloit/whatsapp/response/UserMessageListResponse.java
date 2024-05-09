package com.cloit.whatsapp.response;

import java.util.List;

public class UserMessageListResponse {
	List<UserMessageResponse> listUserMessage;
	Long recordCount;
	
	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public List<UserMessageResponse> getListUserMessage() {
		return listUserMessage;
	}

	public void setListUserMessage(List<UserMessageResponse> listUserMessage) {
		this.listUserMessage = listUserMessage;
	}
	
}
