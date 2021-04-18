//1976336 �����

package dvdhw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/*
 * /�α���, ������, Dvd����, �뿩 �� ��� ��ɵ��� �����ϴ� ����Ŭ����
 * �������, �ߺ�, �޴���ȣ ���� ��� ��� ����ó���� ����Ŭ����
 */
public class DvdShop {
	private static Scanner sc = new Scanner(System.in);

	private static CustomerDataMgt customers = new CustomerDataMgt();
	private static DvdDataMgt dvds = new DvdDataMgt();
	private static RentDvdDataMgt rentDvds = new RentDvdDataMgt();

	public static void main(String[] args) throws NoResultException, DuplicateException {
		
		ManagerData managers = new ManagerData();
		
		System.out.println("-----------------------------");
		System.out.println("���α׷� ������� ���̵�� ��й�ȣ �Է� >> ");
		System.out.println("-----------------------------");
		
		//Ű����� �Ŵ����� ���̵�� ��й�ȣ �Է¹޴´�.
		String id = sc.next();
		String pwd = sc.next();
		
		System.out.println();
		
		//���̵�� ��й�ȣ �Է�Ƚ�� ����
		int count = 1;
		
		//���� �Ŵ����� ���̵�� ��й�ȣ�� ��ġ���� �ʴ´ٸ�
		while(managers.isManager(id, pwd)!= true) {
			
			//�α��� ���� �޼��� ���
			System.out.println("�α��� ����. �Ŵ����� �ƴ�");
			System.out.println();			
			
			if(count==3) break; //�α��� �õ�Ƚ���� 3���̻��̸� ���α׷� ����
			else {              //�׷��� ������ �ٽ� �α��� ������ �Է¹޴´�.
				System.out.println("-----------------------------");
				System.out.println("���α׷� ������� ���̵�� ��й�ȣ �Է� >> ");
				System.out.println("-----------------------------");
				
			    id = sc.next();
			    pwd = sc.next();
			    System.out.println();
			    count++;
			    
			}	
		}
		
		//���� �α��ο� �����ϸ�
		if(managers.isManager(id, pwd) == true) {
			
			//�α��� ���� �޼��� ���
			System.out.println("�α��� ����");
			
			//DvdShop��ü ����
		    DvdShop dvdShop = new DvdShop();
		    try {
		    	if(customers.getCollect()!=null) //���� ������ �����Ѵٸ� data�о����
		    		customers.readData();
		    	if(dvds.getCollect() != null) //dvd������ �����Ѵٸ� data�о����
		    		dvds.readData();
		    	if(rentDvds.getCollect() != null) //dvd�뿩������ �����Ѵٸ� data�о����
		    		rentDvds.readData();
		    	//readCustomer();
		    	dvdShop.start();
		    }catch(Exception e) {
		    	e.getMessage();
		    }
		}	
		
	}
	
	//���� �޼ҵ�
	public void start() throws NoResultException, DuplicateException, IOException {
		decoConsole(true, "=");
		System.out.println("Minji's DVD Shop");  
		decoConsole(false, "=");

		while (true) {
			System.out.println();
			
			int menu = showMenu();
			if (menu == 0) {
				try {
					customers.writeData(); //����� ���� ����������
					dvds.writeData();      //����� dvd����������
					rentDvds.writeData();  //����� dvd�뿩��������
				}catch(Exception e) {
					e.getMessage();
				}
				break; //menu�� 0�̸� ����
			}
			
			try {
			    switch (menu) {
			    case 1: newCustomer(); break;       //�ű԰���
			    case 2: searchCustomer(); break;    //���˻�
			    case 3: customersShowAll(); break;  //��ü���˻�
			    case 4: delCustomer(); break;       //������
			    case 5: newDvd(); break;            //DVD���
			    case 6:	searchDvd(); break;         //DVD�˻�
			    case 7: dvdsShowAll(); break;       //DVD��ü�˻� 
			    case 8: delDvd(); break;            //DVD����
			    case 9: rentDvd(); break;           //DVD�뿩
			    case 10: returnDvd(); break;        //DVD�ݳ�
			    case 11: rentDvdsShowAll(); break;  //�뿩��� ��ü�˻�
			    case 12: delreturn(); break;        //�뿩��� ��ü����
			    }
			    
			}catch(NoResultException e) {  //������� ��� ����ó�� �� �޼��� ���
				System.out.println(e.getMessage());
				System.out.println();
				
			}catch(DuplicateException e) { //����� �ߺ��� ��� ����ó�� �� �޼��� ���
				System.out.println(e.getMessage());
				System.out.println();
	
			}
		}

	}
	
