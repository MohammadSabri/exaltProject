package com.exalt.petclinic.service;

import java.util.List;

public interface FileService {
	String create(String fileName);
	String update(String data, String fileName);
	String get(String fileName);
	List<String> getAll(int page, int limit);
	void delete (String fileName);
}
