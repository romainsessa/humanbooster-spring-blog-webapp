package com.hb.blogwebapp.controllers.formwrappers;

public class PostTagFormWrapper {

	private Integer postId;
	private Integer tagId;
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
}