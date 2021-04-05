package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.GroupInfo;

public interface GroupRepository extends JpaRepository<GroupInfo,Integer>{
	Optional<List<GroupInfo>> findAllByGroupCategoryIn(List<Integer> list);
}
