package cherrybro.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import cherrybro.entitiy.ChickDeath;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//폐사
public class ChickDeathDto {
	
	//폐사 고유 번호(PK)
	private Long chickDeathNo;
	
	//폐사 수(마리)
	private Integer chickDeathNumber;
	
	//폐사 일자(YYYY-MM-DD)
	private LocalDate chickDeathDate;
	
	//폐사 등록 타임스탬프(YYYY-MM-DD HH:mm:ss)
	private LocalDateTime chickDeathCreateAt;
	
	//농장동 고유 번호(FK)
	private Long farmSectionNo;
	
	/************************************************************/
	/* DTO -> Entity */		
	public static ChickDeathDto toDto(ChickDeath chickDeath) {
		
	    return ChickDeathDto.builder()
	            .chickDeathNo(chickDeath.getChickDeathNo())
	            .chickDeathNumber(chickDeath.getChickDeathNumber())
	            .chickDeathDate(chickDeath.getChickDeathDate())
	            .chickDeathCreateAt(chickDeath.getChickDeathCreateAt())
	            .farmSectionNo(chickDeath.getFarmSection().getFarmSectionNo())
	            .build();
	}
}
