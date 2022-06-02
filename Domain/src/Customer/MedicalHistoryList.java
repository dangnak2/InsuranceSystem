package Customer;

public interface MedicalHistoryList {
  boolean add(MedicalHistory medicalHistory);

  boolean delete(int customerId);

  MedicalHistory get(int customerId);

  boolean update(MedicalHistory medicalHistory);

  int getSize();

}
