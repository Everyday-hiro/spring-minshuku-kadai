package com.example.samuraitravel.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "star")
	private Integer star;
	
	@Column(name = "explanation")
	private String explanation;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "house_id")
	private Integer houseId;
	
	@Column(name = "writing")
	private LocalDate writing;
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}
