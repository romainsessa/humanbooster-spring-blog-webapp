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
import com.hb.blogwebapp.dto.TagDTO;

@Component
public class TagProxy {

	@Autowired
	private ApiProperties props;

	private Logger logger = LoggerFactory.getLogger(TagProxy.class);

	public List<TagDTO> getTags() {
		try {
			RestTemplate template = new RestTemplate();

			ResponseEntity<List<TagDTO>> response = template.exchange(props.getUrl() + "/api/tag", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<TagDTO>>() {
					});
			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Tags list not found");
			}
			return new ArrayList<>();
		}
	}

	public TagDTO getTag(Integer id) {
		try {
			RestTemplate template = new RestTemplate();

			ResponseEntity<TagDTO> response = template.exchange(props.getUrl() + "/api/tag/" + id, HttpMethod.GET, null,
					TagDTO.class);

			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Tag id " + id + " not found");
			}
			return null;
		}
	}

	public TagDTO save(TagDTO tag) {
		try {
			RestTemplate template = new RestTemplate();
			HttpEntity<TagDTO> request = new HttpEntity<TagDTO>(tag);
			ResponseEntity<TagDTO> response = template.exchange(props.getUrl() + "/api/tag/", HttpMethod.POST, request,
					TagDTO.class);
			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			logger.error(exception.getStatusCode().toString());
		}
		return tag;
	}

	public void delete(Integer id) {
		try {
			RestTemplate template = new RestTemplate();
			
			template.exchange(props.getUrl() + "/api/tag/" + id, HttpMethod.DELETE, null, Void.class);
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Tag id " + id + " not found");
			}
		}

	}

}
