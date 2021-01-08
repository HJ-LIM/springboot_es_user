package com.simple.juni.security.dao;

import java.util.List;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.simple.juni.elasticsearch.ElasticSearcher;
import com.simple.juni.security.domain.Env;
import com.simple.juni.security.domain.bean.User;


@Repository
public class UserDao {
	private final String TYPE = "user";

	final ElasticSearcher store;

	public UserDao(ElasticSearcher store) {
		this.store = store;
	}

	public User loadUser(String userId){
		GetRequestBuilder prepareGet = store.getClient().prepareGet(Env.ES_INDEX, TYPE, userId);
		ListenableActionFuture<GetResponse> response = prepareGet.execute();
		GetResponse getFields = response.actionGet();

		Gson gson = new Gson();
		User user = gson.fromJson(getFields.getSourceAsString(), User.class);
		user.set_id(getFields.getId());
		return user;
	}

	public List<User> loadAllUsers() {
		//TODO : Simple Searcher 만들기
		return null;
	}
}
