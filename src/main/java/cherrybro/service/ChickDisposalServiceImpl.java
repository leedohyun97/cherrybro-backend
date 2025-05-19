package cherrybro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cherrybro.dto.ChickDisposalDto;
import cherrybro.entitiy.ChickDisposal;
import cherrybro.repository.ChickDisposalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChickDisposalServiceImpl implements ChickDisposalService {
	
	private final ChickDisposalRepository chickDisposalRepository;
	
	/* 도사 저장 */
	@Override
	public ChickDisposalDto saveChickDisposal(ChickDisposalDto chickDisposalDto) {
		try {
			
			//DTO -> Entity 변환
			ChickDisposal chickDisposal = ChickDisposal.toEntity(chickDisposalDto);
			
			//Entity객체 저장(농장 저장)
			ChickDisposal saveChickDisposal = chickDisposalRepository.save(chickDisposal);
			
			//Entity -> DTO 변환
			return ChickDisposalDto.toDto(saveChickDisposal);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("도사 저장 중 오류 발생", e);
		}
	}
	
	/* 도사 수정 */
	@Override
	public ChickDisposalDto updateChickDisposal(ChickDisposalDto chickDisposalDto) {
	    try {
	    	//수정할 도사 조회
	        ChickDisposal chickDisposal = chickDisposalRepository.findByChickDisposalNo(chickDisposalDto.getChickDisposalNo())
	                .orElseThrow(() -> new RuntimeException("해당 도사를 찾을 수 없습니다."));

	        //도사 수(마리) 수정
	        chickDisposal.setChickDisposalNumber(chickDisposalDto.getChickDisposalNumber());
	        //도사 일자 수정
	        chickDisposal.setChickDisposalDate(chickDisposalDto.getChickDisposalDate());
	        
	        //수정 실행
	        ChickDisposal updateChickDisposal = chickDisposalRepository.save(chickDisposal);
	        
	        //DTO 변환 후 반환
	        return ChickDisposalDto.toDto(updateChickDisposal);
	        
	    } catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("도사 수정 중 오류 발생", e);
	    }
	}

	/* 도사 삭제 */
	@Override
	public ChickDisposalDto deleteChickDisposalById(Long chickDisposalNo) {
	    try {
	        ChickDisposal disposal = chickDisposalRepository.findByChickDisposalNo(chickDisposalNo)
	                .orElseThrow(() -> new RuntimeException("해당 도사를 찾을 수 없습니다."));

	        chickDisposalRepository.delete(disposal);
	        return ChickDisposalDto.toDto(disposal);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("도사 삭제 중 오류 발생", e);
	    }
	}

	
	/* 도사 조회 */
	@Override
	public ChickDisposalDto findChickDisposalById(Long chickDisposalNo) {
		try {
			//도사 고유 번호로 도사 조회
			ChickDisposal findChickDisposal = chickDisposalRepository.findByChickDisposalNo(chickDisposalNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("해당 도사를 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return ChickDisposalDto.toDto(findChickDisposal);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("도사 조회 중 오류 발생", e);
		}
	}
	
	/* 농장동 고유 번호로 도사 리스트 조회 */
	@Override
	public List<ChickDisposalDto> findChickDisposalByFarmSectionNo(Long farmSectionNo) {
		try {
			//농장동 고유 번호로 도사 리스트 조회
			List<ChickDisposal> chickDisposals = chickDisposalRepository.findAllByFarmSection_FarmSectionNo(farmSectionNo);
			
			//Stream을 사용해 Entity -> DTO 변환 후 반환
			return chickDisposals.stream()
					.map(ChickDisposalDto::toDto)
					.toList();
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장동 고유 번호로 도사 리스트 조회 중 오류 발생", e);
		}
	}

	@Override
	public List<ChickDisposalDto> findAllChickDisposal() {
		try {
			//농장 고유 번호로 도사 리스트 조회
			List<ChickDisposal> chickDisposals = chickDisposalRepository.findAll();
			
			//Stream을 사용해 Entity -> DTO 변환 후 반환
			return chickDisposals.stream()
					.map(ChickDisposalDto::toDto)
					.toList();
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("모든 도사 리스트 조회 중 오류 발생", e);
		}
	}

	@Override
	public Integer getTotalChickDisposalNumberByFarmSectionNo(Long farmSectionNo) {
		try {
			//농장동 번호로 도사 수 누적합 조회
			Integer totalDisposal = chickDisposalRepository.getTotalChickDisposalNumberByFarmSectionNo(farmSectionNo); 
			
			return totalDisposal;
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("도사 수 누적합 조회 중 오류 발생", e);
		}
	}

}
