package cherrybro.dto;

import cherrybro.entitiy.Farm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {
	
	//농장 고유 번호(PK)
	private Long farmNo;
	
	//농장 이름
	private String farmName;
	
	//사용자(농장 주인) 번호(FK)
	private Long usersNo;
	
	/****************************************/
	
	/* Entity-> DTO 변환 메소드 */
	public static FarmDto toDto(Farm farm) {
	    return FarmDto.builder()
	            .farmNo(farm.getFarmNo())
	            .farmName(farm.getFarmName())
	            .usersNo(farm.getUsers().getUsersNo())
	            .build();
	}
	
}
