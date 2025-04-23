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
	
	@Id
	@SequenceGenerator(name = "users_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_no_SEQ")
	@Column(name = "users_no")
	private Long usersNo;//사용자 고유 번호(PK)
	
	@Column(name = "users_id")
	private String usersId;//사용자 로그인 ID
	
	@Column(name = "users_password")
	private String usersPassword;//사용자 로그인 PW
	
	@Column(name = "users_name")
	private String usersName;//사용자 이름
	
	@Column(name = "users_email")
	private String usersEmail;//사용자 이메일
	
	@Column(name = "users_phone")
	private String usersPhone;//사용자 전화번호
	
	@Column(name = "users_role")
	private Role usersRole;//사용자 권한(관리자, 농장주)
	
}
