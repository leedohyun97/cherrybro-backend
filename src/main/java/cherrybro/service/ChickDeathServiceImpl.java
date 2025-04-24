package cherrybro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cherrybro.dto.ChickDeathDto;
import cherrybro.entitiy.ChickDeath;
import cherrybro.repository.ChickDeathRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChickDeathServiceImpl implements ChickDeathService {
	
	private final ChickDeathRepository chickDeathRepository;
	
	/* 도태폐기 저장 */
	@Override
	public ChickDeathDto saveChickDeath(ChickDeathDto chickDeathDto) {
		try {
			
			//DTO -> Entity 변환
			ChickDeath chickDeath = ChickDeath.toEntity(chickDeathDto);
			
			//Entity객체 저장(농장 저장)
			ChickDeath saveChickDeath = chickDeathRepository.save(chickDeath);
			
			//Entity -> DTO 변환
			return ChickDeathDto.toDto(saveChickDeath);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("도태폐기 저장 중 오류 발생", e);
		}
	}
	
	/* 도태폐기 수정 */
	@Override
	public ChickDeathDto updateChickDeath(ChickDeathDto chickDeathDto) {
	    try {
	    	//수정할 도태폐기 조회
	        ChickDeath chickDeath = chickDeathRepository.findByChickDeathNo(chickDeathDto.getChickDeathNo())
	                .orElseThrow(() -> new RuntimeException("해당 도태폐기를 찾을 수 없습니다."));

	        //도태폐기 수(마리) 수정
	        chickDeath.setChickDeathNumber(chickDeathDto.getChickDeathNumber());
	        //도태폐기 일자 수정
	        chickDeath.setChickDeathDate(chickDeathDto.getChickDeathDate());
	        
	        //수정 실행
	        ChickDeath updateChickDeath = chickDeathRepository.save(chickDeath);
	        
	        //DTO 변환 후 반환
	        return ChickDeathDto.toDto(updateChickDeath);
	        
	    } catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("도태폐기 수정 중 오류 발생", e);
	    }
	}

	/* 도태폐기 삭제 */
	@Override
	public ChickDeathDto deleteChickDeathById(Long chickDeathNo) {
	    try {
	        ChickDeath death = chickDeathRepository.findByChickDeathNo(chickDeathNo)
	                .orElseThrow(() -> new RuntimeException("해당 도태폐기를 찾을 수 없습니다."));

	        chickDeathRepository.delete(death);
	        return ChickDeathDto.toDto(death);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("도태폐기 삭제 중 오류 발생", e);
	    }
	}

	
	/* 도태폐기 조회 */
	@Override
	public ChickDeathDto findChickDeathById(Long chickDeathNo) {
		try {
			//농장 고유 번호로 농장 조회
			ChickDeath findChickDeath = chickDeathRepository.findByChickDeathNo(chickDeathNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("해당 도태폐기를 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return ChickDeathDto.toDto(findChickDeath);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("도태폐기 조회 중 오류 발생", e);
		}
	}
	
	/* 농장동 고유 번호로 도태폐기 리스트 조회 */
	@Override
	public List<ChickDeathDto> findChickDeathByFarmSectionNo(Long farmSectionNo) {
		try {
			//농장 고유 번호로 도태폐기 리스트 조회
			List<ChickDeath> chickDeaths = chickDeathRepository.findAllByFarmSection_FarmSectionNo(farmSectionNo);
			
			//Stream을 사용해 Entity -> DTO 변환 후 반환
			return chickDeaths.stream()
					.map(ChickDeathDto::toDto)
					.toList();
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장동 고유 번호로 도태폐기 리스트 조회 중 오류 발생", e);
		}
	}

}
