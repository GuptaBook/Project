package com.thirdware.guptabookstore.dao;

import java.util.List;

import com.thirdware.guptabookstore.models.Rating;

public interface RatingDao {
	int insertRating(Rating rating);
	Rating fetchRating(int bookid);
	boolean getRatingById(String email,int bookid);
}
