package cherrybro.response;

public class ResponseMessage {

    /******************* USERS 메시지 ************************/
    public static final String CREATED_USER_SUCCESS = "회원 생성 성공";
    public static final String READ_USER_SUCCESS = "회원 조회 성공";
    public static final String READ_USER_LIST_SUCCESS = "회원 목록 조회 성공";
    public static final String UPDATE_USER_SUCCESS = "회원 수정 성공";
    public static final String DELETE_USER_SUCCESS = "회원 삭제 성공";
    public static final String FIND_USER_ID_SUCCESS = "아이디 찾기 성공";
    public static final String FIND_USER_PASSWORD_SUCCESS = "임시 비밀번호 발급 성공";
    
    public static final String CREATED_USER_FAIL = "회원 생성 실패";
    public static final String READ_USER_FAIL = "회원 조회 실패";
    public static final String READ_USER_LIST_FAIL = "회원 목록 조회 실패";
    public static final String UPDATE_USER_FAIL = "회원 수정 실패";
    public static final String DELETE_USER_FAIL = "회원 삭제 실패";
    public static final String FIND_USER_ID_FAIL = "아이디 찾기 실패";
    public static final String FIND_USER_PASSWORD_FAIL = "임시 비밀번호 발급 실패";
    
    /******************* FARM 메시지 ************************/
    public static final String CREATED_FARM_SUCCESS = "농장 생성 성공";
    public static final String READ_FARM_SUCCESS = "농장 조회 성공";
    public static final String READ_FARM_LIST_SUCCESS = "농장 목록 조회 성공";
    public static final String UPDATE_FARM_SUCCESS = "농장 수정 성공";
    public static final String DELETE_FARM_SUCCESS = "농장 삭제 성공";

    public static final String CREATED_FARM_FAIL = "농장 생성 실패";
    public static final String READ_FARM_FAIL = "농장 조회 실패";
    public static final String READ_FARM_LIST_FAIL = "농장 목록 조회 실패";
    public static final String UPDATE_FARM_FAIL = "농장 수정 실패";
    public static final String DELETE_FARM_FAIL = "농장 삭제 실패";

    /******************* FARM_SECTION 메시지 ************************/
    public static final String CREATED_FARM_SECTION_SUCCESS = "농장동 생성 성공";
    public static final String READ_FARM_SECTION_SUCCESS = "농장동 조회 성공";
    public static final String READ_FARM_SECTION_LIST_SUCCESS = "농장동 목록 조회 성공";
    public static final String UPDATE_FARM_SECTION_SUCCESS = "농장동 수정 성공";
    public static final String DELETE_FARM_SECTION_SUCCESS = "농장동 삭제 성공";

    public static final String CREATED_FARM_SECTION_FAIL = "농장동 생성 실패";
    public static final String READ_FARM_SECTION_FAIL = "농장동 조회 실패";
    public static final String READ_FARM_SECTION_LIST_FAIL = "농장동 목록 조회 실패";
    public static final String UPDATE_FARM_SECTION_FAIL = "농장동 수정 실패";
    public static final String DELETE_FARM_SECTION_FAIL = "농장동 삭제 실패";

    /******************* CHICK_ENTRY 메시지 ************************/
    public static final String CREATED_CHICK_ENTRY_SUCCESS = "입추수수 등록 성공";
    public static final String READ_CHICK_ENTRY_SUCCESS = "입추수수 조회 성공";
    public static final String READ_CHICK_ENTRY_LIST_SUCCESS = "입추수수 목록 조회 성공";
    public static final String UPDATE_CHICK_ENTRY_SUCCESS = "입추수수 수정 성공";
    public static final String DELETE_CHICK_ENTRY_SUCCESS = "입추수수 삭제 성공";
    public static final String READ_CHICK_ENTRY_TOTAL_SUCCESS = "입추수수 누적합 조회 성공";
    public static final String READ_CHICK_ENTRY_BY_FARM_NO_SUCCESS = "농장 번호로 입추 내역 조회 성공";
    
    public static final String CREATED_CHICK_ENTRY_FAIL = "입추수수 등록 실패";
    public static final String READ_CHICK_ENTRY_FAIL = "입추수수 조회 실패";
    public static final String READ_CHICK_ENTRY_LIST_FAIL = "입추수수 목록 조회 실패";
    public static final String UPDATE_CHICK_ENTRY_FAIL = "입추수수 수정 실패";
    public static final String DELETE_CHICK_ENTRY_FAIL = "입추수수 삭제 실패";
    public static final String READ_CHICK_ENTRY_TOTAL_FAIL = "입추수수 누적합 조회 실패";
    public static final String READ_CHICK_ENTRY_BY_FARM_NO_FAIL = "농장 번호로 입추 내역 조회 실패";
    
    /******************* CHICK_DEATH 메시지 ************************/
    public static final String CREATED_CHICK_DEATH_SUCCESS = "폐사 등록 성공";
    public static final String READ_CHICK_DEATH_SUCCESS = "폐사 조회 성공";
    public static final String READ_CHICK_DEATH_LIST_SUCCESS = "폐사 목록 조회 성공";
    public static final String UPDATE_CHICK_DEATH_SUCCESS = "폐사 수정 성공";
    public static final String DELETE_CHICK_DEATH_SUCCESS = "폐사 삭제 성공";
    public static final String READ_CHICK_DEATH_TOTAL_SUCCESS = "폐사 수 누적합 조회 성공";
    
    public static final String CREATED_CHICK_DEATH_FAIL = "폐사 등록 실패";
    public static final String READ_CHICK_DEATH_FAIL = "폐사 조회 실패";
    public static final String READ_CHICK_DEATH_LIST_FAIL = "폐사 목록 조회 실패";
    public static final String UPDATE_CHICK_DEATH_FAIL = "폐사 수정 실패";
    public static final String DELETE_CHICK_DEATH_FAIL = "폐사 삭제 실패";
    public static final String READ_CHICK_DEATH_TOTAL_FAIL = "폐사 수 누적합 조회 실패";
    
    /******************* CHICK_DISPOSAL 메시지 ************************/
    public static final String CREATED_CHICK_DISPOSAL_SUCCESS = "도사 등록 성공";
    public static final String READ_CHICK_DISPOSAL_SUCCESS = "도사 조회 성공";
    public static final String READ_CHICK_DISPOSAL_LIST_SUCCESS = "도사 목록 조회 성공";
    public static final String UPDATE_CHICK_DISPOSAL_SUCCESS = "도사 수정 성공";
    public static final String DELETE_CHICK_DISPOSAL_SUCCESS = "도사 삭제 성공";
    public static final String READ_CHICK_DISPOSAL_TOTAL_SUCCESS = "도사 수 누적합 조회 성공";
    
    public static final String CREATED_CHICK_DISPOSAL_FAIL = "도사 등록 실패";
    public static final String READ_CHICK_DISPOSAL_FAIL = "도사 조회 실패";
    public static final String READ_CHICK_DISPOSAL_LIST_FAIL = "도사 목록 조회 실패";
    public static final String UPDATE_CHICK_DISPOSAL_FAIL = "도사 수정 실패";
    public static final String DELETE_CHICK_DISPOSAL_FAIL = "도사 삭제 실패";
    public static final String READ_CHICK_DISPOSAL_TOTAL_FAIL = "도사 수 누적합 조회 실패";
    	
    /******************* 공통 오류 메시지 ************************/
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 오류가 발생했습니다.";
}
