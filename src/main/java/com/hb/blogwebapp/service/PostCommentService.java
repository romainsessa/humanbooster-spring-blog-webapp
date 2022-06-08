package com.hb.blogwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.blogwebapp.dto.PostCommentDTO;
import com.hb.blogwebapp.repositories.PostCommentProxy;

@Service
public class PostCommentService implements IPostCommentService {

	@Autowired
	private PostCommentProxy proxy;

	@Override
	public PostCommentDTO getCommentDTO(Integer id) {
		return proxy.getPostComment(id);
	}

	@Override
	public PostCommentDTO save(PostCommentDTO comment) {
		return proxy.save(comment);
	}

	@Override
	public void delete(Integer id) {
		proxy.delete(id);
	}

}
