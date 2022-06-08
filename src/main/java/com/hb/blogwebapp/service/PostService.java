package com.hb.blogwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.blogwebapp.dto.PostDTO;
import com.hb.blogwebapp.repositories.PostProxy;

@Service
public class PostService implements IPostService {

	@Autowired
	private PostProxy proxy;
	
	@Override
	public PostDTO getPostDTO(Integer id) {
		return proxy.getPost(id);
	}

	@Override
	public List<PostDTO> getPostDTOs() {
		return proxy.getPosts();
	}

	@Override
	public PostDTO save(PostDTO post) {
		return proxy.save(post);
	}

	@Override
	public void delete(Integer id) {
		proxy.delete(id);
	}

	@Override
	public void mapPostTag(Integer postId, Integer tagId) {
		proxy.mapPostTag(postId, tagId);
	}

	@Override
	public void unmapPostTag(Integer postId, Integer tagId) {
		proxy.unmapPostTag(postId, tagId);	
	}

}
