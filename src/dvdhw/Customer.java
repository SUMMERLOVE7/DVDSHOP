//1976336 �����

package dvdhw;

import java.io.Serializable;

/*
 * ���� ������ �����ϴ� Customer Ŭ����
 * ���� ���̵�, �ּ�, ��ȭ��ȣ�� �Է¹޾� �����ϰ� ��ȯ�Ѵ�
 * */
public class Customer extends Person implements Serializable{
	private String loginId;  //���� �α��ξ��̵�
	private String address;  //���� �ּ�
	private String phoneNumber; //���� ��ȭ��ȣ
	
	//���� �̸�, �¾ ��, �α��ξ��̵�, �ּ�, ��ȭ��ȣ�� �Է¹��� ������
	public Customer(String name, int birthyear, String loginId, String address, String phoneNumber) {
		super(name, birthyear);  //person�� ������ȣ��

		this.loginId = loginId;   //�α��� ���̵� �����ϱ�
		this.address = address;   //�ּ� �����ϱ�
		this.phoneNumber = phoneNumber;  //��ȭ��ȣ �����ϱ�
	}

	//�ּҸ� ��ȯ�ϴ� getter
	public String getAddress() {  
		return address;
	}

	//�α��ξ��̵� ��ȯ�ϴ� getter
	public String getLoginId() {  
		return loginId;
	}

	//��ȭ��ȣ�� ��ȯ�ϴ� getter
	public String getPhoneNumber() {   
		return phoneNumber;
	}

	@Override
	public String toString() {  //����Ŭ������ person�� toString()�޼ҵ忡 �߰��� ���̵�, �ּ�, ��ȭ��ȣ�� ����Ѵ�.
		return super.toString() + "���̵�:" + loginId + " �ּ�:" + address + " ��ȭ��ȣ:" + phoneNumber;
	}
	
}
