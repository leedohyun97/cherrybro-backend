package cherrybro.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cherrybro.entitiy.FarmSection;

public interface FarmSectionRepository extends JpaRepository<FarmSection, Long>{
	
	//농장동 고유 번호로 농장동 조회
	Optional<FarmSection> findByFarmSectionNo(Long farmSectionNo);
	
	//농장 고유 번호로 농장동 리스트 조회
	List<FarmSection> findAllByFarm_FarmNo(Long farmNo);
	
}
