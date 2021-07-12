package com.abhishek.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abhishek.model.Feedback;

public interface FeedbackService {
	List<Feedback> getAllFeedbacks();
	void saveFeedback(Feedback Feedback);
	Feedback getFeedbackById(long id);
	void deleteFeedbackById(long id);
	
}
