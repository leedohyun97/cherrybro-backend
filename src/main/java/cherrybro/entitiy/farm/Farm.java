package cherrybro.entitiy.farm;

import cherrybro.entitiy.users.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "farm")
//농장
public class Farm {
	
	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT 사용
	@Column(name = "farm_no")//실제 DB 컬럼 이름
	private Long farmNo;//농장 고유 번호(PK)
	
	@Column(name = "farm_name")//실제 DB 컬럼 이름
	private String farmName;//농장 이름
	
	@JoinColumn(name = "users_no")//실제 DB 컬럼 이름
	@ManyToOne(fetch = FetchType.LAZY)//N:1 관계 매핑, 지연 로딩 사용
	private Users users;//사용자 고유 번호(FK)
	
}
