package cherrybro.dto;

import cherrybro.entitiy.FarmSection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmSectionDto {
	
	//농장동 고유 번호(PK)
	private Long farmSectionNo;
	
	//농장동 이름(1동, 2동, 3동)
	private String farmSectionName;
	
	//사용자(농장 주인) 번호(FK)
	private Long usersNo;
	
	/************************************************************/
	/* DTO -> Entity */	
	public static FarmSectionDto toDto(FarmSection farmSection) {
	    return FarmSectionDto.builder()
	            .farmSectionNo(farmSection.getFarmSectionNo())
	            .farmSectionName(farmSection.getFarmSectionName())
	            .usersNo(farmSection.getFarm().getUsers().getUsersNo()) // 사용자 번호를 꺼내기 위해 관계 따라 접근
	            .build();
	}
	
}
