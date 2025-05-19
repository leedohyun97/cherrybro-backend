package cherrybro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cherrybro.entitiy.ChickDeath;

public interface ChickDeathRepository extends JpaRepository<ChickDeath, Long>{
	
	//도태폐기 고유 번호로 도태폐기 조회
	Optional<ChickDeath> findByChickDeathNo(Long chickDeathNo);
	
	//농장동 고유 번호로 도태폐기 리스트 조회
	List<ChickDeath> findAllByFarmSection_FarmSectionNo(Long farmSectionNo);
	
	// 농장동별 누적 폐사합 구하기
	@Query("SELECT COALESCE"	//SUM 결과가 NULL이면 0으로 반환
			+ "(SUM(c.chickDeathNumber), 0) " //폐사 수를 전부 더하고 NULL이면 0으로 변환
			+ "FROM ChickDeath c "	//ChickDeath 테이블 기준 + alias로 c로 표기
			+ "WHERE c.farmSection.farmSectionNo = :farmSectionNo"	//매개변수로 받은 농장동 번호와 일치하는 데이터만 조회
			)
	Integer getTotalChickDeathNumberByFarmSectionNo(@Param("farmSectionNo") Long farmSectionNo);

}
