/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package com.simple.juni.security.query;

import org.elasticsearch.search.SearchHits;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OffsetOption implements LimitOption{
	private final int offset;
	private final int limit;

	public static final OffsetOption EMPTY = new OffsetOption(0,0);
	public static final OffsetOption ONE_OPTION = new OffsetOption(0, 1);
	public static final OffsetOption DEFAULT_OPTION = new OffsetOption(0, DEFAULT_LIMIT);
	public static final OffsetOption ACCEPTABLE_OPTION = new OffsetOption(0, DEFAULT_ACCEPTABLE_LIMIT);
	public static final OffsetOption DEFAULT_MAX_OPTION = new OffsetOption(0, DEFAULT_MAX_LIMIT);

	@Override
	public OffsetLimit limit(SearchHits searchHits){
		return new OffsetLimit(offset, limit, searchHits==null ? 0 : searchHits.getTotalHits());
	}

	@Override
	public int offset() {
		return offset;
	}

	@Override
	public int limit() {
		return limit;
	}

}
