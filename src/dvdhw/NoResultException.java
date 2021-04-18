//1976336 장민지

package dvdhw;

/*
 * 결과가 없는 것에 대한 예외처리클래스
 * 검색결과가 없으면 아래 메세지 출력
 * */
public class NoResultException extends Exception{

	public NoResultException() {}
	
	public String getMessage() {
		return "검색결과가 없습니다";
	}
}
