package com.simple.juni.security.service;

/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in
 * compliance with license agreement with kt corp. Any redistribution or use of this
 * software, with or without modification shall be strictly prohibited without prior written
 * approval of kt corp, and the copyright notice above does not evidence any actual or
 * intended publication of such software.
 */

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.count.CountRequestBuilder;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.facet.FacetBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.stereotype.Service;

import com.simple.juni.elasticsearch.ElasticSearcher;
import com.simple.juni.security.query.ESQuery;
import com.simple.juni.security.query.LimitOption;
import com.simple.juni.security.query.SearcherUtils;
import com.simple.juni.security.query.Sort;
import com.simple.juni.security.query.SortField;

@Service
public class SimpleSearcher {
	final ElasticSearcher store;

	public SimpleSearcher(ElasticSearcher store) {
		this.store = store;
	}

	private SearchResponse search(SearchRequest request, String query) {
		request.listenerThreaded(true);
		ActionFuture<SearchResponse> future = getClient().search(request);
		SearchResponse searchResponse = future.actionGet();

		SearcherUtils.printSearchResult(searchResponse, query);

		return searchResponse;
	}

	public SearchResponse execute(ESQuery query, String index, String type) throws Exception {
		return execute(query, index, type, new String[0]);
	}


	public SearchResponse execute(ESQuery query, String index, String type, String... fields) throws Exception {
		SearchSourceBuilder sb = SearchSourceBuilder.searchSource();
		if (fields != null && fields.length > 0){
			sb.fields(fields);
		}

		SearchRequest searchRequest = new SearchRequest(index);

		if(type != null){
			searchRequest.types(type);
		}

		sb.query(query.getSearchObject().toString());
		LimitOption option = query.getLimit();
		Sort sort = query.getSort();
		sb.from(option.offset());

		HighlightBuilder hb = new HighlightBuilder();
		if (query.getHighlightFields() != null){
			for (String field : query.getHighlightFields()) {
				hb.field(field, 100);
			}
		}
		sb.highlight(hb);

		sb.size(option.limit() > LimitOption.DEFAULT_MAX_LIMIT ? LimitOption.DEFAULT_MAX_LIMIT : (int) option.limit());
		if (sort != null) {
			for (SortField sortField : sort.getSortFields()) {
				FieldSortBuilder order = SortBuilders.fieldSort(sortField.getFieldName()).order(sortField.getOrder());
				order.ignoreUnmapped(true);
				sb.sort(order);
			}
		}

		searchRequest.source(sb);
		SearchResponse response = search(searchRequest, sb.toString());

		return response;
	}

	public GetResponse get(String index, String type, String id){
		GetRequestBuilder prepareGet = getClient().prepareGet(index, type, id);
		ListenableActionFuture<GetResponse> response = prepareGet.execute();
		return response.actionGet();
	}

	public int count(String index, String type, QueryBuilder queryBuilder) throws Exception {
		CountRequestBuilder countRequest = getClient().prepareCount(index);
		countRequest.setTypes(type);
		countRequest.setQuery(queryBuilder);

		CountResponse response = countRequest.execute().actionGet();

		SearcherUtils.printCountResult(response, queryBuilder.toString());

		return (int) response.getCount();
	}

	public int count(String index, String type, String query) throws Exception {
		CountRequestBuilder countRequest = getClient().prepareCount(index);
		countRequest.setTypes(type);
		//		countRequest.setQuery(query.getBytes());
		countRequest.setQuery(new QueryStringQueryBuilder(query));

		CountResponse response = countRequest.execute().actionGet();

		SearcherUtils.printCountResult(response, query);

		return (int) response.getCount();
	}

	public SearchResponse search(String index, String type, ESQuery query) throws Exception {
		return this.search(query, null, index, type, new String[0]);
	}

	public SearchResponse search(String index, String type, ESQuery query, String... fields) throws Exception {
		return this.search(query, null, index, type, fields);
	}

	public SearchResponse search(String index, String type, ESQuery query, FacetBuilder facet) throws Exception {
		return this.search(query, facet, index, type, new String[0]);
	}

	public SearchResponse search(String index, String type, ESQuery query, FacetBuilder facet, String... fields) throws Exception {
		return this.search(query, facet, index, type, fields);
	}

	public SearchResponse search(ESQuery query, FacetBuilder facet, String index, String type, String... fields) throws Exception {
		return this.search(query, facet, index, new String[]{type}, fields);
	}

	public SearchResponse search(ESQuery query, String index, String[] types, String... fields) throws Exception {
		return search(query, null, index, types, fields);
	}

