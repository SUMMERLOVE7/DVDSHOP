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
 * RentDvdŸ���� DataMgtŬ������ ��ӹ޴� Ŭ���� ����
 * Dvd�� �̸��� isbn�� �̿��Ͽ� �뿩�� �ϰ� ������ �ϴ� Ŭ����
 * */
public class RentDvdDataMgt extends DataMgt<RentDvd> implements Serializable{
	private Vector<RentDvd> collect;    //RentDvd�� �����ϴ� �÷��� ����
	
	//���� Ŭ������ getCollect()�κ��� ������ ���� collect�� ����.
	public RentDvdDataMgt() {
		collect = getCollect();  
	}
	
	//�̸����� rentdvd�� �����ϴ� �޼ҵ�
	@Override
	public RentDvd selectByName(String name) {  
		
		for(RentDvd obj : collect) {  //RentDvd obj�� collect�� element�� ���������� �ִ´�.
			Dvd dvd = obj.getDvd();  // dvd�� ��ȯ���� obj�� dvd�� ����. 
			
			if(name.equals(dvd.getTitle())) { //�Է¹��� �̸��� dvd�� ����� ������ obj�� ��ȯ�Ѵ�.
				return obj;
			}
		}
		
		return null;
	}

	//id�� rentdvd�� ����
	@Override
	public RentDvd selectById(String id) {  
		
		for(RentDvd obj : collect) { //RendDvd�� obj�� collect�� element�� ���������� �ֱ�
			Dvd dvd = obj.getDvd(); //dvd�� obj.getDvd�޼ҵ�� ��ȯ���� dvd�� �ֱ�
			
			if(id.equals(dvd.getISBN())) { //�Էµ� id�� dvd�� ISBN�� �����ϴٸ� obj��ȯ 
				return obj;
			}
		}
		
		return null;
	}

	//�ݳ��Ϸ�� DVD����� ����
	public boolean delrtnDVD() {
		Iterator<RentDvd> it = collect.iterator(); //iterator ��ü���
		int n = 0; //�ݳ��Ϸ�� ����� ���� count
		
		while(it.hasNext()) { //���� �ݺ����� ���� ��Ұ� �����ϸ� ��� �ݺ�
			
			//iterator��ü ��� obj�� �ֱ�
			RentDvd obj = it.next();
			
			if(obj.isReturn() == true) it.remove();  //�ݳ��Ϸ�� dvd��� ��Ͽ��� �����ϱ�
			n++;
		}
		if(n != 0) return true;  //�ݳ��Ϸ�� ����� ������ 0�� �ƴϸ� true��ȯ
		else return false;       //�׷��������� false��ȯ
	}
	
	//RentDvd ��ü�� "rentdvds.dat"�� �����ϴ� �޼ҵ�. ����ó������.
		public void writeData() throws IOException{
			
			FileOutputStream fileos = null;
			ObjectOutputStream oos = null;
			
			try {
				fileos = new FileOutputStream("rentdvds.dat");
				oos = new ObjectOutputStream(fileos);
				
				oos.writeObject(collect);  //collect�� �ִ� ��ü ����
				
				fileos.close();
				oos.close();
				
				if(oos != null) oos.close();
				
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		//RentDvd ��ü�� "rentdvds.dat"���Ͽ��� �о���� �޼ҵ�. ����ó������.
		public void readData() throws IOException, ClassNotFoundException{
			
			FileInputStream fileis = null;
			ObjectInputStream ois = null;
							
			try {
				
				fileis = new FileInputStream("rentdvds.dat");
				ois = new ObjectInputStream(fileis);
							
				collect = (Vector<RentDvd>)ois.readObject();  //���� ��ü�� �о�ͼ� collect�� ����.
					
				setCollect(collect);  //������ ������ ������� collect�� �ٽ� �������ش�.
				
				ois.close();
				
			}catch(Exception e) {	
				e.getMessage();
			}			
		}
}
