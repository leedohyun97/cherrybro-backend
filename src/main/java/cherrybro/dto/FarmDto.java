package cherrybro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {
	
	//농장 번호(PK)
	private Long farmNo;
	
	//농장 이름
	private String farmName;
	
	//사용자(농장 주인) 번호(FK)
	private Long usersNo;
	
}
