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
	
	//농장 번호(FK)
	private Long farmNo;
	
	/************************************************************/
	/* DTO -> Entity */	
	public static FarmSectionDto toDto(FarmSection farmSection) {
	    return FarmSectionDto.builder()
	            .farmSectionNo(farmSection.getFarmSectionNo())
	            .farmSectionName(farmSection.getFarmSectionName())
	            .farmNo(farmSection.getFarm().getFarmNo())
	            .build();
	}
	
}
