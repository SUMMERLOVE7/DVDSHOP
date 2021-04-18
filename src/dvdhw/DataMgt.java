//1976336 장민지

package dvdhw;

import java.io.Serializable;
import java.util.Vector;

/*
 * T타입의 추상클래스 DataMgt클래스
 * 컬렉션을 이용하여 여러가지 타입의 정보를 저장하고 반환하여 보여주는 클래스
 */
public abstract class DataMgt<T> implements Serializable{   

	//타입메개변수가 T인 제네릭벡터 생성
	private Vector<T> collect = new Vector<T>();  
	
	///////
	public void setCollect(Vector<T> t) {
		this.collect = t;
	}
	//collect를 반환하는 getter
	public Vector<T> getCollect() {
		return collect;
	}

	//collect에 item을 추가하는 메소드
	public void add(T item) {
		collect.add(item);
	}

	//모든 정보 보여주는 메소드
	public String showAll() { 
		int i = 1;
		
		//기존의 문자열data에 더할 수 있게 해주는 stringbuilder 정의
		StringBuilder strBuilder = new StringBuilder();
		
		for(T c: collect) {  //c에 collect의 element를 순차적으로 넣기
			strBuilder.append("[" + (i++) + "] " + c.toString() + "\n");
		}
		
		return strBuilder.toString();
	}

	//정수를 반환하는 getter
	public T get(int i) {  
		return collect.get(i);
	}
	
	//모든 정보를 지우는 메소드
	public void removeAll() { 
		collect.clear();
	}
	
	//추상메소드
	public abstract T selectByName(String name);  
	public abstract T selectById(String id);
	
	
}
