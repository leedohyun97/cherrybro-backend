package cherrybro.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cherrybro.entitiy.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	//사용자 고유 번호로 회원 조회
	Optional<Users> findByUsersNo(Long usersNo);
	
	//사용자 ID로 회원 조회
	Optional<Users> findByUsersId(String usersId);
	
	//사용자 EMAIL로 회원 조회
	Optional<Users> findByUsersEmail(String userEmail);
	
	//사용자 NAME, EMAIL로 회원 조회
	Optional<Users> findByUsersNameAndUsersEmail(String usersName, String userEmail);
}
