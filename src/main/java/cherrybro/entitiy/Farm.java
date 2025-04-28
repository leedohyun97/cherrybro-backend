package cherrybro.entitiy;

import java.util.List;

import cherrybro.dto.FarmDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "farm")
//농장
public class Farm {
	
	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT 사용
	@Column(name = "farm_no")//실제 DB 컬럼 이름
	private Long farmNo;//농장 고유 번호(PK)
	
	@Column(name = "farm_name")//실제 DB 컬럼 이름
	private String farmName;//농장 이름
	
	@JoinColumn(name = "users_no")//외래 키(FK)로 사용할 컬럼
	@ManyToOne(fetch = FetchType.LAZY)//N:1 관계 매핑, 지연 로딩 사용
	private Users users;//사용자 고유 번호(FK)

	/************************************************************/

	/* 한 농장이 여러개의 농장동 보유 가능 */
	@OneToMany(mappedBy = "farm", fetch = FetchType.LAZY)
	private List<FarmSection> farmSections;

	/************************************************************/
	
	/* DTO -> Entity */
	public static Farm toEntity(FarmDto farmDto) {
		
		Users users = Users.builder()
				.usersNo(farmDto.getUsersNo())
				.build();
		
		return Farm.builder()
				.farmName(farmDto.getFarmName())
				.users(users)
				.build();
	}
}
