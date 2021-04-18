//1976336 장민지

package dvdhw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/*
 * /로그인, 고객관리, Dvd관리, 대여 등 모든 기능들을 실행하는 메인클래스
 * 결과없음, 중복, 메뉴번호 범위 벗어남 모두 예외처리한 메인클래스
 */
public class DvdShop {
	private static Scanner sc = new Scanner(System.in);

	private static CustomerDataMgt customers = new CustomerDataMgt();
	private static DvdDataMgt dvds = new DvdDataMgt();
	private static RentDvdDataMgt rentDvds = new RentDvdDataMgt();

	public static void main(String[] args) throws NoResultException, DuplicateException {
		
		ManagerData managers = new ManagerData();
		
		System.out.println("-----------------------------");
		System.out.println("프로그램 사용자의 아이디와 비밀번호 입력 >> ");
		System.out.println("-----------------------------");
		
		//키보드로 매니저의 아이디와 비밀번호 입력받는다.
		String id = sc.next();
		String pwd = sc.next();
		
		System.out.println();
		
		//아이디와 비밀번호 입력횟수 설정
		int count = 1;
		
		//만약 매니저의 아이디와 비밀번호와 일치하지 않는다면
		while(managers.isManager(id, pwd)!= true) {
			
			//로그인 실패 메세지 출력
			System.out.println("로그인 실패. 매니저가 아님");
			System.out.println();			
			
			if(count==3) break; //로그인 시도횟수가 3번이상이면 프로그램 종료
			else {              //그렇지 않으면 다시 로그인 정보를 입력받는다.
				System.out.println("-----------------------------");
				System.out.println("프로그램 사용자의 아이디와 비밀번호 입력 >> ");
				System.out.println("-----------------------------");
				
			    id = sc.next();
			    pwd = sc.next();
			    System.out.println();
			    count++;
			    
			}	
		}
		
		//만약 로그인에 성공하면
		if(managers.isManager(id, pwd) == true) {
			
			//로그인 성공 메세지 출력
			System.out.println("로그인 성공");
			
			//DvdShop객체 생성
		    DvdShop dvdShop = new DvdShop();
		    try {
		    	if(customers.getCollect()!=null) //고객의 정보가 존재한다면 data읽어오기
		    		customers.readData();
		    	if(dvds.getCollect() != null) //dvd정보가 존재한다면 data읽어오기
		    		dvds.readData();
		    	if(rentDvds.getCollect() != null) //dvd대여정보가 존재한다면 data읽어오기
		    		rentDvds.readData();
		    	//readCustomer();
		    	dvdShop.start();
		    }catch(Exception e) {
		    	e.getMessage();
		    }
		}	
		
	}
	
	//시작 메소드
	public void start() throws NoResultException, DuplicateException, IOException {
		decoConsole(true, "=");
		System.out.println("Minji's DVD Shop");  
		decoConsole(false, "=");

		while (true) {
			System.out.println();
			
			int menu = showMenu();
			if (menu == 0) {
				try {
					customers.writeData(); //종료시 고객의 데이터저장
					dvds.writeData();      //종료시 dvd데이터저장
					rentDvds.writeData();  //종료시 dvd대여정보저장
				}catch(Exception e) {
					e.getMessage();
				}
				break; //menu가 0이면 종료
			}
			
			try {
			    switch (menu) {
			    case 1: newCustomer(); break;       //신규가입
			    case 2: searchCustomer(); break;    //고객검색
			    case 3: customersShowAll(); break;  //전체고객검색
			    case 4: delCustomer(); break;       //고객삭제
			    case 5: newDvd(); break;            //DVD등록
			    case 6:	searchDvd(); break;         //DVD검색
			    case 7: dvdsShowAll(); break;       //DVD전체검색 
			    case 8: delDvd(); break;            //DVD삭제
			    case 9: rentDvd(); break;           //DVD대여
			    case 10: returnDvd(); break;        //DVD반납
			    case 11: rentDvdsShowAll(); break;  //대여목록 전체검색
			    case 12: delreturn(); break;        //대여목록 전체삭제
			    }
			    
			}catch(NoResultException e) {  //결과없는 경우 예외처리 및 메세지 출력
				System.out.println(e.getMessage());
				System.out.println();
				
			}catch(DuplicateException e) { //결과가 중복된 경우 예외처리 및 메세지 출력
				System.out.println(e.getMessage());
				System.out.println();
	
			}
		}

	}
	