	//�޴� â �����ֱ� �� �����ϱ� �޼ҵ�
	public static int showMenu() {
		int menu = -1;

		while (true) {
			System.out.println("============================= �޴� =============================");
			System.out.println("(0) ���� (1) �ű԰��� (2) ���˻� (3) ��ü���˻� (4) ������");
			System.out.println("_______________________________________________________________");
			System.out.println("(5) DVD ��� (6) DVD �˻� (7) DVD ��ü�˻�  (8) Dvd ����");
			System.out.println("_______________________________________________________________");
			System.out.println("(9) DVD �뿩 (10) DVD �ݳ� (11) �뿩 ��� ��ü�˻� (12) �뿩 ��� ��ü ����");
			System.out.println("_______________________________________________________________");

			System.out.print("�޴��� �����ϼ��� >> ");

			try {				
			    
				//Ű����κ��� �޴��� �Է¹޾� menu�� int������ ����
			    menu = Integer.parseInt(sc.next());
						 
			    if(menu < 0 || menu >12)
			    	throw new NumRangeException(); //�Է¹��� menu���� ������ ��� ��� ����ó��
			    
			    System.out.println();
			
			}catch(NumberFormatException e) { //���ڰ� �ƴ� ��� ����ó��
				System.out.println("�޴� ���� �� ���ڸ� �Է��ؾ��մϴ�.");
				System.out.println();
				
				continue;
				
			}catch(NumRangeException e) { //���ڹ����� ��� ��� ����ó��
				System.out.println("0~12������ ���ڸ� �Է��ϼ���");
				System.out.println();
				
				continue;
			}
			return menu;
		}

	}

	//�ܼ� �ٹ̱� �޼ҵ�
	public static void decoConsole(boolean pre, String deco) {
		if (pre)
			System.out.println(); //�ٹٲ�

		for (int i = 0; i < 30; i++)
			System.out.print(deco); //deco�� 30�� �ݺ��ؼ� ���

		System.out.println();  //�ٹٲ�
		if (!pre)
			System.out.println(); //�ٹٲ�
	}
	
	//(�޴�3)��� ���� ������ ����ϴ� �޼ҵ�
	private void customersShowAll() throws NoResultException {
		String str = customers.showAll(); 
		
		if(str.isEmpty())
			throw new NoResultException();
		
		System.out.println(str);
	}

	//(�޴�7)��� dvd������ ����ϴ� �޼ҵ� 
	private void dvdsShowAll() throws NoResultException {
		String str = dvds.showAll(); 
		
		if(str.isEmpty())   //����� ���� ��� ����ó��
			throw new NoResultException();
		
		System.out.println(str);
	}

	//(�޴�11)�뿩��� ��ü �����ִ� �޼ҵ�
	private void rentDvdsShowAll() throws NoResultException {
		String str = rentDvds.showAll(); 
		
		if(str.isEmpty())   //����� ���� ��� ����ó��
			throw new NoResultException();
		
		System.out.println(str);
	}

