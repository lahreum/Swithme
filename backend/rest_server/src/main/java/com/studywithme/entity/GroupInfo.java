package com.studywithme.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int groupId;
	
	private String groupName;
	private int groupCategory;
	private int groupDailyGoal;
	private String groupPassword;
	private String groupGoalDate;
	private int groupMaxMemberCount;
	@Column(insertable=false)
	private int groupCurMemberCount;
	private String groupMasterNickname;
	private Blob groupProfileImg;
	private String groupNotice;
}
