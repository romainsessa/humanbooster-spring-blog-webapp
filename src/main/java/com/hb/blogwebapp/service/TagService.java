package com.hb.blogwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.blogwebapp.dto.TagDTO;
import com.hb.blogwebapp.repositories.TagProxy;

@Service
public class TagService implements ITagService {

	@Autowired
	private TagProxy tagProxy;

	@Override
	public List<TagDTO> getTagDTOs() {
		return tagProxy.getTags();
	}

	public TagDTO getTagDTO(Integer id) {
		return tagProxy.getTag(id);
	}

	@Override
	public TagDTO save(TagDTO tag) {
		return tagProxy.save(tag);
	}

	@Override
	public void delete(Integer id) {
		tagProxy.delete(id);
	}

}
