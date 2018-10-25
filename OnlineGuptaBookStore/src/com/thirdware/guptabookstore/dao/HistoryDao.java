package com.thirdware.guptabookstore.dao;

import java.util.List;

import com.thirdware.guptabookstore.models.History;

public interface HistoryDao {
	public int insertHistory(List<History> history);
	public List<History> getHistory(String email);

}