	//메뉴 창 보여주기 및 선택하기 메소드
	public static int showMenu() {
		int menu = -1;

		while (true) {
			System.out.println("============================= 메뉴 =============================");
			System.out.println("(0) 종료 (1) 신규가입 (2) 고객검색 (3) 전체고객검색 (4) 고객삭제");
			System.out.println("_______________________________________________________________");
			System.out.println("(5) DVD 등록 (6) DVD 검색 (7) DVD 전체검색  (8) Dvd 삭제");
			System.out.println("_______________________________________________________________");
			System.out.println("(9) DVD 대여 (10) DVD 반납 (11) 대여 목록 전체검색 (12) 대여 목록 전체 삭제");
			System.out.println("_______________________________________________________________");

			System.out.print("메뉴를 선택하세요 >> ");

			try {				
			    
				//키보드로부터 메뉴를 입력받아 menu에 int값으로 저장
			    menu = Integer.parseInt(sc.next());
						 
			    if(menu < 0 || menu >12)
			    	throw new NumRangeException(); //입력받은 menu값이 범위를 벗어난 경우 예외처리
			    
			    System.out.println();
			
			}catch(NumberFormatException e) { //숫자가 아닌 경우 예외처리
				System.out.println("메뉴 선택 시 숫자를 입력해야합니다.");
				System.out.println();
				
				continue;
				
			}catch(NumRangeException e) { //숫자범위가 벗어난 경우 예외처리
				System.out.println("0~12사이의 숫자를 입력하세요");
				System.out.println();
				
				continue;
			}
			return menu;
		}

	}

	//콘솔 꾸미기 메소드
	public static void decoConsole(boolean pre, String deco) {
		if (pre)
			System.out.println(); //줄바꿈

		for (int i = 0; i < 30; i++)
			System.out.print(deco); //deco를 30번 반복해서 출력

		System.out.println();  //줄바꿈
		if (!pre)
			System.out.println(); //줄바꿈
	}
	
	//(메뉴3)모든 고객의 정보를 출력하는 메소드
	private void customersShowAll() throws NoResultException {
		String str = customers.showAll(); 
		
		if(str.isEmpty())
			throw new NoResultException();
		
		System.out.println(str);
	}

	//(메뉴7)모든 dvd정보를 출력하는 메소드 
	private void dvdsShowAll() throws NoResultException {
		String str = dvds.showAll(); 
		
		if(str.isEmpty())   //결과가 없는 경우 예외처리
			throw new NoResultException();
		
		System.out.println(str);
	}

	//(메뉴11)대여목록 전체 보여주는 메소드
	private void rentDvdsShowAll() throws NoResultException {
		String str = rentDvds.showAll(); 
		
		if(str.isEmpty())   //결과가 없는 경우 예외처리
			throw new NoResultException();
		
		System.out.println(str);
	}

	//(메뉴2)고객 검색 메소드
	private void searchCustomer() throws NoResultException {
		System.out.println("검색할 고객 이름을 입력하세요 >>");

		String name = sc.next();
		//키보드로 입력받은 고객의 이름을 바탕으로 고객의 정보가 있는지 찾기
		Customer cust = customers.selectByName(name); 
		
		if(cust == null) //고객정보가 없는 경우 고객정보없다고 출력
			throw new NoResultException();
			
		else System.out.println("\n결과>> " + cust.toString()); //고객정보가 존재하면 고객의 정보 출력
		
	}
	
	//(메뉴6)Dvd정보 검색
	private void searchDvd() throws NoResultException {
		System.out.println("검색할 Dvd 제목을 입력하세요 >>");

		String title = sc.next();
		Dvd dvd = dvds.selectByName(title); //입력받은 dvd제목을 이용해 dvd가 존재하는지 찾기
		
		if(dvd == null)  //결과가 없는 경우 예외처리
			throw new NoResultException();
		
		else System.out.println("\n결과>> " + dvd.toString()); //dvd가 존재하면 그 정보 출력
		
	}

	//(메뉴1)신규 회원가입 메소드
	public void newCustomer() throws DuplicateException {
		
		while(true) {
			try {
				System.out.println("고객 정보를 입력하세요(이름, 생년월일, 아이디, 주소, 전화번호 순으로 입력) >>");

				//키보드로부터 입력받은 고객의 정보 저장
				String name = sc.next();
				int year = sc.nextInt();
				String id = sc.next();
				String addr = sc.next();
				String phone = sc.next();

				if(customers.selectById(id)!= null)  //중복된 경우 예외처리
					throw new DuplicateException();
				else {
					
					//입력받은 정보를 바탕으로 하는 Customer 객체 생성
					Customer cust = new Customer(name, year, id, addr, phone);
					
					//고객 데이터에 저장
					customers.add(cust);
					System.out.println("신규 고객을 등록했습니다.");
					break;
				}
				
			}catch(DuplicateException e) { //아이디중복예외처리
					System.out.print("입력한 아이디가 ");
					System.out.println(e.getMessage());
					sc.nextLine();
					continue;
			}
		}
	}
		
