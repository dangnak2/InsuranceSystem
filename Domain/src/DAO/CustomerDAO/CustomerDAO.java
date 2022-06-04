//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DAO.CustomerDAO;

import Domain.Customer.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
	boolean add(Customer customer);

	boolean delete(int customerId);

	Customer get(int customerId);

	boolean update(Customer customer);

	int getSize();

	public ArrayList<Customer> getCustomerList();
}
