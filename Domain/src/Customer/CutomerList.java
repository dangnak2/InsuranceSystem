package Customer;



/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:10
 */
public interface CutomerList {


	public boolean add(Customer policyholder);

	public boolean delete(int policyholderId);

	public Customer get(int policyholderId);
	//public void update();

	public int getSize();
}
