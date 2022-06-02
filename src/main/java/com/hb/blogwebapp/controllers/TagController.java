package com.hb.blogwebapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hb.blogwebapp.dto.TagDTO;
import com.hb.blogwebapp.service.ITagService;

@Controller
@RequestMapping(value = "tag")
public class TagController {

	@Autowired
	private ITagService tagService;

	@GetMapping
	public String getTags(Model model) {
		List<TagDTO> tags = tagService.getTagDTOs();
		model.addAttribute("tags", tags);
		return "tags";
	}

	@GetMapping("{id}")
	public String getTag(@PathVariable(name = "id") Integer id, Model model) {
		TagDTO tag = tagService.getTagDTO(id);
		model.addAttribute("tag", tag);
		return "tag";
	}

	@GetMapping("{id}/delete")
	public ModelAndView delete(@PathVariable(name = "id") Integer id) {
		tagService.delete(id);
		return new ModelAndView("redirect:/tag");
	}

	@GetMapping("/new")
	public String getCreateForm(Model model) {
		model.addAttribute("tag", new TagDTO());
		return "newTag";
	}

	@GetMapping("{id}/update")
	public String getUpdateForm(@PathVariable(name = "id") Integer id, Model model) {
		TagDTO tag = tagService.getTagDTO(id);
		model.addAttribute("tag", tag);
		return "updateTag";
	}

	@PostMapping
	public ModelAndView save(@ModelAttribute TagDTO tag) {
		tag = tagService.save(tag);
		return new ModelAndView("redirect:/tag/" + tag.getId());
	}

}
