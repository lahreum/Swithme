package com.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Integer>{

}
