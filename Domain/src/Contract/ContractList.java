package Contract;

public interface ContractList {

	public boolean add(Contract contract);

	public boolean delete(int insuranceContractId);

	public Contract get(int insuranceContractId);

	public void update();

	public int getSize();
}
