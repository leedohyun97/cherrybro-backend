package cherrybro.entitiy;

import java.util.List;

import cherrybro.dto.FarmSectionDto;
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
@Table(name = "farm_section")
//농장동
public class FarmSection {
	
	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT 사용
	@Column(name = "farm_section_no")//실제 DB 컬럼 이름
	private Long farmSectionNo;//농장동 고유 번호(PK)
	
	@Column(name = "farm_section_name")//실제 DB 컬럼 이름
	private String farmSectionName;//농장동 이름(1동, 2동, 3동)
	
	@JoinColumn(name = "farm_no")//외래 키(FK)로 사용할 컬럼
	@ManyToOne(fetch = FetchType.LAZY)//N:1 관계 매핑, 지연 로딩 사용
	private Farm farm;//농장 고유 번호(FK)
	
	/************************************************************/

	/* 한 농장동이 여러개의 입추수수 보유 가능 */
	@OneToMany(mappedBy = "farmSection", fetch = FetchType.LAZY)
	private List<ChickEntry> chickEntries;
	
	/* 한 농장동이 여러개의 도태폐사 보유 가능 */
	@OneToMany(mappedBy = "farmSection", fetch = FetchType.LAZY)
	private List<ChickDeath> chickDeaths;
	

	/************************************************************/
	/* DTO -> Entity */
	public static FarmSection toEntity(FarmSectionDto farmSectionDto) {
	    // farm 객체는 farm_no만 설정해서 참조용으로 생성
	    Farm farm = Farm.builder()
	            .farmNo(farmSectionDto.getFarmNo())
	            .build();

	    return FarmSection.builder()
	            .farmSectionName(farmSectionDto.getFarmSectionName())
	            .farm(farm)
	            .build();
	}
}
