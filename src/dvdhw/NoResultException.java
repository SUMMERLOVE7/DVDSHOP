//1976336 �����

package dvdhw;

/*
 * ����� ���� �Ϳ� ���� ����ó��Ŭ����
 * �˻������ ������ �Ʒ� �޼��� ���
 * */
public class NoResultException extends Exception{

	public NoResultException() {}
	
	public String getMessage() {
		return "�˻������ �����ϴ�";
	}
}
