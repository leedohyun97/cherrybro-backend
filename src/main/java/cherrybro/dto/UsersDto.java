package cherrybro.dto;

import cherrybro.entitiy.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
	
	//사용자 고유 번호
	private Long usersNo;
	
	//사용자 이름
	private String usersName;
	
	//사용자 로그인 ID
	private String usersId;
	
	//사용자 로그인 PW
	private String usersPassword;
	
	//사용자 이메일
	private String usersEmail;
	
	//사용자 전화번호
	private String usersPhone;
	
	//사용자 권한(관리자, 농장주)
	private Role usersRole;
	
	
}
