package com.simple.juni.elasticsearch;

import java.util.Map;

import org.elasticsearch.client.Client;

public interface ElasticSearchClient {
	void start(Map<String, String> properties);
	void stop();
	Client getClient();
}
