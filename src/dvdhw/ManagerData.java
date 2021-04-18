//1976336 장민지

package dvdhw;

import java.util.HashMap;

/*
 * 매니저 정보 관리 프로그램
 * HashMap을 이용하여 매니저의 아이디와 비밀번호와 일치하는지 확인하는 클래스
 * */
public class ManagerData { 

    //HashMap을 이용하여 매니저들의 로그인 아이디와 비밀번호 저장
	HashMap<String, String> logindata = new HashMap<String, String>();
	
	//매니저 두 명의 데이터를 저장하는 기본 생성자
	public ManagerData() {
		logindata.put("admin", "admin1234");
		logindata.put("manager", "manager1234");
	}
	
	//입력받은 아이디와 비밀번호를 이용하여 매니저인지 아닌지 판단하는 메소드
	public boolean isManager(String id, String pwd) {
		if(logindata.containsKey(id) && logindata.containsValue(pwd)) return true;
		else return false;
	}
	
}
