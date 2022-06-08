package com.hb.blogwebapp.service;

import com.hb.blogwebapp.dto.PostCommentDTO;

public interface IPostCommentService {

	public PostCommentDTO getCommentDTO(Integer id);

	public PostCommentDTO save(PostCommentDTO comment);

	public void delete(Integer id);

}
