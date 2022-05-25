package Customer;

import java.util.Scanner;

public class MedicalHistory {

	MedicalHistory medicalHistory;
	Scanner sc = new Scanner(System.in);

	private boolean cureComplete;
	public enum disease{
		결핵,
		수두,
		홍역;
	};
	private int historyYear;
	private disease MyDisease;
	public MedicalHistory(){

	}

	public boolean isCureComplete() {
		return cureComplete;
	}
	public void setCureComplete(boolean cureComplete) {
		this.cureComplete = cureComplete;
	}

	public int getHistoryYear() {
		return historyYear;
	}
	public void setHistoryYear(int historyYear) {
		this.historyYear = historyYear;
	}

	public disease getMyDisease() {
		return MyDisease;
	}
	public void setMyDisease(disease myDisease) {
		this.MyDisease = myDisease;
	}


}
