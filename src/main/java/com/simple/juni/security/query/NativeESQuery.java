package com.simple.juni.security.query;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeBuilder;

import net.sf.json.JSONObject;

public class NativeESQuery implements ESQuery{
	private QueryBuilder querybuilder;
	private JSONObject query;
	private LimitOption limitOption;
	private Sort sort;
	private List<String> highlightFields;
	private List<RangeBuilder> aggRangeBuilder;
	private List<AbstractAggregationBuilder> aggBuilders;

	public NativeESQuery(JSONObject query,LimitOption limitOption ,Sort sort)  {
		this.query = query;
		this.limitOption = limitOption;
		this.sort = sort;
	}
	public NativeESQuery(QueryBuilder querybuilder,LimitOption limitOption ,Sort sort)  {
		this.querybuilder = querybuilder;
		this.limitOption = limitOption;
		this.sort = sort;
	}

	@Override
	public JSONObject getSearchObject() {
		return query==null ? JSONObject.fromObject(querybuilder.toString()) : query;
	}

	@Override
	public LimitOption getLimit() {
		return limitOption;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	@Override
	public List<String> getHighlightFields() {
		return highlightFields;
	}


	@Override
	public void setHighlightFields(List<String> highlightFields) {
		this.highlightFields = highlightFields;
	}
	@Override
	public String getChildNodeCountFilter() {
		return null;
	}
	@Override
	public void setChildNodeCountFilter(String childNodeCountFilter) {
	}
	
	@Override
	public List<RangeBuilder> getAggRangeBuilders() {
		return aggRangeBuilder;
	}
	@Override
	public void setAggRangeBuilders(List<RangeBuilder> aggRangeBuilder) {
		this.aggRangeBuilder = aggRangeBuilder;
	}
	@Override
	public List<AbstractAggregationBuilder> getAggBuilders() {
		return aggBuilders;
	}
	@Override
	public void setAggBuilders(List<AbstractAggregationBuilder> aggBuilders) {
		this.aggBuilders = aggBuilders;
	}
	@Override
	public void addAggBuilders(AbstractAggregationBuilder aggBuilder) {
		if (aggBuilders == null){
			aggBuilders = new ArrayList<AbstractAggregationBuilder>();
		}
		aggBuilders.add(aggBuilder);
	}


}
