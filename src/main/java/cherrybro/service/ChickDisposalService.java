package cherrybro.service;

import java.util.List;

import cherrybro.dto.ChickDisposalDto;

public interface ChickDisposalService {
	
	//도사 저장
	ChickDisposalDto saveChickDisposal(ChickDisposalDto chickDisposalDto);
	
	//도사 수정
	ChickDisposalDto updateChickDisposal(ChickDisposalDto chickDisposalDto);
	
	//도사 기록 삭제
	ChickDisposalDto deleteChickDisposalById(Long chickDisposalNo);
	
	//도사 조회
	ChickDisposalDto findChickDisposalById(Long chickDisposalNo);
	
	//도사 리스트 가져오기
	List<ChickDisposalDto> findChickDisposalByFarmSectionNo(Long farmSectionNo);
	
	//모든 도사 리스트 가져오기
	List<ChickDisposalDto> findAllChickDisposal();
	
	// 농장동별 누적 도사합 조회, 없으면 0 반환
	Integer getTotalChickDisposalNumberByFarmSectionNo(Long farmSectionNo);
}
