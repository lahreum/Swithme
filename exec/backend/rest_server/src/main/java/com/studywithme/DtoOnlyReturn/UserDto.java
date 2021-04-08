package com.studywithme.DtoOnlyReturn;

import java.sql.Blob;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto implements Comparable<UserDto>{
	private String nickname;
	private byte[] profileImg;
	private int todayStudyTime;
	private boolean isStudying;

	@Override
	public int compareTo(UserDto o) {
		return Integer.compare(o.todayStudyTime,this.todayStudyTime);
	}
}
