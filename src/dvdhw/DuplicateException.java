//1976336 장민지

package dvdhw;

/*
 * 중복예외처리 클래스
 * 중복예외가 발생하면 아래의 메세지를 반환하여 출력한다.
 * */
public class DuplicateException extends Exception{

	public String getMessage() {
		return "이전에 입력한 값과 중복됩니다.";
	}
}
