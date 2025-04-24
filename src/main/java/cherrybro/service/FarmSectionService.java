package cherrybro.service;

import java.util.List;

import cherrybro.dto.FarmSectionDto;
import cherrybro.entitiy.FarmSection;

public interface FarmSectionService {
	
	//농장동 저장
	FarmSectionDto saveFarmSection(FarmSectionDto farmSectionDto);
	
	//농장동 수정
	FarmSectionDto updateFarmSection(FarmSectionDto farmSectionDto);
	
	//농장동 삭제
	FarmSectionDto deleteFarmSectionById(Long farmSectionNo);
	
	//농장동 가져오기
	FarmSectionDto findFarmSectionById(Long farmSectionNo);
	
	//농장동 리스트 가져오기
	List<FarmSectionDto> findFarmSections();
	
	//사용자 번호로 농장동 리스트 가져오기
	List<FarmSectionDto> findFarmSectionsByFarmNo(Long farmNo);
	
}