	public SearchResponse search(ESQuery query, FacetBuilder facet, String index, String[] types, String[] fields) throws
		Exception {
		SearchSourceBuilder sb = SearchSourceBuilder.searchSource();

		if (query.getAggRangeBuilders() != null){
			for (RangeBuilder agg : query.getAggRangeBuilders() ) {
				sb.aggregation(agg);
			}
		}

		if (query.getAggBuilders() != null){
			for (AbstractAggregationBuilder agg : query.getAggBuilders()) {
				sb.aggregation(agg);
			}
		}

		if (facet != null){
			sb.facet(facet);
		}

		if (fields != null && fields.length > 0){
			sb.fields(fields);
		}

		SearchRequest searchRequest = new SearchRequest(index);
		if(types != null){
			searchRequest.types(types);
		}
		if (query != null){
			sb.query(query.getSearchObject().toString());
			LimitOption option = query.getLimit();
			Sort sort = query.getSort();
			sb.from(option.offset());

			HighlightBuilder hb = new HighlightBuilder();
			if (query.getHighlightFields() != null){
				for (String field : query.getHighlightFields()) {
					hb.field(field, 100);
				}
			}

			sb.size(option.limit() > LimitOption.DEFAULT_MAX_LIMIT ? LimitOption.DEFAULT_MAX_LIMIT : (int) option.limit());
			if (sort != null) {
				for (SortField sortField : sort.getSortFields()) {
					FieldSortBuilder order = SortBuilders.fieldSort(sortField.getFieldName()).order(sortField.getOrder());
					order.ignoreUnmapped(true);
					sb.sort(order);
				}
			}

		}

		searchRequest.source(sb);
		ActionFuture<SearchResponse> response = getClient().search(searchRequest);
		SearchResponse searchResponse = response.get();

		SearcherUtils.printSearchResult(searchResponse, sb.toString());

		return searchResponse;
	}

	public int deleteByQuery(String index, String type, ESQuery esQuery) throws Exception {
		BulkRequestBuilder bulk = store.getClient().prepareBulk();

		SearchResponse searchScroll = searchScroll(index, type, esQuery, new String[0]);
		String scrollId = searchScroll.getScrollId();
		SearchHits searchHits = searchScroll.getHits();

		int deleteCount = 0;
		while(searchHits.getTotalHits()>0 && searchHits.hits().length>0){
			for(SearchHit hit : searchHits.hits()){
				DeleteRequestBuilder prepareDelete = store.getClient().prepareDelete(index, type, hit.getId());
				bulk.add(prepareDelete);
			}

			if (bulk.numberOfActions() == 1000) {
				ListenableActionFuture<BulkResponse> response = bulk.execute();


				try {
					if (response.get().hasFailures()){
						throw new Exception(response.get().buildFailureMessage());
					}
				} catch (Exception e) {
					throw new Exception(e);
				}

				deleteCount += 1000;
				bulk = store.getClient().prepareBulk();
			}

			SearchScrollRequest searchScrollRequest = Requests.searchScrollRequest(scrollId);
			searchScrollRequest.scroll(TimeValue.timeValueMinutes(5));
			ActionFuture<SearchResponse> searcFuture = searchScroll(searchScrollRequest);

			SearchResponse nextResponse = searcFuture.actionGet();
			searchHits = nextResponse.getHits();
		}

		if (bulk.numberOfActions() > 0) {
			deleteCount += bulk.numberOfActions();
			ListenableActionFuture<BulkResponse> response = bulk.execute();
			try {
				if (response.get().hasFailures()){
					throw new Exception(response.get().buildFailureMessage());
				}
			} catch (Exception e) {
				throw new Exception(e);
			}
		}

		return deleteCount;
	}


	public SearchResponse searchScroll(String index, String type, ESQuery query, String... fields) throws Exception {
		String queryString = query.getSearchObject().toString(4);
		LimitOption limitOption = query.getLimit();

		SearchRequestBuilder prepareSearch = getClient().prepareSearch(index);
		prepareSearch.setTypes(type);
		prepareSearch.setQuery(queryString);

		if (fields != null) {
			prepareSearch.addFields(fields);
		}

		if (limitOption != null) {
			prepareSearch.setFrom(limitOption.offset());
			prepareSearch.setSize(limitOption.limit());
		}

		Sort sort = query.getSort();

		if (sort != null) {
			for (SortField sortField : sort.getSortFields()) {
				FieldSortBuilder order = SortBuilders.fieldSort(sortField.getFieldName()).order(sortField.getOrder());
				order.ignoreUnmapped(true);
				prepareSearch.addSort(order);
			}
		}

		prepareSearch.setScroll(TimeValue.timeValueMinutes(5));

		ActionFuture<SearchResponse> actionFuture = prepareSearch.execute();
		SearchResponse searchResponse = actionFuture.actionGet();

		SearcherUtils.printSearchResult(searchResponse, queryString);
		return searchResponse;

	}

	public ActionFuture<SearchResponse> searchScroll(SearchScrollRequest searchScrollRequest) {
		return getClient().searchScroll(searchScrollRequest);
	}

	public Client getClient() {
		return store.getClient();
	}
}
