package cherrybro.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//입추수수
public class ChickEntryDto {
	
	//입추수수 고유 번호(PK)
	private Long chickEntryNo;
	
	//입추수수(마리)
	private Integer chickEntryNumber;
	
	//입추일자(YYYY-MM-DD)
	private LocalDate chickEntryDate;
	
	//입추수수 등록 타임스탬프(YYYY-MM-DD HH:mm:ss)
	private LocalDateTime chickEntryCreateAt;
	
	//농장동 고유 번호(FK)
	private Long farmSectionNo;
	
}
