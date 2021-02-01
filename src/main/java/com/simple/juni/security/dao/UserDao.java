package com.simple.juni.security.dao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.simple.juni.elasticsearch.ElasticSearcher;
import com.simple.juni.exception.SimpleSearchException;
import com.simple.juni.security.domain.Env;
import com.simple.juni.security.domain.bean.SimpleUser;
import com.simple.juni.security.domain.bean.User;
import com.simple.juni.security.query.LimitOption;
import com.simple.juni.security.query.NativeESQuery;
import com.simple.juni.security.query.OffsetOption;
import com.simple.juni.security.service.SimpleSearcher;

@Repository
public class UserDao {
	private final String TYPE = "user";

	final ElasticSearcher store;
	final SimpleSearcher simpleSearcher;

	public UserDao(ElasticSearcher store, SimpleSearcher simpleSearcher) {
		this.store = store;
		this.simpleSearcher = simpleSearcher;
	}

	public User loadUser(String userId){
		GetResponse response = simpleSearcher.get(Env.ES_INDEX, TYPE, userId);

		Gson gson = new Gson();
		User user = gson.fromJson(response.getSourceAsString(), User.class);
		user.set_id(response.getId());
		return user;
	}

	public List<User> loadAllUsers() throws SimpleSearchException {
		return loadAllUsers(OffsetOption.DEFAULT_OPTION);
	}

	public List<User> loadAllUsers(LimitOption limitOption) throws SimpleSearchException {
		NativeESQuery esQuery = new NativeESQuery(QueryBuilders.matchAllQuery(), limitOption,
			null);

		SearchResponse search = null;
		try {

			search = simpleSearcher.search(Env.ES_INDEX, TYPE, esQuery);
		} catch (Exception e) {
			throw new SimpleSearchException(e);
		}

		Gson gson = new Gson();
		return Arrays.stream(search.getHits().getHits())
			.map(hit -> {
				User user = gson.fromJson(hit.getSourceAsString(), User.class);
				user.set_id(hit.getId());
				return user;
			}).collect(Collectors.toList());
	}
}
