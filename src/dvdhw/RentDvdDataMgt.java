//1976336 장민지

package dvdhw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

/*
 * RentDvd타입의 DataMgt클래스를 상속받는 클래스 정의
 * Dvd의 이름과 isbn을 이용하여 대여를 하고 삭제를 하는 클래스
 * */
public class RentDvdDataMgt extends DataMgt<RentDvd> implements Serializable{
	private Vector<RentDvd> collect;    //RentDvd를 저장하는 컬렉션 선언
	
	//상위 클래스의 getCollect()로부터 가져온 값을 collect에 넣음.
	public RentDvdDataMgt() {
		collect = getCollect();  
	}
	
	//이름으로 rentdvd를 선택하는 메소드
	@Override
	public RentDvd selectByName(String name) {  
		
		for(RentDvd obj : collect) {  //RentDvd obj에 collect의 element를 순차적으로 넣는다.
			Dvd dvd = obj.getDvd();  // dvd에 반환받은 obj의 dvd를 삽입. 
			
			if(name.equals(dvd.getTitle())) { //입력받은 이름이 dvd의 제목과 같으면 obj를 반환한다.
				return obj;
			}
		}
		
		return null;
	}

	//id로 rentdvd를 선택
	@Override
	public RentDvd selectById(String id) {  
		
		for(RentDvd obj : collect) { //RendDvd의 obj에 collect의 element를 순차적으로 넣기
			Dvd dvd = obj.getDvd(); //dvd에 obj.getDvd메소드로 반환받은 dvd를 넣기
			
			if(id.equals(dvd.getISBN())) { //입력된 id가 dvd의 ISBN과 동일하다면 obj반환 
				return obj;
			}
		}
		
		return null;
	}

	//반납완료된 DVD목록을 삭제
	public boolean delrtnDVD() {
		Iterator<RentDvd> it = collect.iterator(); //iterator 객체얻기
		int n = 0; //반납완료된 목록의 개수 count
		
		while(it.hasNext()) { //다음 반복에서 사용될 요소가 존재하면 계속 반복
			
			//iterator객체 얻어 obj에 넣기
			RentDvd obj = it.next();
			
			if(obj.isReturn() == true) it.remove();  //반납완료된 dvd라면 목록에서 삭제하기
			n++;
		}
		if(n != 0) return true;  //반납완료된 목록의 개수가 0이 아니면 true반환
		else return false;       //그렇지않으면 false반환
	}
	
	//RentDvd 객체를 "rentdvds.dat"에 저장하는 메소드. 예외처리해줌.
		public void writeData() throws IOException{
			
			FileOutputStream fileos = null;
			ObjectOutputStream oos = null;
			
			try {
				fileos = new FileOutputStream("rentdvds.dat");
				oos = new ObjectOutputStream(fileos);
				
				oos.writeObject(collect);  //collect에 있는 객체 저장
				
				fileos.close();
				oos.close();
				
				if(oos != null) oos.close();
				
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		//RentDvd 객체를 "rentdvds.dat"파일에서 읽어오는 메소드. 예외처리해줌.
		public void readData() throws IOException, ClassNotFoundException{
			
			FileInputStream fileis = null;
			ObjectInputStream ois = null;
							
			try {
				
				fileis = new FileInputStream("rentdvds.dat");
				ois = new ObjectInputStream(fileis);
							
				collect = (Vector<RentDvd>)ois.readObject();  //벡터 객체를 읽어와서 collect에 저장.
					
				setCollect(collect);  //가져온 정보를 기반으로 collect를 다시 설정해준다.
				
				ois.close();
				
			}catch(Exception e) {	
				e.getMessage();
			}			
		}
}
