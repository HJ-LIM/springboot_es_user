package com.simple.juni.security.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ElectricChargeType {
	EMPTY(
		0,
		"empty",
		Collections.emptyList()),
	GENERAL(
		1,
		"general",
		Arrays.asList("general","GENERAL")
	),
	BUSINESS(
		2,
		"business",
		Arrays.asList("BUSINESS","BUSINESS")
	);

	private final int intValue;
	private final String stringValue;
	private final List<String> codeList;

	public static ElectricChargeType findType(String name){
		if (StringUtils.isBlank(name)) {
			return EMPTY;
		}
		return Arrays.stream(ElectricChargeType.values())
			.filter(electricChargeType -> electricChargeType.hasTypeCode(name.trim()))
			.findAny()
			.orElse(EMPTY);
	}

	public boolean hasTypeCode(String name){
		return codeList.stream()
			.anyMatch(code -> code.equals(name));
	}
}
