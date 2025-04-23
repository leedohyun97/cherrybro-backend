package cherrybro.entitiy.chick;

import java.time.LocalDate;
import java.time.LocalDateTime;

import cherrybro.entitiy.farm.Farm;
import cherrybro.entitiy.farm.FarmSection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "chick_death")
//도태폐사
public class ChickDeath {
	
	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT 사용
	@Column(name = "chick_death_no")//실제 DB 컬럼 이름
	private Long chickDeathNo;//도태폐사 고유 번호(PK)
	
	@Column(name = "chick_death_number")//실제 DB 컬럼 이름
	private Integer chickDeathNumber;//도태폐사(마리)
	
	@Column(name = "chick_death_date")//실제 DB 컬럼 이름
	private LocalDate chickDeathDate;//도태폐사 일자
	
	@Column(name = "chick_death_create_at")//실제 DB 컬럼 이름
	private LocalDateTime chickDeathCreateAt;//도태폐사 등록 타임스탬프
	
	@JoinColumn(name = "farm_section")//외래 키(FK)로 사용할 컬럼
	@ManyToOne(fetch = FetchType.LAZY)//N:1 관계 매핑, 지연 로딩 사용
	private FarmSection farmSection;//농장동 고유 번호(FK)

	
	
	
	
	
	
	
	
	
	
	
	
}
