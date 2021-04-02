package com.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.GroupInfo;

public interface GroupRepository extends JpaRepository<GroupInfo,Integer>{

}
