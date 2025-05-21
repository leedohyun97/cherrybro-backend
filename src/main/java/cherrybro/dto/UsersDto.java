package cherrybro.dto;

import cherrybro.entitiy.Users;
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
	
	
	/****************************************/
	
	/* Entity-> DTO 변환 메소드 */
	public static UsersDto toDto(Users users) {
		return UsersDto.builder()
				.usersNo(users.getUsersNo())
				.usersName(users.getUsersName())
				.usersId(users.getUsersId())
				.usersPassword(users.getUsersPassword())
				.usersEmail(users.getUsersEmail())
				.usersPhone(users.getUsersPhone())
				.usersRole(users.getUsersRole())
				.build();
	}
	
	/* 아이디 찾기용 DTO */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FindIdDto {
		private String usersName;
		private String usersEmail;
	}
	
	/* 비밀번호 찾기용 DTO */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FindPasswordDto {
		private String usersId;
		private String usersEmail;
	}
}
