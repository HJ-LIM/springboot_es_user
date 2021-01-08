package com.simple.juni.security.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {
	public static String ES_INDEX;

	public Env(@Value("${elasticsearch.index}") String esIndex){
		ES_INDEX = esIndex;
	}
}
