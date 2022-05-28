package Control.Sale;

import Customer.Customer;

public interface CalculatePremium {

    public double calculatePremium(Customer customer, int premiumRate);
}
