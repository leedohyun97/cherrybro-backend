package cherrybro.service;

import java.util.List;

import cherrybro.dto.ChickEntryDto;
import cherrybro.entitiy.ChickEntry;

public interface ChickEntryService {
	
	//입추수수 저장
	ChickEntryDto saveChickEntry(ChickEntryDto chickEntryDto);
	
	//입추수수 수정
	ChickEntryDto updateChickEntry(ChickEntryDto chickEntryDto);
	
	//입추수수 기록 삭제
	ChickEntryDto deleteChickEntryById(Long chickEntryNo);
	
	//입추수수 조회
	ChickEntryDto findChickEntryById(Long chickEntryNo);
	
	//농장동 고유 번호로 입추수수 리스트 가져오기
	List<ChickEntryDto> findChickEntriesByFarmSectionNo(Long farmSectionNo);
}
