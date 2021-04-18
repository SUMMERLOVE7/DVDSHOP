//1976336 장민지

package dvdhw;

import java.io.Serializable;

/*
 * 고객의 정보를 저장하는 Customer 클래스
 * 고객의 아이디, 주소, 전화번호를 입력받아 저장하고 반환한다
 * */
public class Customer extends Person implements Serializable{
	private String loginId;  //고객의 로그인아이디
	private String address;  //고객의 주소
	private String phoneNumber; //고객의 전화번호
	
	//고객의 이름, 태어난 해, 로그인아이디, 주소, 전화번호를 입력받은 생성자
	public Customer(String name, int birthyear, String loginId, String address, String phoneNumber) {
		super(name, birthyear);  //person의 생성자호출

		this.loginId = loginId;   //로그인 아이디 세팅하기
		this.address = address;   //주소 세팅하기
		this.phoneNumber = phoneNumber;  //전화번호 세팅하기
	}

	//주소를 반환하는 getter
	public String getAddress() {  
		return address;
	}

	//로그인아이디를 반환하는 getter
	public String getLoginId() {  
		return loginId;
	}

	//전화번호를 반환하는 getter
	public String getPhoneNumber() {   
		return phoneNumber;
	}

	@Override
	public String toString() {  //상위클래스인 person의 toString()메소드에 추가로 아이디, 주소, 전화번호를 출력한다.
		return super.toString() + "아이디:" + loginId + " 주소:" + address + " 전화번호:" + phoneNumber;
	}
	
}
