//1976336 장민지

package dvdhw;

/*
 * 숫자범위가 넘어선 것에 대한 예외처리 클래스
 * */
public class NumRangeException extends Exception{

	public NumRangeException() {}
	
	public NumRangeException(String message) {
		super(message);
	}
}
