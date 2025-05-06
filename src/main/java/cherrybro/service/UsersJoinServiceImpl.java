package cherrybro.service;

import java.util.Iterator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherrybro.dto.UsersDto;
import cherrybro.dto.UsersJoinDto;
import cherrybro.entitiy.Farm;
import cherrybro.entitiy.FarmSection;
import cherrybro.entitiy.Users;
import cherrybro.entitiy.role.Role;
import cherrybro.repository.FarmRepository;
import cherrybro.repository.FarmSectionRepository;
import cherrybro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersJoinServiceImpl implements UsersJoinService {
	
	private final UsersRepository usersRepository;
	
	private final FarmRepository farmRepository;
	
	private final FarmSectionRepository farmSectionRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	/* 회원가입 */
	@Override
	@Transactional
	public UsersDto saveUser(UsersJoinDto usersJoinDto) {
			
			Users user = Users.builder()
					.usersName(usersJoinDto.getUsersName())
					.usersId(usersJoinDto.getUsersId())
					.usersPassword(passwordEncoder.encode(usersJoinDto.getUsersPassword()))
					.usersEmail(usersJoinDto.getUsersEmail())
					.usersPhone(usersJoinDto.getUsersPhone())
					.usersRole(Role.ROLE_FARMER)
					.build();
			
			Users saveUser = usersRepository.save(user);
			
			Farm farm = Farm.builder()
					.users(user)
					.farmName(usersJoinDto.getFarmName())
					.build();
			
			farmRepository.save(farm);
			
			for (String farmSectionName : usersJoinDto.getFarmSections()) {
				FarmSection farmSection = FarmSection.builder()
						.farm(farm)
						.farmSectionName(farmSectionName)
						.build();
				farmSectionRepository.save(farmSection);
			}
			
			return UsersDto.toDto(saveUser);
			
	}
	
	
	
}
