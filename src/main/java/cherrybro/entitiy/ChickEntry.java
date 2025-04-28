package cherrybro.entitiy;

import java.time.LocalDate;
import java.time.LocalDateTime;

import cherrybro.dto.ChickEntryDto;
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
@Table(name = "chick_entry")
//입추수수
public class ChickEntry {
	
	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT 사용
	@Column(name = "chick_entry_no")//실제 DB 컬럼 이름
	private Long chickEntryNo;//입추수수 고유 번호(PK)
	
	@Column(name = "chick_entry_number")//실제 DB 컬럼 이름
	private Integer chickEntryNumber;//입추수수(마리)
	
	@Column(name = "chick_entry_date")//실제 DB 컬럼 이름
	private LocalDate chickEntryDate;//입추일자
	
	@Column(name = "chick_entry_create_at")//실제 DB 컬럼 이름
	private LocalDateTime chickEntryCreateAt;//입추수수 등록 타임스탬프
	
	@JoinColumn(name = "farm_section")//외래 키(FK)로 사용할 컬럼
	@ManyToOne(fetch = FetchType.LAZY)//N:1 관계 매핑, 지연 로딩 사용
	private FarmSection farmSection;//농장동 고유 번호(FK)

	

	/************************************************************/
	/* DTO -> Entity */
	public static ChickEntry toEntity(ChickEntryDto chickEntryDto) {
	    FarmSection farmSection = FarmSection.builder()
	            .farmSectionNo(chickEntryDto.getFarmSectionNo())
	            .build();

	    return ChickEntry.builder()
	            .chickEntryNumber(chickEntryDto.getChickEntryNumber())
	            .chickEntryDate(chickEntryDto.getChickEntryDate())
	            .chickEntryCreateAt(chickEntryDto.getChickEntryCreateAt())
	            .farmSection(farmSection)
	            .build();
	}
	
	
	
	
	
	
	
	
	
	
	
}
