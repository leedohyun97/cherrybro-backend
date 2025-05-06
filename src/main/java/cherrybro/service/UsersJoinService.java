package cherrybro.service;

import cherrybro.dto.UsersDto;
import cherrybro.dto.UsersJoinDto;

public interface UsersJoinService {
	
	//회원가입
	UsersDto saveUser(UsersJoinDto usersJoinDto);

}
