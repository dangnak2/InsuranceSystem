package Domain.Contract;

import java.util.ArrayList;

public interface ContractList {

	public boolean add(Contract contract);

	public boolean delete(int contractId);

	public Contract get(int contractId);

	public boolean update( Contract contract);

	public int getSize();

	public ArrayList<Contract> getContractList();
}
