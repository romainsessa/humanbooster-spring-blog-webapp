package com.hb.blogwebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hb.blogwebapp.dto.PostCommentDTO;
import com.hb.blogwebapp.service.ICommentService;

@Controller
@RequestMapping(value = "comment")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	@PostMapping
	public ModelAndView save(@ModelAttribute(name = "comment") PostCommentDTO comment) {
		commentService.save(comment);
		return new ModelAndView("redirect:/post/" + comment.getPostId());
	}

	@GetMapping("/{id}/post/{postId}")
	public ModelAndView delete(@PathVariable(name = "id") Integer id, @PathVariable(name = "postId") Integer postId) {
		commentService.delete(id);
		return new ModelAndView("redirect:/post/" + postId);
	}
	
	@GetMapping("{id}/update")
	public String getUpdateForm(@PathVariable(name = "id") Integer id, Model model) {
		PostCommentDTO comment = commentService.getCommentDTO(id);
		model.addAttribute("comment", comment);		
		return "updateComment";
	}
}