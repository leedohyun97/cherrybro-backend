package cherrybro.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cherrybro.entitiy.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	//사용자 고유 번호로 회원 조회
	Optional<Users> findByUsersNo(Long usersNo);
}
