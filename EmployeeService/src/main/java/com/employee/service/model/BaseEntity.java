package com.employee.service.model;

import java.time.LocalDate;
import java.util.UUID;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@MappedSuperclass

@Data
public class BaseEntity {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @CreationTimestamp
    private LocalDate createdOn;

    @UpdateTimestamp
    private LocalDate updatedOn;
    
    
  
	@PrePersist
	public void uuidGen() {
		String uuid = UUID.randomUUID().toString();
		setUuid(uuid);
	}



}
