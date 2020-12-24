package com.simple.juni.security.domain.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class User {
	private final String _id;
	private String name;
}
