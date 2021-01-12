package com.simple.juni.security.query;

import java.beans.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OffsetLimit implements Limit {

	private final int offset;
	private final int limit;
	private final long totalCount;

	@Override
	@Transient
	public long getTerm() {
		if(limit<=0) {
			return totalCount;
		}
		return (long)(offset + limit);
	}
}
