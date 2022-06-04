package DAO.InsuranceDAO;

import Domain.Insurance.Insurance;

import java.util.ArrayList;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:09
 */
public interface InsuranceDAO {

	public boolean add(Insurance insurance);

	public boolean delete(int insuranceId);

	public boolean update(Insurance insurance);

	public Insurance get(int insuranceId);

	public int getSize();

	public ArrayList<Insurance> getInsuranceList();
}
