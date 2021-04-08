package com.studywithme.DtoOnlyReturn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupProfileDto {
	private byte[] groupProfileImg;
	private int groupId;
}
