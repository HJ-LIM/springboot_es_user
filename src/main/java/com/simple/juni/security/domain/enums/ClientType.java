package com.simple.juni.security.domain.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientType {
	EMPTY(
		0,
		"empty",
		Collections.emptyList()),
	NONE(
		1,
		"none",
		Arrays.asList("none","NONE")),
	DB_GATEWAY(
		2,
		"dbGateway",
		Arrays.asList("dbGateway","DBGATEWAY","DB_GATEWAY"));

	private final int intValue;
	private final String stringValue;
	private final List<String> codeList;

	public static ClientType findType(String name){
		if (StringUtils.isBlank(name)) {
			return EMPTY;
		}
		return Arrays.stream(ClientType.values())
			.filter(clientType -> clientType.hasTypeCode(name.trim()))
			.findAny()
			.orElse(EMPTY);
	}

	public boolean hasTypeCode(String name){
		return codeList.stream()
			.anyMatch(code -> code.equals(name));
	}
}
