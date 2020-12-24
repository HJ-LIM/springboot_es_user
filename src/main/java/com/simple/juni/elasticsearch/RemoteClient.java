package com.simple.juni.elasticsearch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.loader.SettingsLoaderFactory;
import org.elasticsearch.common.settings.loader.YamlSettingsLoader;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RemoteClient  extends DefaultResourceLoader implements ElasticSearchClient{
	private Client client;

	@Value("${elasticsearch.remote.server}")
	private String server;

	@Value("${elasticsearch.remote.port}")
	private int port;

	@Value("${elasticsearch.remote.cluster.name}")
	private String clusterName;

	@Override
	public void start(Map<String, String> properties) {
		if (log.isInfoEnabled()) {
			log.info("Starting the Elastic Search Transport Client.....");
		}

		client =  new TransportClient(
			ImmutableSettings.settingsBuilder()
				.put("cluster.name", clusterName)
		).addTransportAddresses(
			new InetSocketTransportAddress(new InetSocketAddress(server, port))
		);
	}

	@Override
	public void stop() {
		log.debug("Close the Elastic Search Client....");
		client.close();
	}

	@Override
	public Client getClient() {
		return client;
	}
}
