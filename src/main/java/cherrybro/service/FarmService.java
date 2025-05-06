package cherrybro.service;

import java.util.List;
import cherrybro.dto.FarmDto;

public interface FarmService {
	
	//농장 저장
	FarmDto saveFarm(FarmDto farmDto);
	
	//농장 수정
	FarmDto updateFarm(FarmDto farmDto);
	
	//농장 삭제
	FarmDto deleteFarmById(Long farmNo);
	
	//농장 가져오기
	FarmDto findFarmById(Long farmNo);
	
	//사용자 고유번호로 농장 가져오기
	FarmDto findFarmByUsersNo(Long usersNo);
	
	//농장 리스트 가져오기
	List<FarmDto> findFarms();
	
	
}
