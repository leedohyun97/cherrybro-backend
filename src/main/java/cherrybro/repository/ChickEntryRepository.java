package cherrybro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cherrybro.entitiy.ChickEntry;

public interface ChickEntryRepository extends JpaRepository<ChickEntry, Long>{
	
	//입추수수 고유 번호로 입추수수 조회
	Optional<ChickEntry> findByChickEntryNo(Long chickEntry);
	
	//농장동 고유 번호로 입추수수 리스트 조회
	List<ChickEntry> findAllByFarmSection_FarmSectionNo(Long farmSectionNo);
	
	// 농장동별 누적 입추합 구하기
	@Query("SELECT COALESCE"	//SUM 결과가 NULL이면 0으로 반환
			+ "(SUM(c.chickEntryNumber), 0) " //입추 수를 전부 더하고 NULL이면 0으로 변환
			+ "FROM ChickEntry c "	//ChickEntry 테이블 기준 + alias로 c로 표기
			+ "WHERE c.farmSection.farmSectionNo = :farmSectionNo"	//매개변수로 받은 농장동 번호와 일치하는 데이터만 조회
			)
	Integer getTotalChickEntryNumberByFarmSectionNo(@Param("farmSectionNo") Long farmSectionNo);
	
	//농장 번호로 입추 내역 조회(농장동이 참조하는 농장의 농장번호)
	List<ChickEntry> findByFarmSection_Farm_FarmNo(Long farmNo);

	
}
