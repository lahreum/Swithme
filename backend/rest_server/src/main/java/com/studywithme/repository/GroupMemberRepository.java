package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Integer>{
	Optional<GroupMember> findByGroupMemberUserNicknameAndGroupMemberGroupId(String groupMemberUserNickname,int groupMemberGroupId);
	Optional<List<GroupMember>> findByGroupMemberGroupId(int groupMemberGroupId);
	Optional<List<GroupMember>> findByGroupMemberUserNickname(String groupMemberUserNickname);
}
