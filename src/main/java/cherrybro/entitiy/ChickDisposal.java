package cherrybro.entitiy;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import cherrybro.dto.ChickDisposalDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "chick_disposal")
//도사
public class ChickDisposal {
	

	@Id//기본 키(PK) 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT 사용
	@Column(name = "chick_disposal_no")//실제 DB 컬럼 이름
	private Long chickDisposalNo;//도사 고유 번호(PK)
	
	@Column(name = "chick_disposal_number")//실제 DB 컬럼 이름
	private Integer chickDisposalNumber;//도사(마리)
	
	@Column(name = "chick_disposal_date")//실제 DB 컬럼 이름
	private LocalDate chickDisposalDate;//도사 일자
	
	@CreationTimestamp
	@Column(name = "chick_disposal_create_at")//실제 DB 컬럼 이름
	private LocalDateTime chickDisposalCreateAt;//도사 등록 타임스탬프
	
	@JoinColumn(name = "farm_section_no")//외래 키(FK)로 사용할 컬럼
	@ManyToOne(fetch = FetchType.LAZY)//N:1 관계 매핑, 지연 로딩 사용
	private FarmSection farmSection;//농장동 고유 번호(FK)

	/************************************************************/
	/* DTO -> Entity */	
	public static ChickDisposal toEntity(ChickDisposalDto chickDisposalDto) {
	    FarmSection farmSection = FarmSection.builder()
	            .farmSectionNo(chickDisposalDto.getFarmSectionNo())
	            .build();

	    return ChickDisposal.builder()
	            .chickDisposalNumber(chickDisposalDto.getChickDisposalNumber())
	            .chickDisposalDate(chickDisposalDto.getChickDisposalDate())
	            .chickDisposalCreateAt(chickDisposalDto.getChickDisposalCreateAt())
	            .farmSection(farmSection)
	            .build();
	}
}
