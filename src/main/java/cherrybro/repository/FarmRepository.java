package cherrybro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import cherrybro.entitiy.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>{
	
	//농장 고유 번호로 농장 조회
	Optional<Farm> findByFarmNo(Long farmNo);
	
	//농장 고유 번호로 농장 조회
	Optional<Farm> findByUsersUsersNo(Long usersNo);
	
}
