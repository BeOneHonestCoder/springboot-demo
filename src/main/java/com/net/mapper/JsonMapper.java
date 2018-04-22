package com.net.mapper;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonMapper extends AbstractJsonMapper {

	public JsonMapper() {
		super(Jackson2ObjectMapperBuilder.json().build());
	}

}
