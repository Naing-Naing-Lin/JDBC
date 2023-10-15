package com.jdc.mkt.service;

import java.util.List;

public interface CrudOperation<T> {

	int insert(T t);
	int update(T t);
	int delete(T t);
	
	List<T> select(T t);
}
