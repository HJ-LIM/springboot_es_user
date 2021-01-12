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

public interface LimitOption {
	int DEFAULT_OFFSET = 0;
	int DEFAULT_LIMIT = 20;
	int DEFAULT_ACCEPTABLE_LIMIT = 1000;
	int DEFAULT_MAX_LIMIT = 50000;

	int limit();
	int offset();

	Limit limit(SearchHits searchHits);
}
