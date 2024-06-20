package com.example.samuraitravel.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	
	@NotBlank
	private String name;
	
	@Max(value = 5)
	private Integer star;
	
	@NotBlank(message = "理由をご入力ください。")
	private String explanation;
	
	
}
