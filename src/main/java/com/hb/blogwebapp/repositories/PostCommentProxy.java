package com.hb.blogwebapp.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.hb.blogwebapp.configuration.ApiProperties;
import com.hb.blogwebapp.dto.PostCommentDTO;

@Component
public class PostCommentProxy {

	@Autowired
	private ApiProperties props;

	private Logger logger = LoggerFactory.getLogger(PostCommentProxy.class);

	public PostCommentDTO getPostComment(Integer id) {
		try {
			RestTemplate template = new RestTemplate();

			ResponseEntity<PostCommentDTO> response = template.exchange(props.getUrl() + "/api/comment/" + id,
					HttpMethod.GET, null, PostCommentDTO.class);

			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Comment id " + id + " not found");
			} else {
				logger.error(exception.getStatusCode().toString());
			}
			return null;
		}
	}

	public PostCommentDTO save(PostCommentDTO comment) {
		try {
			RestTemplate template = new RestTemplate();
			HttpEntity<PostCommentDTO> request = new HttpEntity<PostCommentDTO>(comment);
			ResponseEntity<PostCommentDTO> response = template.exchange(props.getUrl() + "/api/comment/",
					HttpMethod.POST, request, PostCommentDTO.class);
			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			logger.error(exception.getStatusCode().toString());
		}
		return comment;
	}

	public void delete(Integer id) {
		try {
			RestTemplate template = new RestTemplate();

			template.exchange(props.getUrl() + "/api/comment/" + id, HttpMethod.DELETE, null, Void.class);
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Comment id " + id + " not found");
			} else {
				logger.error(exception.getStatusCode().toString());
			}
		}
	}

}