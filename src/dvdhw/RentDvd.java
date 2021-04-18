//1976336 �����

package dvdhw;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * ���� Dvd������ �Է¹ް�
 * �뿩�ð��� �ݳ��ð��� ����ϰ� ��ȯ�ϸ�
 * �뿩���θ� �˷��ִ� Ŭ����
 * */
public class RentDvd implements Serializable{
	private Customer customer; 
	private Dvd dvd;
	private LocalDate borrowDate;
	private LocalDate returnDate;
	private boolean isReturn = false;
	
	public RentDvd(Customer customer, Dvd dvd){  //customer�� dvd�� �Է¹��� ������
		this.customer = customer;
		this.dvd = dvd; 
		borrowDate = LocalDate.now(); //���� �ð��� borrowDate�� ����
	}
	
	public void setReturn() {
		isReturn = true;  //������ �ʱ�ȭ
		returnDate = LocalDate.now();  //���� �ð� ��ȯ
	}

	public Customer getCustomer() { //customer ��ȯ�ϴ� getter
		return customer;  
	}

	public Dvd getDvd() {  //dvd��ȯ�ϴ� getter
		return dvd;
	}

	public boolean isReturn() {
		return isReturn;  //true, false 
	}

	@Override
	public String toString() {
		String name = customer.getName();  //���� �̸� ��������
		String title = dvd.getTitle();  //dvd�� ���� ��������
		
		String borrow = borrowDate.toString(); //���� �ð� �� ���ڿ��� ����
		
		String rtndate = "�뿩 ��";
		if(returnDate != null) rtndate = returnDate.toString(); //returndate�� �����ϸ� ���ڿ��� ����
		
		return "�� �̸�:" + name + " DVD ����:" + title + " �뿩 ��¥:" + borrowDate.toString()
		       + " �ݳ� ��¥:" + rtndate;  
	}
}
