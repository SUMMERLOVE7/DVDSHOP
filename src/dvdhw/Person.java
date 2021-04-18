//1976336 장민지

package dvdhw;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * 사람의 이름과 출생년도, 나이를 입력받아 생성자를 이용해 저장하고
 * getter를 이용하여 그 값들을 반환하는 클래스
 * */
public class Person implements Serializable{
	private String name;    //사람 이름
	private int birthyear;  //사람 출생년도
	private int age;        //사람 나이
	
	public Person(String name, int birthyear){
		this.name = name;     //사람 이름 설정
		this.birthyear = birthyear;   //사람 생일 설정
		
		LocalDate today = LocalDate.now(); //현재 날짜 정보 출력
		age = today.getYear() - birthyear;    //나이계산
	}
	
	public Person(String name) {  //String을 매개변수로 갖는 생성자
		this.name = name;
		this.birthyear = 0; //0으로 초기화
		age = 0;   //0으로 초기화
	}

	public String getName() { //이름 반환하는 getter
		return name;
	}

	public int getBirthyear() {   //태어난 해 반환하는 getter
		return birthyear;
	}

	public int getAge() {    //나이 반환하는 getter
		return age;
	}

	@Override
	public String toString() {    //이름과 나이를 문자열로 출력
		return "이름:" + name + " 나이:" + age + " ";
	}
	
}
