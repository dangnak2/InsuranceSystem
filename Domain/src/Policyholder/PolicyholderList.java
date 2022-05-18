package Policyholder;



/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:10
 */
public interface PolicyholderList {


	public boolean add(Policyholder policyholder);

	public boolean delete(int policyholderId);

	public Policyholder get(int policyholderId);
	//public void update();

}
