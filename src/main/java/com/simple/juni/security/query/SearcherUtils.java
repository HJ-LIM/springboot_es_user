/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package com.simple.juni.security.query;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.xcontent.XContentHelper;

import net.sf.json.JSONObject;

import com.simple.juni.utils.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearcherUtils {
	public static void printSearchResult(SearchResponse searchResponse, String query){
		if (!log.isDebugEnabled()){
			return;
		}

		int totalShards = searchResponse.getTotalShards();
		long took = searchResponse.getTookInMillis();
		int failedShards = searchResponse.getFailedShards();
		int successfulShards = searchResponse.getSuccessfulShards();
		long totalHits = searchResponse.getHits().getTotalHits();
		boolean isTimedOut = searchResponse.isTimedOut();

		ShardSearchFailure[] shardFailures = searchResponse.getShardFailures();
		for (ShardSearchFailure shardSearchFailure : shardFailures) {
			if (shardSearchFailure != null){
				System.out.println(shardSearchFailure.reason());
			}
		}

		log.debug("elapsed:" + took + " ms, totalHits:" +totalHits + ", totalShards:" + totalShards +", successfulShards:" + successfulShards + ", failedShards:" + failedShards + ", timedOut:" + isTimedOut + " (" + getCallerInfo(3) + ")\n" + query);
	}

	private static String getCallerInfo(int depth) {
		StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();

		StringBuilder sb = new StringBuilder();
		for (int i = 3; i < depth + 4; i++) {
			StackTraceElement stackTraceElement = stackTrace[i];
			String className = stackTraceElement.getFileName();
			int lineNumber = stackTraceElement.getLineNumber();

			sb.append(className + " line " + lineNumber + ", ");
		}
		return sb.toString();

	}

	public static void printSearchResult(DeleteByQueryResponse searchResponse, String query){
		if (!log.isDebugEnabled()){
			return;
		}

		log.debug("deleteByQuery (" + getCallerInfo(3) + ")\n" + query);

	}

	public static void printCountResult(CountResponse response, String query) {
		if (!log.isDebugEnabled()){
			return;
		}

		int totalShards = response.getTotalShards();
		int failedShards = response.getFailedShards();
		int successfulShards = response.getSuccessfulShards();

		log.debug(
			"CountQuery - count: " + response.getCount()+", totalShards:" + totalShards +", successfulShards:" + successfulShards + ", failedShards:" + failedShards + " (" + getCallerInfo(3) + ")\n" + query
		);
	}

	public static void printSearchResult(SearchResponse searchResponse, BytesReference source) {
		if (!log.isDebugEnabled()){
			return;
		}

		JSONObject loggingObject = new JSONObject();
		try {
			byte[] array = source.array();
			JSONObject fromObject = JSONObject.fromObject(XContentHelper.convertToJson(array, 0, array.length, false));
			for (Object key : fromObject.keySet()) {
				String sKey = key.toString();
				if (sKey.endsWith("_binary")) {
					loggingObject.put(StringUtils.substringBefore(sKey, "_binary"), Base64.decode(fromObject.get(key).toString()));
				} else {
					loggingObject.put(sKey, fromObject.get(sKey));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int totalShards = searchResponse.getTotalShards();
		long took = searchResponse.getTookInMillis();
		int failedShards = searchResponse.getFailedShards();
		int successfulShards = searchResponse.getSuccessfulShards();
		long totalHits = searchResponse.getHits().getTotalHits();
		boolean isTimedOut = searchResponse.isTimedOut();

		log.debug(
			"elapsed:" + took + " ms, totalHits:" +totalHits + ", totalShards:" + totalShards +", successfulShards:" + successfulShards + ", failedShards:" + failedShards + ", timedOut:" + isTimedOut + " (" + getCallerInfo(3) + ")\n" + loggingObject.toString(4)
		);
	}



}
