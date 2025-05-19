package cherrybro.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import cherrybro.entitiy.ChickDisposal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//도사
public class ChickDisposalDto {
	
	//도사 고유 번호(PK)
	private Long chickDisposalNo;
	
	//도사 수(마리)
	private Integer chickDisposalNumber;
	
	//도사 일자(YYYY-MM-DD)
	private LocalDate chickDisposalDate;
	
	//도사 등록 타임스탬프(YYYY-MM-DD HH:mm:ss)
	private LocalDateTime chickDisposalCreateAt;
	
	//농장동 고유 번호(FK)
	private Long farmSectionNo;
	
	/************************************************************/
	/* DTO -> Entity */		
	public static ChickDisposalDto toDto(ChickDisposal chickDisposal) {
		
	    return ChickDisposalDto.builder()
	            .chickDisposalNo(chickDisposal.getChickDisposalNo())
	            .chickDisposalNumber(chickDisposal.getChickDisposalNumber())
	            .chickDisposalDate(chickDisposal.getChickDisposalDate())
	            .chickDisposalCreateAt(chickDisposal.getChickDisposalCreateAt())
	            .farmSectionNo(chickDisposal.getFarmSection().getFarmSectionNo())
	            .build();
	}
}
