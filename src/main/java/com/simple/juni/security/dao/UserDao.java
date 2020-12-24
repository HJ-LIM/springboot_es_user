package com.simple.juni.security.dao;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.stereotype.Repository;

import com.simple.juni.elasticsearch.ElasticSearcher;
import com.simple.juni.security.domain.bean.User;

@Repository
public class UserDao {
	final ElasticSearcher store;

	public UserDao(ElasticSearcher store) {
		this.store = store;
	}

	public User getUser(String userId){
		GetRequestBuilder prepareGet = store.getClient().prepareGet("tis", "user", userId);
		ListenableActionFuture<GetResponse> response = prepareGet.execute();
		GetResponse getFields = response.actionGet();
		System.out.println(getFields);
		return new User(userId);
	}

}
