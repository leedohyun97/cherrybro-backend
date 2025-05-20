package cherrybro.entitiy;

import java.util.List;

import cherrybro.dto.UsersDto;
import cherrybro.entitiy.role.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
//사용자
public class Users {
	
	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT 사용
	@Column(name = "users_no")//실제 DB 컬럼 이름
	private Long usersNo;//사용자 고유 번호(PK)
	
	@Column(name = "users_id")//실제 DB 컬럼 이름
	private String usersId;//사용자 로그인 ID
	
	@Column(name = "users_password")//실제 DB 컬럼 이름
	private String usersPassword;//사용자 로그인 PW
	
	@Column(name = "users_name")//실제 DB 컬럼 이름
	private String usersName;//사용자 이름
	
	@Column(name = "users_email")//실제 DB 컬럼 이름
	private String usersEmail;//사용자 이메일
	
	@Column(name = "users_phone")//실제 DB 컬럼 이름
	private String usersPhone;//사용자 전화번호
	
	@Enumerated(EnumType.STRING)
	@Column(name = "users_role")//실제 DB 컬럼 이름
	private Role usersRole;//사용자 권한(관리자, 농장주)
	
	/************************************************************/
	
	/* 한 명의 사용자가 여러개의 농장 보유 가능 */
	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Farm> farms;
	
	/************************************************************/
	
	/* DTO -> Entity */
	public static Users toEntity(UsersDto usersDto) {
		return Users.builder()
				.usersId(usersDto.getUsersId())
				.usersPassword(usersDto.getUsersPassword())
				.usersName(usersDto.getUsersName())
				.usersEmail(usersDto.getUsersEmail())
				.usersPhone(usersDto.getUsersPhone())
				.usersRole(usersDto.getUsersRole())
				.build();
	}
	
	/************************************************************/
	
	/* 비밀번호 찾기 시 비밀번호 재설정 */
	public void changePassword(String newPassword) {
		this.usersPassword = newPassword;
	}
	
	
}
