package cherrybro.response;

public class ResponseStatusCode {

    /******************* USERS 메시지 ************************/
    public static final int CREATED_USER_SUCCESS = 2000;
    public static final int READ_USER_SUCCESS = 2001;
    public static final int READ_USER_LIST_SUCCESS = 2002;
    public static final int UPDATE_USER_SUCCESS = 2003;
    public static final int DELETE_USER_SUCCESS = 2004;
    public static final int FIND_USER_ID_SUCCESS = 2005;
    public static final int FIND_USER_PASSWORD_SUCCESS = 2006;
    
    public static final int CREATED_USER_FAIL = 4000;
    public static final int READ_USER_FAIL = 4001;
    public static final int READ_USER_LIST_FAIL = 4002;
    public static final int UPDATE_USER_FAIL = 4003;
    public static final int DELETE_USER_FAIL = 4004;
    public static final int FIND_USER_ID_FAIL = 4005;
    public static final int FIND_USER_PASSWORD_FAIL = 4006;
    
    /******************* FARM 메시지 ************************/
    public static final int CREATED_FARM_SUCCESS = 2010;
    public static final int READ_FARM_SUCCESS = 2011;
    public static final int READ_FARM_LIST_SUCCESS = 2012;
    public static final int UPDATE_FARM_SUCCESS = 2013;
    public static final int DELETE_FARM_SUCCESS = 2014;

    public static final int CREATED_FARM_FAIL = 4010;
    public static final int READ_FARM_FAIL = 4011;
    public static final int READ_FARM_LIST_FAIL = 4012;
    public static final int UPDATE_FARM_FAIL = 4013;
    public static final int DELETE_FARM_FAIL = 4014;

    /******************* FARM_SECTION 메시지 ************************/
    public static final int CREATED_FARM_SECTION_SUCCESS = 2020;
    public static final int READ_FARM_SECTION_SUCCESS = 2021;
    public static final int READ_FARM_SECTION_LIST_SUCCESS = 2022;
    public static final int UPDATE_FARM_SECTION_SUCCESS = 2023;
    public static final int DELETE_FARM_SECTION_SUCCESS = 2024;

    public static final int CREATED_FARM_SECTION_FAIL = 4020;
    public static final int READ_FARM_SECTION_FAIL = 4021;
    public static final int READ_FARM_SECTION_LIST_FAIL = 4022;
    public static final int UPDATE_FARM_SECTION_FAIL = 4023;
    public static final int DELETE_FARM_SECTION_FAIL = 4024;

    /******************* CHICK_ENTRY 메시지 ************************/
    public static final int CREATED_CHICK_ENTRY_SUCCESS = 2030;
    public static final int READ_CHICK_ENTRY_SUCCESS = 2031;
    public static final int READ_CHICK_ENTRY_LIST_SUCCESS = 2032;
    public static final int UPDATE_CHICK_ENTRY_SUCCESS = 2033;
    public static final int DELETE_CHICK_ENTRY_SUCCESS = 2034;
    public static final int READ_CHICK_ENTRY_TOTAL_SUCCESS = 2035;
    public static final int READ_CHICK_ENTRY_BY_FARM_NO_SUCCESS = 2036;
    
    public static final int CREATED_CHICK_ENTRY_FAIL = 4030;
    public static final int READ_CHICK_ENTRY_FAIL = 4031;
    public static final int READ_CHICK_ENTRY_LIST_FAIL = 4032;
    public static final int UPDATE_CHICK_ENTRY_FAIL = 4033;
    public static final int DELETE_CHICK_ENTRY_FAIL = 4034;	
    public static final int READ_CHICK_ENTRY_TOTAL_FAIL = 4035;
    public static final int READ_CHICK_ENTRY_BY_FARM_NO_FAIL = 4036;
    
    /******************* CHICK_DEATH 메시지 ************************/
    public static final int CREATED_CHICK_DEATH_SUCCESS = 2040;
    public static final int READ_CHICK_DEATH_SUCCESS = 2041;
    public static final int READ_CHICK_DEATH_LIST_SUCCESS = 2042;
    public static final int UPDATE_CHICK_DEATH_SUCCESS = 2043;
    public static final int DELETE_CHICK_DEATH_SUCCESS = 2044;
    public static final int READ_CHICK_DEATH_TOTAL_SUCCESS = 2045;

    public static final int CREATED_CHICK_DEATH_FAIL = 4040;
    public static final int READ_CHICK_DEATH_FAIL = 4041;
    public static final int READ_CHICK_DEATH_LIST_FAIL = 4042;
    public static final int UPDATE_CHICK_DEATH_FAIL = 4043;
    public static final int DELETE_CHICK_DEATH_FAIL = 4044;
    public static final int READ_CHICK_DEATH_TOTAL_FAIL = 4045;
    
    /******************* CHICK_DISPOSAL 메시지 ************************/
    public static final int CREATED_CHICK_DISPOSAL_SUCCESS = 2050;
    public static final int READ_CHICK_DISPOSAL_SUCCESS = 2051;
    public static final int READ_CHICK_DISPOSAL_LIST_SUCCESS = 2052;
    public static final int UPDATE_CHICK_DISPOSAL_SUCCESS = 2053;
    public static final int DELETE_CHICK_DISPOSAL_SUCCESS = 2054;
    public static final int READ_CHICK_DISPOSAL_TOTAL_SUCCESS = 2055;
    
    public static final int CREATED_CHICK_DISPOSAL_FAIL = 4050;
    public static final int READ_CHICK_DISPOSAL_FAIL = 4051;
    public static final int READ_CHICK_DISPOSAL_LIST_FAIL = 4052;
    public static final int UPDATE_CHICK_DISPOSAL_FAIL = 4053;
    public static final int DELETE_CHICK_DISPOSAL_FAIL = 4054;
    public static final int READ_CHICK_DISPOSAL_TOTAL_FAIL = 4055;
    
    /******************* 공통 오류 메시지 ************************/
    public static final int INTERNAL_SERVER_ERROR = 5000;
}
