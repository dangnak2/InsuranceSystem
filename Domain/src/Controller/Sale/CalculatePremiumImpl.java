package Controller.Sale;

import Domain.Customer.Customer;

public class CalculatePremiumImpl implements CalculatePremium {



    public CalculatePremiumImpl() {
    }

    @Override
    public double calculatePremium(Customer customer, int premiumRate) {
        int age = customer.getAge();

        if (customer.isSex()){
            //남자일 경우
            return Math.round((1/age * 90) * premiumRate);

        }

        return Math.round((1/age * 80) * premiumRate);
    }
}
