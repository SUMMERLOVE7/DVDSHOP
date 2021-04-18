//1976336 �����

package dvdhw;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * ����� �̸��� ����⵵, ���̸� �Է¹޾� �����ڸ� �̿��� �����ϰ�
 * getter�� �̿��Ͽ� �� ������ ��ȯ�ϴ� Ŭ����
 * */
public class Person implements Serializable{
	private String name;    //��� �̸�
	private int birthyear;  //��� ����⵵
	private int age;        //��� ����
	
	public Person(String name, int birthyear){
		this.name = name;     //��� �̸� ����
		this.birthyear = birthyear;   //��� ���� ����
		
		LocalDate today = LocalDate.now(); //���� ��¥ ���� ���
		age = today.getYear() - birthyear;    //���̰��
	}
	
	public Person(String name) {  //String�� �Ű������� ���� ������
		this.name = name;
		this.birthyear = 0; //0���� �ʱ�ȭ
		age = 0;   //0���� �ʱ�ȭ
	}

	public String getName() { //�̸� ��ȯ�ϴ� getter
		return name;
	}

	public int getBirthyear() {   //�¾ �� ��ȯ�ϴ� getter
		return birthyear;
	}

	public int getAge() {    //���� ��ȯ�ϴ� getter
		return age;
	}

	@Override
	public String toString() {    //�̸��� ���̸� ���ڿ��� ���
		return "�̸�:" + name + " ����:" + age + " ";
	}
	
}
