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
 * Dvd타입의 DataMgt클래스를 상속받는 클래스 정의
 * Dvd의 제목과 isbn을 이용하여 Dvd의 존재여부를 확인하고 삭제하는 기능을 갖는 클래스
 * */
public class DvdDataMgt extends DataMgt<Dvd> implements Serializable{ //
	private Vector<Dvd> collect;  //Dvd타입의 제네릭 벡터
	
	public DvdDataMgt() { //기본생성자
		collect = getCollect(); //getCollect메소드로부터 얻은 값을 collect에 넣음
	}
	

	@Override
	public Dvd selectByName(String name) {  //Dvd의 이름을 이용하여 select하는 메소드
	
		for(Dvd obj : collect) {  //Dvd의 obj에 collect의 element를 순차적으로 넣기
			if(name.equals(obj.getTitle())) { //만약 입력된 이름이 obj의 제목과 같다면 obj반환
				return obj;
			}
		}
		
		return null;
	}


	@Override
	public Dvd selectById(String id) { //Dvd의 id를 이용하여 select하는 메소드
		for(Dvd obj : collect) {  //Dvd의 obj에 collect의 element를 순차적으로 넣기
			if(id.equals(obj.getISBN())) { //만약 입력된 id가 obj의 ISBN과 동일하다면 obj반환
				return obj;
			}
		}
		
		return null;
	}

	//주어진 DVD제목과 일치하는 DVD삭제
	public boolean deleteByName(String name) {
		
		//컬렉션의 순차검색을 위한 iterator객체정의
		Iterator<Dvd> it = collect.iterator();
		
		//다음 반복에서 사용될 요소가 있으면 계속 반복
		while(it.hasNext()) { 
			Dvd obj = it.next(); //iterator객체 얻어 obj에 넣기
			
			if(name.equals(obj.getTitle())) { //만약 입력받은 제목과 obj의 제목이 일치한다면 삭제
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	//DVD 객체를 "dvds.dat"에 저장하는 메소드. 예외처리해줌.
		public void writeData() throws IOException{
			
			FileOutputStream fileos = null;
			ObjectOutputStream oos = null;
			
			try {
				fileos = new FileOutputStream("dvds.dat");
				oos = new ObjectOutputStream(fileos);
				
				oos.writeObject(collect);  //collect에 있는 객체 저장
				
				fileos.close();
				oos.close();
				
				if(oos != null) oos.close();
				
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		//Dvd 객체를 "dvds.dat"파일에서 읽어오는 메소드. 예외처리해줌.
		public void readData() throws IOException, ClassNotFoundException{
			
			FileInputStream fileis = null;
			ObjectInputStream ois = null;
							
			try {
				
				fileis = new FileInputStream("dvds.dat");
				ois = new ObjectInputStream(fileis);
							
				collect = (Vector<Dvd>)ois.readObject();  //벡터 객체를 읽어와서 collect에 저장.
					
				setCollect(collect);  //가져온 정보를 기반으로 collect를 다시 설정해준다.
				
				ois.close();
				
			}catch(Exception e) {	
				e.getMessage();
			}			
		}
	
}
