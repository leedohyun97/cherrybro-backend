package cherrybro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import cherrybro.entitiy.ChickDeath;

public interface ChickDeathRepository extends JpaRepository<ChickDeath, Long>{
	
	//도태폐기 고유 번호로 도태폐기 조회
	Optional<ChickDeath> findByChickDeathNo(Long chickDeathNo);
	
	//농장동 고유 번호로 도태폐기 리스트 조회
	List<ChickDeath> findAllByFarmSection_FarmSectionNo(Long farmSectionNo);
}
