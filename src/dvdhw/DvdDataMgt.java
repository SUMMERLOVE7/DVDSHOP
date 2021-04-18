//1976336 �����

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
 * DvdŸ���� DataMgtŬ������ ��ӹ޴� Ŭ���� ����
 * Dvd�� ����� isbn�� �̿��Ͽ� Dvd�� ���翩�θ� Ȯ���ϰ� �����ϴ� ����� ���� Ŭ����
 * */
public class DvdDataMgt extends DataMgt<Dvd> implements Serializable{ //
	private Vector<Dvd> collect;  //DvdŸ���� ���׸� ����
	
	public DvdDataMgt() { //�⺻������
		collect = getCollect(); //getCollect�޼ҵ�κ��� ���� ���� collect�� ����
	}
	

	@Override
	public Dvd selectByName(String name) {  //Dvd�� �̸��� �̿��Ͽ� select�ϴ� �޼ҵ�
	
		for(Dvd obj : collect) {  //Dvd�� obj�� collect�� element�� ���������� �ֱ�
			if(name.equals(obj.getTitle())) { //���� �Էµ� �̸��� obj�� ����� ���ٸ� obj��ȯ
				return obj;
			}
		}
		
		return null;
	}


	@Override
	public Dvd selectById(String id) { //Dvd�� id�� �̿��Ͽ� select�ϴ� �޼ҵ�
		for(Dvd obj : collect) {  //Dvd�� obj�� collect�� element�� ���������� �ֱ�
			if(id.equals(obj.getISBN())) { //���� �Էµ� id�� obj�� ISBN�� �����ϴٸ� obj��ȯ
				return obj;
			}
		}
		
		return null;
	}

	//�־��� DVD����� ��ġ�ϴ� DVD����
	public boolean deleteByName(String name) {
		
		//�÷����� �����˻��� ���� iterator��ü����
		Iterator<Dvd> it = collect.iterator();
		
		//���� �ݺ����� ���� ��Ұ� ������ ��� �ݺ�
		while(it.hasNext()) { 
			Dvd obj = it.next(); //iterator��ü ��� obj�� �ֱ�
			
			if(name.equals(obj.getTitle())) { //���� �Է¹��� ����� obj�� ������ ��ġ�Ѵٸ� ����
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	//DVD ��ü�� "dvds.dat"�� �����ϴ� �޼ҵ�. ����ó������.
		public void writeData() throws IOException{
			
			FileOutputStream fileos = null;
			ObjectOutputStream oos = null;
			
			try {
				fileos = new FileOutputStream("dvds.dat");
				oos = new ObjectOutputStream(fileos);
				
				oos.writeObject(collect);  //collect�� �ִ� ��ü ����
				
				fileos.close();
				oos.close();
				
				if(oos != null) oos.close();
				
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		//Dvd ��ü�� "dvds.dat"���Ͽ��� �о���� �޼ҵ�. ����ó������.
		public void readData() throws IOException, ClassNotFoundException{
			
			FileInputStream fileis = null;
			ObjectInputStream ois = null;
							
			try {
				
				fileis = new FileInputStream("dvds.dat");
				ois = new ObjectInputStream(fileis);
							
				collect = (Vector<Dvd>)ois.readObject();  //���� ��ü�� �о�ͼ� collect�� ����.
					
				setCollect(collect);  //������ ������ ������� collect�� �ٽ� �������ش�.
				
				ois.close();
				
			}catch(Exception e) {	
				e.getMessage();
			}			
		}
	
}
