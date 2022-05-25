package Contract;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ???? 4:51:09
 */
public interface ContractList {

	public boolean add(Contract contract);

	public boolean delete(int insuranceContractId);

	public Contract get(int insuranceContractId);
	//public void update();

	public int getSize();
}
