package cherrybro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cherrybro.dto.ChickEntryDto;
import cherrybro.entitiy.ChickEntry;
import cherrybro.repository.ChickEntryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChickEntryServiceImpl implements ChickEntryService {
	
	private final ChickEntryRepository chickEntryRepository;
	
	/* 입추수수 저장 */
	@Override
	public ChickEntryDto saveChickEntry(ChickEntryDto chickEntryDto) {
		try {
			
			//DTO -> Entity 변환
			ChickEntry chickEntry = ChickEntry.toEntity(chickEntryDto);
			
			//Entity객체 저장(농장 저장)
			ChickEntry saveChickEntry = chickEntryRepository.save(chickEntry);
			
			//Entity -> DTO 변환
			return ChickEntryDto.toDto(saveChickEntry);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("입추수수 저장 중 오류 발생", e);
		}
	}
	
	/* 입추수수 수정 */
	@Override
	public ChickEntryDto updateChickEntry(ChickEntryDto chickEntryDto) {
	    try {
	    	//수정할 입추수수 조회
	        ChickEntry chickEntry = chickEntryRepository.findByChickEntryNo(chickEntryDto.getChickEntryNo())
	                .orElseThrow(() -> new RuntimeException("해당 입추수수를 찾을 수 없습니다."));
	        
	        //입추수수(마리) 수정
	        chickEntry.setChickEntryNumber(chickEntryDto.getChickEntryNumber());
	        //입추일자 수정
	        chickEntry.setChickEntryDate(chickEntryDto.getChickEntryDate());
	        
	        //수정 실행
	        ChickEntry updateChickEntry = chickEntryRepository.save(chickEntry);
	        
	        //DTO 변환 후 반환
	        return ChickEntryDto.toDto(updateChickEntry);
	        
	    } catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("입추수수 수정 중 오류 발생", e);
	    }
	}

	/* 입추수수 삭제 */
	@Override
	public ChickEntryDto deleteChickEntryById(Long chickEntryNo) {
	    try {
	    	
	    	//삭제할 입추수수 조회
	        ChickEntry chickEntry = chickEntryRepository.findByChickEntryNo(chickEntryNo)
	                .orElseThrow(() -> new RuntimeException("해당 입추수수를 찾을 수 없습니다."));

	        chickEntryRepository.delete(chickEntry);
	        
	        return ChickEntryDto.toDto(chickEntry);
	        
	    } catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("입추수수 삭제 중 오류 발생", e);
	    }
	}
	
	/* 입추수수 조회 */
	@Override
	public ChickEntryDto findChickEntryById(Long chickEntryNo) {
		try {
			//농장 고유 번호로 농장 조회
			ChickEntry findChickEntry = chickEntryRepository.findByChickEntryNo(chickEntryNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("해당 입추수수를 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return ChickEntryDto.toDto(findChickEntry);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("입추수수 조회 중 오류 발생", e);
		}
	}
	
	/* 농장동 고유 번호로 입추수수 리스트 조회 */
	@Override
	public List<ChickEntryDto> findChickEntriesByFarmSectionNo(Long farmSectionNo) {
		try {
			//농장 고유 번호로 입추수수 리스트 조회
			List<ChickEntry> chickEntrys = chickEntryRepository.findAllByFarmSection_FarmSectionNo(farmSectionNo);
			
			//Stream을 사용해 Entity -> DTO 변환 후 반환
			return chickEntrys.stream()
					.map(ChickEntryDto::toDto)
					.toList();
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장동 고유 번호로 입추수수 리스트 조회 중 오류 발생", e);
		}
	}

}
