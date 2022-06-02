package Customer;

public class MedicalHistory {
	public enum Disease {
		암(false),
		결핵(true),
		수두(true),
		홍역(true),
		없음(true);

		private boolean pass;

		Disease(boolean pass) {
			this.pass = pass;
		}

		public boolean isPass() {
			return pass;
		}
	}

	private boolean cureComplete;
	private int historyYear;
	private Disease MyDisease;
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

	public Disease getMyDisease() {
		return MyDisease;
	}
	public void setMyDisease(Disease myDisease) {
		this.MyDisease = myDisease;
	}


}
