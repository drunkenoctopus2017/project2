package com.revature.model;

public class UserBoardDTO {
	private int userId;
	private int boardId;
	
	public UserBoardDTO() {}
	
	public UserBoardDTO(int userId, int boardId) {
		this.userId = userId;
		this.boardId = boardId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	@Override
	public String toString() {
		return "UserBoardDTO [userId=" + userId + ", boardId=" + boardId + "]";
	}

}
