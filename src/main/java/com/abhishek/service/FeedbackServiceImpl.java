package com.abhishek.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abhishek.repository.FeedbackRepository;
import com.abhishek.model.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;


	@Override
	public List<Feedback> getAllFeedbacks() {
		return feedbackRepository.findAll();
	}

	@Override
	public void saveFeedback(Feedback feedback) {
		this.feedbackRepository.save(feedback);
	}

	@Override
	public Feedback getFeedbackById(long id) {
		Optional<Feedback> optional = feedbackRepository.findById(id);
		Feedback feedback = null;
		if (optional.isPresent()) {
			feedback = optional.get();
		} else {
			throw new RuntimeException(" Feedback not found for id :: " + id);
		}
		return feedback;
	}

	@Override
	public void deleteFeedbackById(long id) {
		this.feedbackRepository.deleteById(id);
	}

}
