package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private final ReviewService reviewService;
	private final ReviewRepository reviewRepository;
	
	public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
		this.reviewService = reviewService;
		this.reviewRepository = reviewRepository;
	}
	
	@GetMapping
	public String review(Model model) {
		List<Review> review = reviewRepository.findAll();
		
		model.addAttribute("review", review); 
		
		return "/templates/houses/show"; 
	}
	
	@GetMapping("/registerform")
	public String reviewregister(Model model) {
		model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
		
		return "templates/review/reviewregister";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			
			return "templates/review/reviewregister";
		}
		
		reviewService.create(reviewRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを登録しました。");
		
		return "redirect:/templates/reivew";
	}
	
	@PostMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Review review = reviewRepository.getReferenceById(id);
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(),review.getStar(), review.getExplanation());
				
				model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "templates/review/edit";
	}
	
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "templates/review/edit";
		}
		
		reviewService.update(reviewEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "レビューの内容を編集しました。");
		
		return "redirect:/admin/houses/show";
	}
	
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
		
		return "redirect:/admin/houses/show";
	}
}