	//(메뉴5)새로운 Dvd등록
	public void newDvd() throws DuplicateException {
		
		while(true) {
			try {
				System.out.println("DVD 정보를 입력하세요(ISBN, 제목, 제작자, 제조국, 장르(1.액션,2.코믹,3.로맨틱,4.드라마,5.기타) >>");
				
				//키보드로부터 입력받은 Dvd정보 저장
				String isbn = sc.next();
				String title = sc.next();
				String producer = sc.next();
				String contry = sc.next();
				
				Genre genre = Genre.ETC;
				int num = sc.nextInt(); //장르 정보 저장
				
				if(num == 1) genre = Genre.Action;
				else if(num == 2) genre = Genre.Comic;
				else if(num == 3) genre = Genre.Romantic;
				else if(num == 4) genre = Genre.Drama;
				else genre = Genre.ETC;

				if (dvds.selectById(isbn) != null)  //중복된 값 예외처리
						throw new DuplicateException();	
				else {
					//입력받은 정보를 바탕으로 하는 새로운 Dvd객체 정보 저장
					Dvd dvd = new Dvd(isbn, title, new Person(producer), contry, genre);
					System.out.println("DVD를 등록했습니다.");
					dvds.add(dvd);
					break;
				}
				
			}catch(DuplicateException e) { //중복값 예외처리
					System.out.print("DVD의 ISBN이 ");
					System.out.println(e.getMessage());
					sc.nextLine();
					continue;
			}
		}
	}
	
	//(메뉴4)고객 정보 삭제
	public void delCustomer() throws NoResultException {
		System.out.println("삭제할 고객 이름을 입력하세요(이름 또는 성 입력) >>");
		
		//키보드로 고객의 이름 또는 성을 입력받아 저장
		String name = sc.next();
			
		//고객의 삭제정보를 true 또는 false 값으로 반환받음
		boolean dcm = customers.deleteByName(name);
		
		if(dcm == true)	System.out.println("고객정보가 삭제되었습니다.");
		else  //삭제할 정보가 없다면 예외처리
			throw new NoResultException();
	}
	
	//(메뉴8)dvd 삭제 
	public void delDvd() throws NoResultException {
		System.out.println("삭제할 Dvd 제목을 입력하세요 >>");
		
		//키보드로 삭제할 dvd제목을 입력받아 저장
		String title = sc.next();
		
		//dvd삭제정보를 true 또는 false 값으로 반환받음.
		boolean dd = dvds.deleteByName(title);
		
		if(dd == true)	System.out.println("dvd가 삭제되었습니다.");
		else    //삭제할 정보가 없다면 예외처리
			throw new NoResultException();
	}
	
	//(메뉴9)dvd 대여하는 메소드
	public void rentDvd() throws DuplicateException, NoResultException {
		
		while(true) {
			
			try {
				System.out.println("대여를 원하는 고객 아이디와 대여할 DVD의 ISBN을 입력하세요 >> ");

				//대여하고자 하는 고객과 dvd의 ISBN 입력받기
				String id = sc.next();
				String isbn = sc.next();
				
				
				//대여시 고객의 아이디가 없는 경우나 DVD의 ISBN이 존재하지 않는경우 예외처리
				if((customers.selectById(id) == null) || (dvds.selectById(isbn) == null)) 		
					throw new NoResultException();
				
				//
				if(rentDvds.selectById(isbn) != null)   //중복된 값이면 예외처리
					throw new DuplicateException();
				
				else {
					//고객의 아이디와 dvd의 ISBN이 존재하는지 확인
					Customer cust = customers.selectById(id);
					Dvd dvd = dvds.selectById(isbn);
							
					//대여정보를 저장
					RentDvd rentInfo = new RentDvd(cust, dvd);
					rentDvds.add(rentInfo);			
					System.out.println("대여가 완료되었습니다.");
					break;
				}
				
			}catch(NoResultException e) {
				
				System.out.print("입력한 고객 아이디나 DVD의 ISBN에 대한 ");
				System.out.println(e.getMessage());
				sc.nextLine();
				continue;
				
			}catch(DuplicateException e) {
				System.out.print("DVD의 ISBN이 ");
				System.out.println(e.getMessage());
				sc.nextLine();
				continue;
			}
		}
	}
	
	//(메뉴10)dvd반납 메소드
	public void returnDvd() {
		System.out.println("반납할 DVD의 ISBN을 입력하세요 >> ");

		//키보드로 반납할 dvd의 ISBN입력받기
		String isbn = sc.next();
		
		//해당 ISBN이 존재한다면 반납성공
		RentDvd rentDvd = rentDvds.selectById(isbn);
		rentDvd.setReturn();
		System.out.println("반납이 완료되었습니다.");
	}	
	
	//(메뉴12)dvd반납 완료한 목록 삭제
	public void delreturn() {
		
		//반납완료된 책들이 있다면 반납완료
		if(rentDvds.delrtnDVD() == true) System.out.println("반납완료 목록이 성공적으로 삭제되었습니다.");
		else System.out.println("삭제할 반납완료정보가 없습니다.");
	}
	
}
