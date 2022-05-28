package Contract;

public interface ContractList {

	public boolean add(Contract contract);

	public boolean delete(int contractId);

	public Contract get(int contractId);

	public void update( Contract contract);

	public int getSize();
}
