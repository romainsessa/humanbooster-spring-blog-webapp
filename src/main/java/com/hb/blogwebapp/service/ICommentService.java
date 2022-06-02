package com.hb.blogwebapp.service;

import java.util.List;

import com.hb.blogwebapp.dto.PostCommentDTO;

public interface ICommentService {

	public PostCommentDTO getCommentDTO(Integer id);	

	public List<PostCommentDTO> getCommentDTOs();

	public PostCommentDTO save(PostCommentDTO comment);

	public void delete(Integer id);

}
