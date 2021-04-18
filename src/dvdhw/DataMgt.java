//1976336 �����

package dvdhw;

import java.io.Serializable;
import java.util.Vector;

/*
 * TŸ���� �߻�Ŭ���� DataMgtŬ����
 * �÷����� �̿��Ͽ� �������� Ÿ���� ������ �����ϰ� ��ȯ�Ͽ� �����ִ� Ŭ����
 */
public abstract class DataMgt<T> implements Serializable{   

	//Ÿ�Ըް������� T�� ���׸����� ����
	private Vector<T> collect = new Vector<T>();  
	
	///////
	public void setCollect(Vector<T> t) {
		this.collect = t;
	}
	//collect�� ��ȯ�ϴ� getter
	public Vector<T> getCollect() {
		return collect;
	}

	//collect�� item�� �߰��ϴ� �޼ҵ�
	public void add(T item) {
		collect.add(item);
	}

	//��� ���� �����ִ� �޼ҵ�
	public String showAll() { 
		int i = 1;
		
		//������ ���ڿ�data�� ���� �� �ְ� ���ִ� stringbuilder ����
		StringBuilder strBuilder = new StringBuilder();
		
		for(T c: collect) {  //c�� collect�� element�� ���������� �ֱ�
			strBuilder.append("[" + (i++) + "] " + c.toString() + "\n");
		}
		
		return strBuilder.toString();
	}

	//������ ��ȯ�ϴ� getter
	public T get(int i) {  
		return collect.get(i);
	}
	
	//��� ������ ����� �޼ҵ�
	public void removeAll() { 
		collect.clear();
	}
	
	//�߻�޼ҵ�
	public abstract T selectByName(String name);  
	public abstract T selectById(String id);
	
	
}
