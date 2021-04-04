package com.studywithme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studywithme.entity.Category;
import com.studywithme.repository.CategoryRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@GetMapping("")
	@ApiOperation(value="카테고리 목록 불러오기",notes="db에 저장된 카테고리 목록을 불러온다\n글쓰기나 그룹생성에 써")
	public Object getCategoryList() {
		Map<String,Object> result=new HashMap<>();
		List<Category> list=categoryRepository.findAll();
		
		result.put("categoryList",null);
		
		if(list!=null) {
			result.clear();
			result.put("categoryList",list);
		}
		return result;
	}
	
	@GetMapping("/{category-list}")
	@ApiOperation(value="특정 카테고리 번호의 이름들 받기",notes="db에서 해당 카테고리 번호들의 이름을 받는다")
	public Object getCategoryNames(@PathVariable("category-list") List<Integer> categoryList) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("searchedCategoryList",null);
		
		List<Category> list=categoryRepository.findAllById(categoryList);
		if(list!=null) {
			result.clear();
			result.put("searchedCategoryList",list);
		}
		
		return result;
	}
	
}
