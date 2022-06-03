//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Customer;

public class MedicalHistory {
	private boolean cureComplete;
	private int historyYear;
	private Disease MyDisease;
	private int customer_id;

	public int getCustomer_id() {
		return this.customer_id;
	}

	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}

	public MedicalHistory() {
	}

	public boolean isCureComplete() {
		return this.cureComplete;
	}

	public void setCureComplete(boolean cureComplete) {
		this.cureComplete = cureComplete;
	}

	public int getHistoryYear() {
		return this.historyYear;
	}

	public void setHistoryYear(int historyYear) {
		this.historyYear = historyYear;
	}

	public MedicalHistory.Disease getMyDisease() {
		return this.MyDisease;
	}

	public void setMyDisease(MedicalHistory.Disease myDisease) {
		this.MyDisease = myDisease;
	}

	public enum Disease {
		암(false),
		결핵(true),
		수두(true),
		홍역(true),
		없음(true);

		private boolean pass;

		private Disease(boolean pass) {
			this.pass = pass;
		}

		public boolean isPass() {
			return this.pass;
		}
	}
}
