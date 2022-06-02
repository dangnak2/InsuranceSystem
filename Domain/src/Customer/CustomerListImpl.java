package Customer;


import DB.DBConnector;

import java.util.ArrayList;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:10
 */
public class CustomerListImpl extends DBConnector implements CutomerList {//impl이 저장소

    private ArrayList<Customer> customerList;

    public CustomerListImpl() {
        this.customerList = new ArrayList<>();
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public boolean add(Customer customer) {
        if (this.customerList.add(customer)) {
            String query = "INSERT INTO customer VALUES ("
                    + customer.getId() + ",'" + customer.getAddress() + "','" + customer.getEmail()
                    + "','" + customer.getAccount() + "','" + customer.getJob() + "','" + customer.getMedicalHistory() + "','"
                    + customer.getName() + "','" + customer.getPhoneNumber() +  "'," + customer.isSex()
                    + ",'" + customer.getSSN() + "'," + customer.isPay() + ",'" + customer.getJoinDate()
                    + "','" + customer.getHouse() + "','" + customer.getShip() + "','" + customer.getCar() + ");";
            super.add(query);
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(int customerId) {
        if (this.customerList.remove(this.get(customerId))) {
            return true;
        } else
            return false;
    }

    @Override
    public Customer get(int customerId) {
        for (Customer customer : customerList) {
            if (customerId == customer.getId()) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return this.customerList.size();
    }

}
