package com.simple.juni.security.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FetchType {
	EMPTY(
		0,
		"empty",
		Collections.emptyList()),
	KEPCO_API(
		1,
		"kepcoapi",
		Arrays.asList("kepcoapi", "kepcoApi", "KEPCOAPI", "kepco_api", "KEPCO_API")
	),
	KEPCO_CRAWL(
		2,
		"kepcocrawl",
		Arrays.asList("kepcocrawl", "kepcoCrawl", "KEPCOCRAWL", "kepco_crawl", "KEPCO_CRAWL")
	);

	private final int intValue;
	private final String stringValue;
	private final List<String> codeList;

	public static FetchType findType(String name){
		if (StringUtils.isBlank(name)) {
			return EMPTY;
		}
		return Arrays.stream(FetchType.values())
			.filter(fetchType -> fetchType.hasTypeCode(name.trim()))
			.findAny()
			.orElse(EMPTY);
	}

	public boolean hasTypeCode(String name){
		return codeList.stream()
			.anyMatch(code -> code.equals(name));
	}
}
