package com.simple.juni.elasticsearch;

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ElasticSearcher extends DefaultResourceLoader{
	@Autowired
	private ElasticSearchClient esClient;

	@PostConstruct
	public void start() {
		esClient.start(Collections.emptyMap());
	}

	@PreDestroy
	public void stop() {
		esClient.stop();
	}

	public Client getClient() {
		return esClient.getClient();
	}
}
