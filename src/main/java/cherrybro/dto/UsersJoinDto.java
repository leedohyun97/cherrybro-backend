package cherrybro.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersJoinDto {
	
	//사용자 고유 번호
	private Long usersNo;
	
	//사용자 이름
	private String usersName;
	
	//사용자 로그인 ID
	private String usersId;
	
	//사용자 비밀번호
	private String usersPassword;
	
	//사용자 이메일
	private String usersEmail;
	
	//사용자 전화번호
	private String usersPhone;
	
	//농장 이름
	private String farmName;
	
	//사용자 농장 구역
	private List<String> farmSections;
	
}
