//1976336 장민지

package dvdhw;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

/*
 * Customer타입의 DataMgt클래스를 상속받는 클래스 정의
 * 고객의 아이디와 이름을 이용하여 고객이 있는지 확인하거나 삭제할 수 있게 해주는 클래스
 * */

public class CustomerDataMgt extends DataMgt<Customer> implements Serializable{ 
	private Vector<Customer> collect;   //Customer타입의 제네릭 벡터
	
	public CustomerDataMgt() {	//기본 생성자
		collect = getCollect(); //getCollect메소드로부터 반환받은 값을 collect에 삽입
	}
	

	@Override
	public Customer selectByName(String name) {  //Customer의 이름을 이용하여 선택하는 메소드
	
		for(Customer obj : collect) {  //Customer obj에 collect의 element를 순차적으로 넣기
			if(name.equals(obj.getName())) {  //입력된 이름이 obj의 name과 동일하다면 obj반환
				return obj;
			}
		}
		
		return null;
	}


	@Override
	public Customer selectById(String id) {   //Customer의 id를 이용하여 선택하는 메소드
		for(Customer obj : collect) {  //Customer obj에 collect의 element를 순차적으로 넣기
			if(id.equals(obj.getLoginId())) { //입력된 id가 obj의 id와 동일하다면 obj반환
				return obj;
			}
		}
		
		return null;
	}
	
	//고객의 이름이나 성을 받아 삭제하는 메소드
	public boolean deleteByName(String name) {
		
		//컬렉션의 순차검색을 위한 iterator객체정의
		Iterator<Customer> it = collect.iterator(); 
		int n = 0; //삭제되는 고객의 수를 count하기 위한 변수
		
		while(it.hasNext()) { //다음 반복에서 사용될 요소가 있으면 계속 반복
			Customer obj = it.next(); //iterator객체 얻어 obj에 넣기
			
			//입력된 이름 또는 성이 고객의 이름 또는 성과 일치한다면 해당되는 정보 모두 삭제
			if(name.equals(obj.getName()) || obj.getName().startsWith(name) || obj.getName().endsWith(name)) {
				it.remove();
				n++;
			}
		}
		if(n != 0) return true;  //삭제된 고객의 수가 0이 아니면 true 반환
		else return false;   //그렇지 않다면 false반환
		
	}
	
	//Customer 객체를 "customers.dat"에 저장하는 메소드. 예외처리해줌.
	public void writeData() throws IOException{
		
		FileOutputStream fileos = null;
		ObjectOutputStream oos = null;
		
		try {
			fileos = new FileOutputStream("customers.dat");
			oos = new ObjectOutputStream(fileos);
			
			oos.writeObject(collect);  //collect에 있는 객체 저장
			
			fileos.close();
			oos.close();
			
			if(oos != null) oos.close();
			
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	//Customer 객체를 "customers.dat"파일에서 읽어오는 메소드. 예외처리해줌.
	public void readData() throws IOException, ClassNotFoundException{
		
		FileInputStream fileis = null;
		ObjectInputStream ois = null;
						
		try {
			
			fileis = new FileInputStream("customers.dat");
			ois = new ObjectInputStream(fileis);
						
			collect = (Vector<Customer>)ois.readObject();  //벡터 객체를 읽어와서 collect에 저장.
				
			setCollect(collect);  //가져온 정보를 기반으로 collect를 다시 설정해준다.
			
			ois.close();
			
		}catch(Exception e) {	
			e.getMessage();
		}			
	}
}
