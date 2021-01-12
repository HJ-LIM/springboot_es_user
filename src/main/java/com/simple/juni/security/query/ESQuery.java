/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package com.simple.juni.security.query;

import java.util.List;

import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeBuilder;

import net.sf.json.JSONObject;

public interface ESQuery {
	JSONObject getSearchObject() throws Exception;
	LimitOption getLimit();
	Sort getSort();
	List<String> getHighlightFields();
	void setHighlightFields(List<String> highlightFields);


	String getChildNodeCountFilter();
	void setChildNodeCountFilter(String childNodeCountFilter);

	List<RangeBuilder> getAggRangeBuilders();
	void setAggRangeBuilders(List<RangeBuilder> aggregationBuilders);
	
	List<AbstractAggregationBuilder> getAggBuilders();
	void setAggBuilders(List<AbstractAggregationBuilder> aggBuilders);
	void addAggBuilders(AbstractAggregationBuilder aggBuilder);
}
