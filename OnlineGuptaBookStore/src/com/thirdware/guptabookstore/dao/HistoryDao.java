package com.thirdware.guptabookstore.dao;

import java.util.List;

import com.thirdware.guptabookstore.models.History;

public interface HistoryDao {
	public int insertHistory(History history);
	public List<History> getHistory(String email);
	public List<History> getAllHistory(String date);
}
