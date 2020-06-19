package thirdclass;

public class StudentWithCard extends Student{
	private int cardid;
	private String cardInfo;
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public String getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	public  String toString() {
		return super.toString()+this.cardid+";"+this.cardInfo;
	}
}
