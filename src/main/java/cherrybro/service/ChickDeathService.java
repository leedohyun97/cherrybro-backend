package cherrybro.service;

import java.util.List;

import cherrybro.dto.ChickDeathDto;
import cherrybro.entitiy.ChickDeath;

public interface ChickDeathService {
	
	//도태폐기 저장
	ChickDeathDto saveChickDeath(ChickDeathDto chickDeathDto);
	
	//도태폐기 수정
	ChickDeathDto updateChickDeath(ChickDeathDto chickDeathDto);
	
	//도태폐기 기록 삭제
	ChickDeathDto deleteChickDeathById(Long chickDeathNo);
	
	//도태폐기 조회
	ChickDeathDto findChickDeathById(Long chickDeathNo);
	
	//도태폐기 리스트 가져오기
	List<ChickDeathDto> findChickDeathByFarmSectionNo(Long farmSectionNo);
	
}
