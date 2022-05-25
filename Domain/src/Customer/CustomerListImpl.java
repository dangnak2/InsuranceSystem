package Customer;


import java.util.ArrayList;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:10
 */
public class CustomerListImpl implements CutomerList {//impl이 저장소

	private ArrayList<Customer> customerList;

	public CustomerListImpl(){
		this.customerList = new ArrayList<>();
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	@Override
	public boolean add(Customer policyholder) {
		if (this.customerList.add(policyholder)) {
			return true;
		}else
			return false;
	}

	@Override
	public boolean delete(int policyholderId) {
		if (this.customerList.remove(this.get(policyholderId))) {
			return true;
		}else
			return false;
	}

	@Override
	public Customer get(int policyholderId) {
		for (Customer policyholder : customerList) {
			if (policyholderId == policyholder.getId()) {
				return policyholder;
			}
		}
		return null;
	}

	@Override
	public int getSize() {
		return this.customerList.size();
	}

}
