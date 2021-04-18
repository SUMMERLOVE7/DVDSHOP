//1976336 �����

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
 * CustomerŸ���� DataMgtŬ������ ��ӹ޴� Ŭ���� ����
 * ���� ���̵�� �̸��� �̿��Ͽ� ���� �ִ��� Ȯ���ϰų� ������ �� �ְ� ���ִ� Ŭ����
 * */

public class CustomerDataMgt extends DataMgt<Customer> implements Serializable{ 
	private Vector<Customer> collect;   //CustomerŸ���� ���׸� ����
	
	public CustomerDataMgt() {	//�⺻ ������
		collect = getCollect(); //getCollect�޼ҵ�κ��� ��ȯ���� ���� collect�� ����
	}
	

	@Override
	public Customer selectByName(String name) {  //Customer�� �̸��� �̿��Ͽ� �����ϴ� �޼ҵ�
	
		for(Customer obj : collect) {  //Customer obj�� collect�� element�� ���������� �ֱ�
			if(name.equals(obj.getName())) {  //�Էµ� �̸��� obj�� name�� �����ϴٸ� obj��ȯ
				return obj;
			}
		}
		
		return null;
	}


	@Override
	public Customer selectById(String id) {   //Customer�� id�� �̿��Ͽ� �����ϴ� �޼ҵ�
		for(Customer obj : collect) {  //Customer obj�� collect�� element�� ���������� �ֱ�
			if(id.equals(obj.getLoginId())) { //�Էµ� id�� obj�� id�� �����ϴٸ� obj��ȯ
				return obj;
			}
		}
		
		return null;
	}
	
	//���� �̸��̳� ���� �޾� �����ϴ� �޼ҵ�
	public boolean deleteByName(String name) {
		
		//�÷����� �����˻��� ���� iterator��ü����
		Iterator<Customer> it = collect.iterator(); 
		int n = 0; //�����Ǵ� ���� ���� count�ϱ� ���� ����
		
		while(it.hasNext()) { //���� �ݺ����� ���� ��Ұ� ������ ��� �ݺ�
			Customer obj = it.next(); //iterator��ü ��� obj�� �ֱ�
			
			//�Էµ� �̸� �Ǵ� ���� ���� �̸� �Ǵ� ���� ��ġ�Ѵٸ� �ش�Ǵ� ���� ��� ����
			if(name.equals(obj.getName()) || obj.getName().startsWith(name) || obj.getName().endsWith(name)) {
				it.remove();
				n++;
			}
		}
		if(n != 0) return true;  //������ ���� ���� 0�� �ƴϸ� true ��ȯ
		else return false;   //�׷��� �ʴٸ� false��ȯ
		
	}
	
	//Customer ��ü�� "customers.dat"�� �����ϴ� �޼ҵ�. ����ó������.
	public void writeData() throws IOException{
		
		FileOutputStream fileos = null;
		ObjectOutputStream oos = null;
		
		try {
			fileos = new FileOutputStream("customers.dat");
			oos = new ObjectOutputStream(fileos);
			
			oos.writeObject(collect);  //collect�� �ִ� ��ü ����
			
			fileos.close();
			oos.close();
			
			if(oos != null) oos.close();
			
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	//Customer ��ü�� "customers.dat"���Ͽ��� �о���� �޼ҵ�. ����ó������.
	public void readData() throws IOException, ClassNotFoundException{
		
		FileInputStream fileis = null;
		ObjectInputStream ois = null;
						
		try {
			
			fileis = new FileInputStream("customers.dat");
			ois = new ObjectInputStream(fileis);
						
			collect = (Vector<Customer>)ois.readObject();  //���� ��ü�� �о�ͼ� collect�� ����.
				
			setCollect(collect);  //������ ������ ������� collect�� �ٽ� �������ش�.
			
			ois.close();
			
		}catch(Exception e) {	
			e.getMessage();
		}			
	}
}
