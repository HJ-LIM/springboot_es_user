/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package com.simple.juni.security.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.search.sort.SortOrder;

public class Sort {
	public static final Sort UID_ASC  = new Sort(new SortField("_uid", SortOrder.ASC));
	public static final Sort UID_DESC  = new Sort(new SortField("_uid",SortOrder.DESC));

	private final List<SortField> sortFields = new ArrayList<>();
	public Sort(SortField ...fields) {
		Collections.addAll(sortFields, fields);
	}

	public Sort addSortField(SortField field){
		sortFields.add(field);
		return this;
	}

	public List<SortField> getSortFields() {
		return sortFields;
	}

}