	//(�޴�2)�� �˻� �޼ҵ�
	private void searchCustomer() throws NoResultException {
		System.out.println("�˻��� �� �̸��� �Է��ϼ��� >>");

		String name = sc.next();
		//Ű����� �Է¹��� ���� �̸��� �������� ���� ������ �ִ��� ã��
		Customer cust = customers.selectByName(name); 
		
		if(cust == null) //�������� ���� ��� ���������ٰ� ���
			throw new NoResultException();
			
		else System.out.println("\n���>> " + cust.toString()); //�������� �����ϸ� ���� ���� ���
		
	}
	
	//(�޴�6)Dvd���� �˻�
	private void searchDvd() throws NoResultException {
		System.out.println("�˻��� Dvd ������ �Է��ϼ��� >>");

		String title = sc.next();
		Dvd dvd = dvds.selectByName(title); //�Է¹��� dvd������ �̿��� dvd�� �����ϴ��� ã��
		
		if(dvd == null)  //����� ���� ��� ����ó��
			throw new NoResultException();
		
		else System.out.println("\n���>> " + dvd.toString()); //dvd�� �����ϸ� �� ���� ���
		
	}

	//(�޴�1)�ű� ȸ������ �޼ҵ�
	public void newCustomer() throws DuplicateException {
		
		while(true) {
			try {
				System.out.println("�� ������ �Է��ϼ���(�̸�, �������, ���̵�, �ּ�, ��ȭ��ȣ ������ �Է�) >>");

				//Ű����κ��� �Է¹��� ���� ���� ����
				String name = sc.next();
				int year = sc.nextInt();
				String id = sc.next();
				String addr = sc.next();
				String phone = sc.next();

				if(customers.selectById(id)!= null)  //�ߺ��� ��� ����ó��
					throw new DuplicateException();
				else {
					
					//�Է¹��� ������ �������� �ϴ� Customer ��ü ����
					Customer cust = new Customer(name, year, id, addr, phone);
					
					//�� �����Ϳ� ����
					customers.add(cust);
					System.out.println("�ű� ���� ����߽��ϴ�.");
					break;
				}
				
			}catch(DuplicateException e) { //���̵��ߺ�����ó��
					System.out.print("�Է��� ���̵� ");
					System.out.println(e.getMessage());
					sc.nextLine();
					continue;
			}
		}
	}
		
	//(�޴�5)���ο� Dvd���
	public void newDvd() throws DuplicateException {
		
		while(true) {
			try {
				System.out.println("DVD ������ �Է��ϼ���(ISBN, ����, ������, ������, �帣(1.�׼�,2.�ڹ�,3.�θ�ƽ,4.���,5.��Ÿ) >>");
				
				//Ű����κ��� �Է¹��� Dvd���� ����
				String isbn = sc.next();
				String title = sc.next();
				String producer = sc.next();
				String contry = sc.next();
				
				Genre genre = Genre.ETC;
				int num = sc.nextInt(); //�帣 ���� ����
				
				if(num == 1) genre = Genre.Action;
				else if(num == 2) genre = Genre.Comic;
				else if(num == 3) genre = Genre.Romantic;
				else if(num == 4) genre = Genre.Drama;
				else genre = Genre.ETC;

				if (dvds.selectById(isbn) != null)  //�ߺ��� �� ����ó��
						throw new DuplicateException();	
				else {
					//�Է¹��� ������ �������� �ϴ� ���ο� Dvd��ü ���� ����
					Dvd dvd = new Dvd(isbn, title, new Person(producer), contry, genre);
					System.out.println("DVD�� ����߽��ϴ�.");
					dvds.add(dvd);
					break;
				}
				
			}catch(DuplicateException e) { //�ߺ��� ����ó��
					System.out.print("DVD�� ISBN�� ");
					System.out.println(e.getMessage());
					sc.nextLine();
					continue;
			}
		}
	}
	
