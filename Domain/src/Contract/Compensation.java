package Contract;


import java.util.Date;

public class Compensation {

	private int compensationAmount;
	private Date compensationDate;
	private int id;

	public int getCompensationAmount() {
		return compensationAmount;
	}

	public void setCompensationAmount(int compensationAmount) {
		this.compensationAmount = compensationAmount;
	}

	public Date getCompensationDate() {
		return compensationDate;
	}

	public void setCompensationDate(Date compensationDate) {
		this.compensationDate = compensationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Compensation(){

	}

}
