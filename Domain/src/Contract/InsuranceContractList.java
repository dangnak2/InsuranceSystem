package Contract;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ???? 4:51:09
 */
public interface InsuranceContractList {

	public boolean add(InsuranceContract insuranceContract);

	public boolean delete(int insuranceContractId);

	public InsuranceContract get(int insuranceContractId);
	//public void update();

}
