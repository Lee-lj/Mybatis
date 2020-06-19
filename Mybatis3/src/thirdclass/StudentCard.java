package thirdclass;

import java.io.Serializable;

//public class StudentCard implements Serializable{
public class StudentCard implements Serializable{//–Ú¡–ªØStudentCard

	private int cardId;
	private String cardInfo;
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	
	

}
