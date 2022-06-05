package Controller.ContractService;

import Domain.Customer.Customer;

public interface CalculatePremium {

    public double calculatePremium(Customer customer, int premiumRate);
}
