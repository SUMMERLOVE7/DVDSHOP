//1976336 �����

package dvdhw;

import java.util.HashMap;

/*
 * �Ŵ��� ���� ���� ���α׷�
 * HashMap�� �̿��Ͽ� �Ŵ����� ���̵�� ��й�ȣ�� ��ġ�ϴ��� Ȯ���ϴ� Ŭ����
 * */
public class ManagerData { 

    //HashMap�� �̿��Ͽ� �Ŵ������� �α��� ���̵�� ��й�ȣ ����
	HashMap<String, String> logindata = new HashMap<String, String>();
	
	//�Ŵ��� �� ���� �����͸� �����ϴ� �⺻ ������
	public ManagerData() {
		logindata.put("admin", "admin1234");
		logindata.put("manager", "manager1234");
	}
	
	//�Է¹��� ���̵�� ��й�ȣ�� �̿��Ͽ� �Ŵ������� �ƴ��� �Ǵ��ϴ� �޼ҵ�
	public boolean isManager(String id, String pwd) {
		if(logindata.containsKey(id) && logindata.containsValue(pwd)) return true;
		else return false;
	}
	
}
