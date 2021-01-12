package com.simple.juni.security.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CBLType {
	EMPTY(
		0,
		"empty",
		Collections.emptyList()),
	MAX(
		1,
		"max",
		Arrays.asList("max","MAX")),
	MID(
		2,
		"mid",
		Arrays.asList("mid","MID")),
	ALT_ONLY_THE_DAY(
		3,
		"alt_only_the_day",
		Arrays.asList("alt_only_the_day","ALT_ONLY_THE_DAY")),
	ALT_30DAYS(
		4,
		"alt_30days",
		Arrays.asList("alt_30days","ALT_30DAYS")),
	ALT_NO_ADJUSTED(
		5,
		"alt_no_adjusted",
		Arrays.asList("alt_no_adjusted","ALT_NO_ADJUSTED"));

	private final int intValue;
	private final String stringValue;
	private final List<String> codeList;

	public static CBLType findType(String name){
		if (StringUtils.isBlank(name)) {
			return EMPTY;
		}
		return Arrays.stream(CBLType.values())
			.filter(cblType -> cblType.hasTypeCode(name.trim()))
			.findAny()
			.orElse(EMPTY);
	}

	public boolean hasTypeCode(String name){
		return codeList.stream()
			.anyMatch(code -> code.equals(name));
	}
}
