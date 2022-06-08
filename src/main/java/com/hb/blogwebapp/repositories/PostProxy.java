package com.hb.blogwebapp.repositories;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.hb.blogwebapp.configuration.ApiProperties;
import com.hb.blogwebapp.dto.PostDTO;

@Component
public class PostProxy {

	@Autowired
	private ApiProperties props;

	private Logger logger = LoggerFactory.getLogger(PostProxy.class);

	public List<PostDTO> getPosts() {
		try {
			RestTemplate template = new RestTemplate();

			ResponseEntity<List<PostDTO>> response = template.exchange(props.getUrl() + "/api/post", HttpMethod.GET,
					null, new ParameterizedTypeReference<List<PostDTO>>() {
					});
			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			logger.error(exception.getStatusCode().toString());
			return new ArrayList<>();
		}
	}

	public PostDTO getPost(Integer id) {
		try {
			RestTemplate template = new RestTemplate();

			ResponseEntity<PostDTO> response = template.exchange(props.getUrl() + "/api/post/" + id, HttpMethod.GET,
					null, PostDTO.class);

			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Post id " + id + " not found");
			} else {
				logger.error(exception.getStatusCode().toString());
			}
			return null;
		}
	}

	public PostDTO save(PostDTO tag) {
		try {
			RestTemplate template = new RestTemplate();
			HttpEntity<PostDTO> request = new HttpEntity<PostDTO>(tag);
			ResponseEntity<PostDTO> response = template.exchange(props.getUrl() + "/api/post/", HttpMethod.POST,
					request, PostDTO.class);
			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			logger.error(exception.getStatusCode().toString());
		}
		return tag;
	}

	public void delete(Integer id) {
		try {
			RestTemplate template = new RestTemplate();

			template.exchange(props.getUrl() + "/api/post/" + id, HttpMethod.DELETE, null, Void.class);
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Post id " + id + " not found");
			} else {
				logger.error(exception.getStatusCode().toString());
			}
		}
	}

	public void mapPostTag(Integer postId, Integer tagId) {
		try {
			RestTemplate template = new RestTemplate();			
			template.exchange(props.getUrl() + "/api/post/" + postId + "/map/" + tagId, HttpMethod.GET, null, Void.class);
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Post id " + postId + " or Tag id " + tagId + " not found");
			} else {
				logger.error(exception.getStatusCode().toString());
			}
		}		
	}

	public void unmapPostTag(Integer postId, Integer tagId) {
		try {
			RestTemplate template = new RestTemplate();			
			template.exchange(props.getUrl() + "/api/post/" + postId + "/unmap/" + tagId, HttpMethod.GET, null, Void.class);
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Post id " + postId + " or Tag id " + tagId + " not found");
			} else {
				logger.error(exception.getStatusCode().toString());
			}
		}		
	}

}