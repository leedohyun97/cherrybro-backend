package cherrybro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class usersDto {
	
	//사용자 고유 번호
	private Long usersNo;
	
	//사용자 이름
	private String usersName;
	
	private String usersId;
	
	private String usersPassword;
	
	private String usersEmail;
	
	private String usersPhone;
	
	private Enum usersRole;
	
	
}
