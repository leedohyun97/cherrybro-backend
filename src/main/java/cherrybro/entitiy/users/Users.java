package cherrybro.entitiy.users;

import cherrybro.entitiy.role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
public class Users {
	
	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_no_SEQ")//MySQL의 AUTO_INCREMENT 사용
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
	
	@Column(name = "users_role")//실제 DB 컬럼 이름
	private Role usersRole;//사용자 권한(관리자, 농장주)
	
}
