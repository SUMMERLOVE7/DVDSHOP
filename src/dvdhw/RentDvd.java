//1976336 장민지

package dvdhw;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * 고객과 Dvd정보를 입력받고
 * 대여시간과 반납시간을 기록하고 반환하며
 * 대여여부를 알려주는 클래스
 * */
public class RentDvd implements Serializable{
	private Customer customer; 
	private Dvd dvd;
	private LocalDate borrowDate;
	private LocalDate returnDate;
	private boolean isReturn = false;
	
	public RentDvd(Customer customer, Dvd dvd){  //customer과 dvd를 입력받은 생성자
		this.customer = customer;
		this.dvd = dvd; 
		borrowDate = LocalDate.now(); //현재 시간값 borrowDate에 저장
	}
	
	public void setReturn() {
		isReturn = true;  //참으로 초기화
		returnDate = LocalDate.now();  //현재 시간 반환
	}

	public Customer getCustomer() { //customer 반환하는 getter
		return customer;  
	}

	public Dvd getDvd() {  //dvd반환하는 getter
		return dvd;
	}

	public boolean isReturn() {
		return isReturn;  //true, false 
	}

	@Override
	public String toString() {
		String name = customer.getName();  //고객의 이름 가져오기
		String title = dvd.getTitle();  //dvd의 제목 가져오기
		
		String borrow = borrowDate.toString(); //현재 시간 값 문자열로 저장
		
		String rtndate = "대여 중";
		if(returnDate != null) rtndate = returnDate.toString(); //returndate가 존재하면 문자열로 저장
		
		return "고객 이름:" + name + " DVD 제목:" + title + " 대여 날짜:" + borrowDate.toString()
		       + " 반납 날짜:" + rtndate;  
	}
}
