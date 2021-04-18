//1976336 장민지

package dvdhw;

import java.io.Serializable;

//enum을 이용하여 DVD장르 열거
enum Genre{ 
	Action, Comic, Romantic, Drama, ETC
}

/*
 * DVD기본 정보를 담고있는 클래스
 * 생성자로 Dvd의  ISBN, 제목, 제작사, 제조국, 장르를 입력받아 저장하고
 * getter를 이용하여 그 정보들을 반환한다.
 * */
public class Dvd implements Serializable{
	private String ISBN;
	private String title;
	private Person producer;
	private String manufactureContry;
	private Genre genre;
	
	public Dvd(String ISBN, String title, Person producer, String manufactureContry, Genre genre) {
		this.ISBN = ISBN;  //입력된 ISBN 세팅
		this.title = title;  //입력된 Title 세팅
		this.producer = producer; //입력된 producer 세팅
		this.manufactureContry = manufactureContry;  //입력된 manufactureCountry 설정
		this.genre = genre; //입력된 genre 세팅
	}

	@Override
	public String toString() { //문자열로 출력
		return "ISBN:" + ISBN + " 제목:" + title + " 제작자:" + producer.getName() + " 제조국:"
				+ manufactureContry + " 장르: " + genre;
	}
		
	public String getISBN() { //ISBN을 반환하는 getter
		return ISBN;
	}

	public String getTitle() { //title을 반환하는 getter
		return title;
	}

	public Person getProducer() { //producer를 반환하는 getter
		return producer;
	}

	public String getManufactureContry() { //manufactureCountry를 반환하는 getter
		return manufactureContry;
	}

	public Genre getGenre() { //genre를 반환하는 getter
		return genre;
	}

}
