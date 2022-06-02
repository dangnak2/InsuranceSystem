package Control.Sale;

import Auth.MyAuthentication;
import Contract.*;
import Customer.*;
import Insurance.*;
import Customer.MedicalHistory.Disease;
import Customer.Customer.Job;
import Customer.House.HouseType;
import Customer.Ship.ShipType;
import Staff.Staff;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Sale {
    private InsuranceList insuranceList;
    private CustomerList customerList;
    private ContractList contractList;
    private CalculatePremium calculatePremium;
    private int count;

    public Sale(InsuranceList insuranceList, CustomerList customerList, ContractList contractList, CalculatePremium calculatePremium) {
        this.insuranceList = insuranceList;
        this.customerList = customerList;
        this.contractList = contractList;
        this.calculatePremium = calculatePremium;
    }


    //총 고객 수 구하기
    public int totalCustomerCount() {
        return this.customerList.getSize();
    }

    //이번 달 가입한 고객 수 구하기
    public int thisMonthCustomerCount() {
        int count = 0;
        Date date = new Date();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(date);

        Calendar customerJoinDate = Calendar.getInstance();
        if (this.customerList instanceof CustomerListImpl) {
            for (Customer customer : ((CustomerListImpl) this.customerList).getCustomerList()) {
                customerJoinDate.setTime(customer.getJoinDate());
                if (calendarToday.get(Calendar.YEAR) == customerJoinDate.get(Calendar.YEAR)
                        && calendarToday.get(Calendar.MONTH) == customerJoinDate.get(Calendar.MONTH)) {
                    count++;
                }
            }
        }
        return count;
    }

    //미납한 고객 수 구하기
    public int unpaidCustomerCount() {
        int count = 0;
        if (this.customerList instanceof CustomerListImpl) {
            for (Customer customer : ((CustomerListImpl) this.customerList).getCustomerList()) {
                if (!customer.isPay()) {
                    count++;
                }
            }
        }
        return count;
    }

    //고객List 전달
    public ArrayList<Customer> getTotalCustomer() {
        return ((CustomerListImpl) this.customerList).getCustomerList();
    }

    public ArrayList<Insurance> getJoinInsurances(int customerId) {
        ArrayList<Insurance> joinInsurance = new ArrayList<>();
        if (this.contractList instanceof ContractListImpl) {
            for (Contract contract : ((ContractListImpl) this.contractList).getContractList()) {
                if (contract.getCustomerId() == customerId) {
                    joinInsurance.add(this.insuranceList.get(contract.getInsuranceId()));
                }
            }
        }
        return joinInsurance;
    }

    //고객 정보 수정하기
    public boolean updateCustomer(int customerId, String address, String phoneNum, String email) {
        Customer customer = this.customerList.get(customerId);
        if (customer == null) {
            return false;
        }
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNum);
        customer.setEmail(email);

        this.customerList.update(customer);
        return true;

//		customer.getMedicalHistory().setMyDisease();

        //update 구현해야 할 수 있음
    }

    //고객 정보 삭제하기
    public boolean deleteCustomer(int customerId) {
        return this.customerList.delete(customerId);
    }

    public String getCustomerName(int customerId) {
        Customer customer = this.customerList.get(customerId);
        if (customer == null) {
            return null;
        } else {
            return customer.getName();
        }
    }

    public Customer getCustomer(int customerId) {
        Customer customer = this.customerList.get(customerId);
        if (customer == null) {
            return null;
        } else {
            return customer;
        }
    }

    //미납한 고객 구하기
    public ArrayList<Customer> getUnpaidCustomer() {
        ArrayList<Customer> unpaidCustomerList = new ArrayList<>();
        if (this.customerList instanceof CustomerListImpl) {
            for (Customer customer : ((CustomerListImpl) this.customerList).getCustomerList()) {
                if (!customer.isPay()) {
                    unpaidCustomerList.add(customer);
                }
            }
        }
        return unpaidCustomerList;
    }


    //미납한 고객 돈 받기
    public boolean payCustomer(ArrayList<Customer> unpaidCustomerList) {
        ArrayList<String> emailList = new ArrayList<>();
        for (Customer customer : unpaidCustomerList) {
            emailList.add(customer.getEmail());
        }
        if (emailList.isEmpty()) {
            return false;
        }

        if (verifyEmail(emailList)) {
            for (Customer customer : unpaidCustomerList) {
                customer.setPay(true);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyEmail(ArrayList<String> emails) {
        boolean result = false;
        count = 0;

        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.naver.com");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "587");

        Authenticator auth = new MyAuthentication();
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage message = new MimeMessage(session);

        // Compose the message
        try {

            message.setSentDate(new Date());
            InternetAddress from = new InternetAddress();

            from = new InternetAddress("Foreigners<hakgooyeol@naver.com>");
            message.setFrom(from);

            InternetAddress[] addresses = new InternetAddress[emails.size()];

            emails.forEach(email -> {
                try {
                    addresses[count] = new InternetAddress(email);
                    count = count + 1;
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            });

            message.addRecipients(Message.RecipientType.TO, addresses);

            // Subject
            message.setSubject("[전과자들] 보험료 체납 안내", "UTF-8");

            // Text
            message.setText("안녕하세요 전과자들입니다. 고객님의 보험료가 정상적으로 입금되지 않았습니다. 돈 내놔", "UTF-8");

            // send the message
            Transport.send(message);

            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }

    //보험 리스트 전달
    public ArrayList<Insurance> getInsuranceList() {
        if (this.insuranceList instanceof InsuranceListImpl) {
            return ((InsuranceListImpl) this.insuranceList).getInsuranceList();
        }
        return null;
    }


    //보험 이름 찾기
    public String getInsuranceName(int insuranceId) {
        if (this.insuranceList instanceof InsuranceListImpl) {
            for (Insurance insurance : ((InsuranceListImpl) this.insuranceList).getInsuranceList()) {
                if (insurance.getId() == insuranceId) {
                    return insurance.getName();
                }
            }
        }
        return null;
    }

    public Insurance getInsurance(int insuranceId) {
        if (this.insuranceList instanceof InsuranceListImpl) {
            for (Insurance insurance : ((InsuranceListImpl) this.insuranceList).getInsuranceList()) {
                if (insurance.getId() == insuranceId) {
                    return insurance;
                }
            }
        }
        return null;
    }


    //보험마다 계약한 고객 수 구하기
    public int countContractCustomer(int insuranceId) {
        int count = 0;
        if (this.contractList instanceof ContractListImpl) {
            for (Contract contract : ((ContractListImpl) this.contractList).getContractList()) {
                if (contract.getInsuranceId() == insuranceId && contract.isUnderWrite()) {
                    count++;
                }
            }
        }
        return count;
    }


    //가입자 id, 보험 id 입력했을 경우로 유스케이스 수정
    public ArrayList<Contract> findContract(int customerId) {
        ArrayList<Contract> findContracts = new ArrayList<>();
        if (this.contractList instanceof ContractListImpl) {
            for (Contract contract : ((ContractListImpl) this.contractList).getContractList()) {
                if (contract.getCustomerId() == customerId) {
                    findContracts.add(contract);
                }
            }
        }
        return findContracts;
    }

    //계약 전달
    public Contract getContract(int contractId) {
        if (this.contractList instanceof ContractListImpl) {
            for (Contract contract : ((ContractListImpl) this.contractList).getContractList()) {
                if (contract.getContractId() == contractId) {
                    return contract;
                }
            }
        }
        return null;
    }

    //보험 계약 해지
    public boolean cancelContract(int contractId) {
        return this.contractList.delete(contractId);
    }

    public Customer joinCustomer(String customerName, String customerSSN, String customerAddress, String customerPhoneNum, String customerEmail, String customerAccount, int customerAge, int customerSex, int customerJob, int customerDisease, int customerHistoryYear, int customerCureComplete) {

        Customer customer = new Customer();
        customer.setId(this.customerList.getSize() + 1);
        customer.setName(customerName);
        customer.setSSN(customerSSN);
        customer.setAddress(customerAddress);
        customer.setPhoneNumber(customerPhoneNum);
        customer.setEmail(customerEmail);
        customer.setAccount(customerAccount);

        Date date = new Date();
        customer.setJoinDate(date);

        if (customerSex == 1) {
            customer.setSex(true);
        } else if (customerSex == 2) {
            customer.setSex(false);
        }

        customer.setJob(Job.values()[customerJob - 1]);
        customer.setAge(customerAge);


        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setMyDisease(Disease.values()[customerDisease - 1]);
        if (customerDisease != 5) {
            medicalHistory.setHistoryYear(customerHistoryYear);
            if (customerCureComplete == 1) {
                medicalHistory.setCureComplete(true);
            } else {
                medicalHistory.setCureComplete(false);
            }
        }
        customer.setMedicalHistory(medicalHistory);

        this.customerList.add(customer);
        return customer;
    }

    public void setCustomerCar(Customer customer, int carNum, int year, int displacement, int price) {
        Car car = new Car();
        car.setCarNum(carNum);
        car.setYear(year);
        car.setDisplacement(displacement);
        car.setPrice(price);

        customer.setCar(car);
        this.customerList.update(customer);
    }

    public void setCustomerHouse(Customer customer, int houseType, int housePrice) {
        House house = new House();
        house.setHouseType(HouseType.values()[houseType - 1]);
        house.setPrice(housePrice);

        customer.setHouse(house);
        this.customerList.update(customer);
    }

    public void setCustomerSea(Customer customer, int shipNum, int year, int price, int shipType) {
        Ship ship = new Ship();
        ship.setShipNum(shipNum);
        ship.setYear(year);
        ship.setPrice(price);
        ship.setShipType(ShipType.values()[shipType - 1]);

        customer.setShip(ship);
        this.customerList.update(customer);
    }

    //보험 계약 체결
    //Main에서 Customer를 새로 만들고 전달하는 식으로
    //고객을 먼저 추가하고 가입을 할지 가입 할 때 저장을 할지 정확하게 정해야 함
    //일단 가입 시 고객을 저장하는 버전
    public boolean signContract(int insuranceId, Customer customer, Staff staff) {
        this.customerList.add(customer);
        Contract contract = new Contract();
        contract.setContractId(this.contractList.getSize() + 1);
        contract.setCustomerId(customer.getId());
        contract.setInsuranceId(insuranceId);
        contract.setPremiumRate(this.insuranceList.get(insuranceId).getPremium());
        contract.setInsurancePrice(
                (int) this.calculatePremium.calculatePremium(customer, this.insuranceList.get(insuranceId).getPremium()));
        contract.setContractDate(Timestamp.valueOf(LocalDateTime.now()));
        staff.setResult(staff.getResult()+1);
        return this.contractList.add(contract);
    }


}
