package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@Transactional
	public void create(ReviewRegisterForm reviewRegisterForm) {
		Review review = new Review();
		
		review.setStar(reviewRegisterForm.getStar());
		review.setExplanation(reviewRegisterForm.getExplanation());
		
		reviewRepository.save(review);
	}
	
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		
		review.setStar(reviewEditForm.getStar());
		review.setExplanation(reviewEditForm.getExplanation());
		
		reviewRepository.save(review);
	}
}
