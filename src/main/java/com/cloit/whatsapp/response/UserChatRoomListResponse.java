package com.cloit.whatsapp.response;

import java.util.List;

public class UserChatRoomListResponse {
	List<UserChatRoomResponse> listUserChatRoom;
	Long recordCount;

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public List<UserChatRoomResponse> getListUserChatRoom() {
		return listUserChatRoom;
	}

	public void setListUserChatRoom(List<UserChatRoomResponse> listUserChatRoom) {
		this.listUserChatRoom = listUserChatRoom;
	}
}
