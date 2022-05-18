package Insurance;

import Insurance.Insurance;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:09
 */
public interface InsuranceList {

	public boolean add(Insurance insurance);

	public boolean delete(int insuranceId);

	public Insurance get(int insuranceId);
	//public void update();


}
