
package Customer;

import DB.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerListImpl extends DBConnector implements CustomerList {
	ArrayList<Customer> customerList;

	public CustomerListImpl() {
		customerList = new ArrayList<Customer>();
		super.getConnection();
		this.customerList = this.getCustomerList();
	}

	public ArrayList<Customer> getCustomerList() {
		String query = "select * from customer;";
		ResultSet rs = super.retreive(query);
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("customer_id"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				customer.setAccount(rs.getString("account"));
				customer.setJob(Customer.Job.valueOf(rs.getString("job")));
				customer.setName(rs.getString("name"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setSex(rs.getBoolean("sex"));
				customer.setSSN(rs.getString("ssn"));
				customer.setAge(rs.getInt("age"));
				customer.setPay(rs.getBoolean("pay"));
				customer.setJoinDate(rs.getDate("joinDate"));


				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return customers;
	}

	@Override
	public boolean add(Customer customer) {
		String query = "insert into customer values ("
				+ customer.getId() + ",'" + customer.getAddress() + "','" + customer.getEmail()
				+ "','" + customer.getAccount() + "','" + customer.getJob() + "','" + customer.getName() + "','"
				+ customer.getPhoneNumber() + "', " + customer.isSex() + ",'" + customer.getSSN() + "'," + customer.getAge() +", "
				+ customer.isPay() + ", '" + customer.getJoinDate() + "');";
		if(super.create(query)){
			this.customerList = getCustomerList();
			return true;
		}
		return false;
	}

	@Override
	public Customer get(int customerId) {
		for (Customer customer : customerList) {
			if (customer.getId() == customerId) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public boolean update(Customer customer) {
		String query = "update customer set address = '"
				+ customer.getAddress() + "', email = '" + customer.getEmail()
				+ "', account = '" + customer.getAccount() + "', job = '" + customer.getJob() + "', name = '" + customer.getName()
				+ "', phoneNumber = '" + customer.getPhoneNumber() + "', sex = " + customer.isSex() + ", ssn = '" + customer.getSSN()
				+ "', age = " + customer.getAge() + ", pay = " + customer.isPay() + ", joinDate = '" + customer.getJoinDate()
				+ "' where customer_id = " + customer.getId();
		if(super.update(query)){
			this.customerList = getCustomerList();
			return true;
		}
		return false;
	}

	@Override
	public int getSize() {
		return this.customerList.size();
	}


	@Override
	public boolean delete(int customerid) {

		String query = "delete from customer where customer_id=" + customerid;


		if(super.delete(query)){
			this.customerList = this.getCustomerList();
			return true;
		}

		return false;

	}
}