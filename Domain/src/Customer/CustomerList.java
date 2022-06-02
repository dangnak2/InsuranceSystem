//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Customer;

public interface CustomerList {
	boolean add(Customer customer);

	boolean delete(int customerId);

	Customer get(int customerId);

	boolean update(Customer customer);

	int getSize();
}
