package cherrybro.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cherrybro.dto.UsersDto;
import cherrybro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	//UsersRepository 생성자 주입
public class PrincipalDetailsService implements UserDetailsService {
	
	private final UsersRepository usersRepository;
	
	// 사용자 ID로 DB에서 사용자 정보를 조회하고, UserDetails 구현체로 반환
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//아이디로 사용자 조회, 없으면 예외 처리
		UsersDto findUsers = UsersDto.toDto(
				usersRepository.findByUsersId(username)
				.orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다.")));
		
		return new PrincipalDetails(findUsers);
	}
	
}
