package com.net.mapper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractJsonMapper {

	protected ObjectMapper mapper;

	public AbstractJsonMapper(ObjectMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public <T> T readValue(String json, Class<T> toValueType) throws IOException {
		return mapper.readValue(json, toValueType);
	}

	public String writeValueAsString(Object value) throws JsonProcessingException {
		return mapper.writeValueAsString(value);
	}

	public <T> T convertValue(Object fromValue, Class<T> toValueType) {
		return mapper.convertValue(fromValue, toValueType);
	}

}
