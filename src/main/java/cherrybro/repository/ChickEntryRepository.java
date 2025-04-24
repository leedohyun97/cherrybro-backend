package cherrybro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import cherrybro.entitiy.ChickEntry;

public interface ChickEntryRepository extends JpaRepository<ChickEntry, Long>{
	
	//입추수수 고유 번호로 입추수수 조회
	Optional<ChickEntry> findByChickEntryNo(Long chickEntry);
	
	//농장동 고유 번호로 입추수수 리스트 조회
	List<ChickEntry> findAllByFarmSection_FarmSectionNo(Long farmSectionNo);
}
