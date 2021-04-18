//1976336 �����

package dvdhw;

import java.io.Serializable;

//enum�� �̿��Ͽ� DVD�帣 ����
enum Genre{ 
	Action, Comic, Romantic, Drama, ETC
}

/*
 * DVD�⺻ ������ ����ִ� Ŭ����
 * �����ڷ� Dvd��  ISBN, ����, ���ۻ�, ������, �帣�� �Է¹޾� �����ϰ�
 * getter�� �̿��Ͽ� �� �������� ��ȯ�Ѵ�.
 * */
public class Dvd implements Serializable{
	private String ISBN;
	private String title;
	private Person producer;
	private String manufactureContry;
	private Genre genre;
	
	public Dvd(String ISBN, String title, Person producer, String manufactureContry, Genre genre) {
		this.ISBN = ISBN;  //�Էµ� ISBN ����
		this.title = title;  //�Էµ� Title ����
		this.producer = producer; //�Էµ� producer ����
		this.manufactureContry = manufactureContry;  //�Էµ� manufactureCountry ����
		this.genre = genre; //�Էµ� genre ����
	}

	@Override
	public String toString() { //���ڿ��� ���
		return "ISBN:" + ISBN + " ����:" + title + " ������:" + producer.getName() + " ������:"
				+ manufactureContry + " �帣: " + genre;
	}
		
	public String getISBN() { //ISBN�� ��ȯ�ϴ� getter
		return ISBN;
	}

	public String getTitle() { //title�� ��ȯ�ϴ� getter
		return title;
	}

	public Person getProducer() { //producer�� ��ȯ�ϴ� getter
		return producer;
	}

	public String getManufactureContry() { //manufactureCountry�� ��ȯ�ϴ� getter
		return manufactureContry;
	}

	public Genre getGenre() { //genre�� ��ȯ�ϴ� getter
		return genre;
	}

}
