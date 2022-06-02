package com.hb.blogwebapp.service;

import java.util.List;

import com.hb.blogwebapp.dto.TagDTO;

public interface ITagService {

	public List<TagDTO> getTagDTOs();

	public TagDTO getTagDTO(Integer id);

	public void delete(Integer id);

	public TagDTO save(TagDTO tag);

}
