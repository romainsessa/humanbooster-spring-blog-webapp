package com.hb.blogwebapp.controllers;

import java.util.Date;
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

import com.hb.blogwebapp.controllers.formwrappers.PostTagFormWrapper;
import com.hb.blogwebapp.dto.PostCommentDTO;
import com.hb.blogwebapp.dto.PostDTO;
import com.hb.blogwebapp.dto.PostDetailsDTO;
import com.hb.blogwebapp.dto.TagDTO;
import com.hb.blogwebapp.service.IPostService;
import com.hb.blogwebapp.service.ITagService;

@Controller
@RequestMapping(value = "post")
public class PostController {

	@Autowired
	private IPostService postService;

	@Autowired
	private ITagService tagService;

	@GetMapping("{id}")
	public String getPost(@PathVariable(name = "id") Integer id, Model model) {
		PostDTO post = postService.getPostDTO(id);
		model.addAttribute("post", post);
		
		PostCommentDTO comment = new PostCommentDTO();
		comment.setPostId(post.getId());
		model.addAttribute("comment", comment);
		return "post";
	}

	@GetMapping
	public String getPosts(Model model) {
		List<PostDTO> posts = postService.getPostDTOs();
		model.addAttribute("posts", posts);
		return "posts";
	}

	@GetMapping("/new")
	public String getCreateForm(Model model) {
		PostDTO post = new PostDTO();
		post.setDetails(new PostDetailsDTO());
		model.addAttribute("post", post);
		return "newPost";
	}

	@GetMapping("{id}/update")
	public String getUpdateForm(@PathVariable(name = "id") Integer id, Model model) {
		PostDTO post = postService.getPostDTO(id);
		model.addAttribute("post", post);
		return "updatePost";
	}

	@PostMapping
	public ModelAndView save(@ModelAttribute PostDTO post) {
		if (post.getId() == null) {
			post.getDetails().setCreatedOn(new Date());
		}
		post = postService.save(post);
		return new ModelAndView("redirect:/post/" + post.getId());
	}

	@GetMapping("{id}/delete")
	public ModelAndView delete(@PathVariable(name = "id") Integer id) {
		postService.delete(id);
		return new ModelAndView("redirect:/post");
	}

	@GetMapping("/map")
	public String getMapForm(Model model) {
		List<PostDTO> posts = postService.getPostDTOs();
		List<TagDTO> tags = tagService.getTagDTOs();
		model.addAttribute("posts", posts);
		model.addAttribute("tags", tags);
		model.addAttribute("mapPostTag", new PostTagFormWrapper());
		return "mapPostTag";
	}

	@PostMapping("/map")
	public ModelAndView mapPostTag(@ModelAttribute PostTagFormWrapper postTag) {
		postService.mapPostTag(postTag.getPostId(), postTag.getTagId());
		return new ModelAndView("redirect:/post");
	}

	@GetMapping("{postId}/unmap/{tagId}")
	public ModelAndView unMapPostTag(@PathVariable(name = "postId") Integer postId,
			@PathVariable(name = "tagId") Integer tagId) {
		postService.unmapPostTag(postId, tagId);
		return new ModelAndView("redirect:/post/" + postId);
	}

}