	//(�޴�4)�� ���� ����
	public void delCustomer() throws NoResultException {
		System.out.println("������ �� �̸��� �Է��ϼ���(�̸� �Ǵ� �� �Է�) >>");
		
		//Ű����� ���� �̸� �Ǵ� ���� �Է¹޾� ����
		String name = sc.next();
			
		//���� ���������� true �Ǵ� false ������ ��ȯ����
		boolean dcm = customers.deleteByName(name);
		
		if(dcm == true)	System.out.println("�������� �����Ǿ����ϴ�.");
		else  //������ ������ ���ٸ� ����ó��
			throw new NoResultException();
	}
	
	//(�޴�8)dvd ���� 
	public void delDvd() throws NoResultException {
		System.out.println("������ Dvd ������ �Է��ϼ��� >>");
		
		//Ű����� ������ dvd������ �Է¹޾� ����
		String title = sc.next();
		
		//dvd���������� true �Ǵ� false ������ ��ȯ����.
		boolean dd = dvds.deleteByName(title);
		
		if(dd == true)	System.out.println("dvd�� �����Ǿ����ϴ�.");
		else    //������ ������ ���ٸ� ����ó��
			throw new NoResultException();
	}
	
	//(�޴�9)dvd �뿩�ϴ� �޼ҵ�
	public void rentDvd() throws DuplicateException, NoResultException {
		
		while(true) {
			
			try {
				System.out.println("�뿩�� ���ϴ� �� ���̵�� �뿩�� DVD�� ISBN�� �Է��ϼ��� >> ");

				//�뿩�ϰ��� �ϴ� ���� dvd�� ISBN �Է¹ޱ�
				String id = sc.next();
				String isbn = sc.next();
				
				
				//�뿩�� ���� ���̵� ���� ��쳪 DVD�� ISBN�� �������� �ʴ°�� ����ó��
				if((customers.selectById(id) == null) || (dvds.selectById(isbn) == null)) 		
					throw new NoResultException();
				
				//
				if(rentDvds.selectById(isbn) != null)   //�ߺ��� ���̸� ����ó��
					throw new DuplicateException();
				
				else {
					//���� ���̵�� dvd�� ISBN�� �����ϴ��� Ȯ��
					Customer cust = customers.selectById(id);
					Dvd dvd = dvds.selectById(isbn);
							
					//�뿩������ ����
					RentDvd rentInfo = new RentDvd(cust, dvd);
					rentDvds.add(rentInfo);			
					System.out.println("�뿩�� �Ϸ�Ǿ����ϴ�.");
					break;
				}
				
			}catch(NoResultException e) {
				
				System.out.print("�Է��� �� ���̵� DVD�� ISBN�� ���� ");
				System.out.println(e.getMessage());
				sc.nextLine();
				continue;
				
			}catch(DuplicateException e) {
				System.out.print("DVD�� ISBN�� ");
				System.out.println(e.getMessage());
				sc.nextLine();
				continue;
			}
		}
	}
	
	//(�޴�10)dvd�ݳ� �޼ҵ�
	public void returnDvd() {
		System.out.println("�ݳ��� DVD�� ISBN�� �Է��ϼ��� >> ");

		//Ű����� �ݳ��� dvd�� ISBN�Է¹ޱ�
		String isbn = sc.next();
		
		//�ش� ISBN�� �����Ѵٸ� �ݳ�����
		RentDvd rentDvd = rentDvds.selectById(isbn);
		rentDvd.setReturn();
		System.out.println("�ݳ��� �Ϸ�Ǿ����ϴ�.");
	}	
	
	//(�޴�12)dvd�ݳ� �Ϸ��� ��� ����
	public void delreturn() {
		
		//�ݳ��Ϸ�� å���� �ִٸ� �ݳ��Ϸ�
		if(rentDvds.delrtnDVD() == true) System.out.println("�ݳ��Ϸ� ����� ���������� �����Ǿ����ϴ�.");
		else System.out.println("������ �ݳ��Ϸ������� �����ϴ�.");
	}
	
}
