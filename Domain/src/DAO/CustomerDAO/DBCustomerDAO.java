
package DAO.CustomerDAO;

import DAO.DBConnector.DBConnectorDAO;
import Domain.Customer.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCustomerDAO extends DBConnectorDAO implements CustomerDAO {

	private MedicalHistoryDAO medicalHistoryDAO;
	private CarDAO carDAO;
	private HouseDAO houseDAO;
	private ShipDAO shipDAO;


	public DBCustomerDAO(MedicalHistoryDAO medicalHistoryDAO, CarDAO carDAO, HouseDAO houseDAO, ShipDAO shipDAO) {
		this.medicalHistoryDAO = medicalHistoryDAO;
		this.carDAO = carDAO;
		this.houseDAO = houseDAO;
		this.shipDAO = shipDAO;

		super.getConnection();

	}

	public ArrayList<Customer> getCustomerList() {
		String query = "select * from customer;";
		ResultSet rs = super.retrieve(query);
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

				MedicalHistory medicalHistory = medicalHistoryDAO.get(customer.getId());
				if (medicalHistory != null) {
					customer.setMedicalHistory(medicalHistory);
				}
				Car car = carDAO.get(customer.getId());
				if (car != null) {
					customer.setCar(car);
				}
				House house = houseDAO.get(customer.getId());
				if (house != null) {
					customer.setHouse(house);
				}
				Ship ship = shipDAO.get(customer.getId());
				if (ship != null) {
					customer.setShip(ship);
				}

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
			return true;
		}
		return false;
	}

	@Override
	public Customer get(int customerId) {
		for (Customer customer : getCustomerList()) {
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
			return true;
		}
		return false;
	}

	@Override
	public int getSize() {
		return getCustomerList().size();
	}


	@Override
	public boolean delete(int customerid) {

		String query = "delete from customer where customer_id=" + customerid;


		if(super.delete(query)){
			return true;
		}

		return false;

	}
}
