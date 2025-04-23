package cherrybro.dto;

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
	
}
