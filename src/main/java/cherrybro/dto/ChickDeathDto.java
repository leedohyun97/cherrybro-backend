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
public class ChickDeathDto {
	
	//도태폐사 고유 번호(PK)
	private Long chickDeathNo;
	
	//도태폐사 수(마리)
	private Integer chickDeathNumber;
	
	//도태폐사 일자(YYYY-MM-DD)
	private LocalDate chickDeathDate;
	
	//도태폐사 등록 타임스탬프(YYYY-MM-DD HH:mm:ss)
	private LocalDateTime chickDeathCreateAt;
	
	//농장동 고유 번호(FK)
	private Long farmSectionNo;
	
}
