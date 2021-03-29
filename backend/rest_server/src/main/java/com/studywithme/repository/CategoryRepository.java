package com.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

}
