package Controller.Sale;

import Domain.Contract.*;
import Domain.Customer.*;
import Domain.Insurance.*;
import Domain.Customer.MedicalHistory.Disease;
import Domain.Customer.Customer.Job;
import Domain.Customer.House.HouseType;
import Domain.Customer.Ship.ShipType;
import Domain.Staff.Staff;
import DAO.ContractDAO.ContractDAO;
import DAO.CustomerDAO.*;
import DAO.InsuranceDAO.InsuranceDAO;
import DAO.StaffDAO.StaffDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Sale {
    private InsuranceDAO insuranceDAO;
    private CustomerDAO customerDAO;
    private ContractDAO contractDAO;
    private StaffDAO staffDAO;
    private MedicalHistoryDAO medicalHistoryDAO;
    private CarDAO carDAO;
    private HouseDAO houseDAO;
    private ShipDAO shipDAO;
    private CalculatePremium calculatePremium;

    public Sale(InsuranceDAO insuranceDAO, CustomerDAO customerDAO, MedicalHistoryDAO medicalHistoryDAO,
                CarDAO carDAO, HouseDAO houseDAO, ShipDAO shipDAO, ContractDAO contractDAO, StaffDAO staffDAO, CalculatePremium calculatePremium) {
        this.insuranceDAO = insuranceDAO;
        this.customerDAO = customerDAO;
        this.contractDAO = contractDAO;
        this.staffDAO = staffDAO;
        this.medicalHistoryDAO = medicalHistoryDAO;
        this.carDAO = carDAO;
        this.houseDAO = houseDAO;
        this.shipDAO = shipDAO;
        this.calculatePremium = calculatePremium;
    }


    //총 고객 수 구하기
    public int totalCustomerCount() {
        return this.customerDAO.getSize();
    }

    //이번 달 가입한 고객 수 구하기
    public int thisMonthCustomerCount() {
        int count = 0;
        Date date = new Date();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(date);

        Calendar customerJoinDate = Calendar.getInstance();
        for (Customer customer : customerDAO.getCustomerList()) {
            customerJoinDate.setTime(customer.getJoinDate());
            if (calendarToday.get(Calendar.YEAR) == customerJoinDate.get(Calendar.YEAR)
                    && calendarToday.get(Calendar.MONTH) == customerJoinDate.get(Calendar.MONTH)) {
                count++;
            }
        }

        return count;
    }

    //미납한 고객 수 구하기
    public int unpaidCustomerCount() {
        int count = 0;
        boolean addCustomer = false;
        for (Customer customer : this.customerDAO.getCustomerList()) {
            addCustomer = false;
            for (Contract contract : this.contractDAO.getContractList()) {
                if (contract.getCustomerId() == customer.getId() && contract.isUnderWrite() && !customer.isPay() && !addCustomer) {
                    count++;
                    addCustomer = true;
                }
            }
        }
        return count;
    }

    //고객List 전달
    public ArrayList<Customer> getTotalCustomer() {
        return this.customerDAO.getCustomerList();
    }

    public ArrayList<Insurance> getJoinInsurances(int customerId) {
        ArrayList<Insurance> joinInsurance = new ArrayList<>();

        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId) {
                joinInsurance.add(this.getInsurance(contract.getInsuranceId()));
            }
        }

        return joinInsurance;
    }

    //고객 정보 수정하기
    public boolean updateCustomer(int customerId, String address, String phoneNum, String email) {
        Customer customer = this.customerDAO.get(customerId);
        if (customer == null) {
            return false;
        }
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNum);
        customer.setEmail(email);

        this.customerDAO.update(customer);
        return true;

//		customer.getMedicalHistory().setMyDisease();

        //update 구현해야 할 수 있음
    }

    //고객 정보 삭제하기
    public boolean deleteCustomer(int customerId) {
        return this.customerDAO.delete(customerId);
    }

    public String getCustomerName(int customerId) {
        Customer customer = this.customerDAO.get(customerId);
        if (customer == null) {
            return null;
        } else {
            return customer.getName();
        }
    }

    public Customer getCustomer(int customerId) {
        Customer customer = this.customerDAO.get(customerId);
        if (customer == null) {
            return null;
        } else {
            return customer;
        }
    }

    //미납한 고객 구하기
    public ArrayList<Customer> getUnpaidCustomer() {
        boolean addCustomer = false;
        ArrayList<Customer> unpaidCustomerList = new ArrayList<>();
        for (Customer customer : this.customerDAO.getCustomerList()) {
            addCustomer = false;
            for (Contract contract : this.contractDAO.getContractList()) {
                if (contract.getCustomerId() == customer.getId() && contract.isUnderWrite() && !customer.isPay() && !addCustomer) {
                    unpaidCustomerList.add(customer);
                    addCustomer = true;
                }
            }
        }
        return unpaidCustomerList;
    }


    //미납한 고객 돈 받기
    public boolean payCustomer(ArrayList<Customer> unpaidCustomerList) {

        if (verifyEmail(unpaidCustomerList)) {
            for (Customer customer : unpaidCustomerList) {
                customer.setPay(true);
                this.customerDAO.update(customer);
            }

            return true;
        } else {
            return false;
        }
    }

    private boolean verifyEmail(ArrayList<Customer> unpaidCustomerList) {
        boolean result = false;

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

            for (Customer customer : unpaidCustomerList) {
                for (Contract contract : this.contractDAO.getContractList()) {
                    if (contract.getCustomerId() == customer.getId() && contract.isUnderWrite()) {
                        message.setRecipient(Message.RecipientType.TO, new InternetAddress(customer.getEmail()));
                        message.setSubject("[전과자들]"+this.insuranceDAO.get(contract.getInsuranceId()).getName()+" 보험 보험료 체납 안내", "UTF-8");
                        message.setText("안녕하세요 전과자들입니다.\n" +
                                "고객님이 가입하신 "+this.insuranceDAO.get(contract.getInsuranceId()).getName()+
                                " 보험의 보험료 "+ contract.getInsurancePrice() +" 원이 정상적으로 입금되지 않았습니다.\n" +
                                "확인 후 입금 부탁드립니다.\n" +
                                "갑사합니다.", "UTF-8");
                        Transport.send(message);

                    }
                }
            }
            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }

    //보험 리스트 전달
    public ArrayList<Insurance> getInsuranceDAO() {
        return this.insuranceDAO.getInsuranceList();
    }


    //보험 이름 찾기
    public String getInsuranceName(int insuranceId) {
        for (Insurance insurance : this.insuranceDAO.getInsuranceList()) {
            if (insurance.getId() == insuranceId) {
                return insurance.getName();
            }
        }

        return null;
    }

    public Insurance getInsurance(int insuranceId) {
        for (Insurance insurance : this.insuranceDAO.getInsuranceList()) {
            if (insurance.getId() == insuranceId) {
                return insurance;
            }
        }
        return null;
    }

    public int getUnPaidAmount(int customerId) {
        int amount = 0;
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId && contract.isUnderWrite() && !customerDAO.get(customerId).isPay()) {
                amount += contract.getInsurancePrice();
            }
        }
        return amount;
    }


    //보험마다 계약한 고객 수 구하기
    public int countContractCustomer(int insuranceId) {
        int count = 0;
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getInsuranceId() == insuranceId && contract.isUnderWrite()) {
                count++;
            }
        }

        return count;
    }


    //가입자 id, 보험 id 입력했을 경우로 유스케이스 수정
    public ArrayList<Contract> findContract(int customerId) {
        ArrayList<Contract> findContracts = new ArrayList<>();
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId) {
                findContracts.add(contract);
            }

        }
        return findContracts;
    }

    //계약 전달
    public Contract getContract(int contractId) {
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getContractId() == contractId) {
                return contract;
            }

        }
        return null;
    }

    //보험 계약 해지
    public boolean cancelContract(int contractId) {
        return this.contractDAO.delete(contractId);
    }

    public Customer joinCustomer(String customerName, String customerSSN, String customerAddress, String customerPhoneNum, String customerEmail, String customerAccount, int customerAge, int customerSex, int customerJob, int customerDisease, int customerHistoryYear, int customerCureComplete) {

        Customer customer = new Customer();
        customer.setId(setCustomerId());
        customer.setName(customerName);
        customer.setSSN(customerSSN);
        customer.setAddress(customerAddress);
        customer.setPhoneNumber(customerPhoneNum);
        customer.setEmail(customerEmail);
        customer.setAccount(customerAccount);
        customer.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));

        if (customerSex == 1) {
            customer.setSex(true);
        } else if (customerSex == 2) {
            customer.setSex(false);
        }

        customer.setJob(Job.values()[customerJob - 1]);
        customer.setAge(customerAge);


        MedicalHistory medicalHistory = new MedicalHistory();

        medicalHistory.setCustomerId(customer.getId());
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

        this.customerDAO.add(customer);
        this.medicalHistoryDAO.add(medicalHistory);

        return customer;
    }

    public int setCustomerId() {
        int customerId = 1;
        while (true) {
            if (customerDAO.get(customerId) == null) {
                return customerId;
            } else {
                customerId++;
            }
        }
    }

    public void setCustomerCar(Customer customer, int carNum, int year, int displacement, int price) {
        Car car = new Car();
        car.setCustomerId(customer.getId());
        car.setCarNum(carNum);
        car.setYear(year);
        car.setDisplacement(displacement);
        car.setPrice(price);

        customer.setCar(car);
        this.carDAO.add(car);
    }

    public void setCustomerHouse(Customer customer, int houseType, int housePrice) {
        House house = new House();
        house.setCustomerId(customer.getId());
        house.setHouseType(HouseType.values()[houseType - 1]);
        house.setPrice(housePrice);

        customer.setHouse(house);

        this.houseDAO.add(house);
    }

    public void setCustomerSea(Customer customer, int shipNum, int year, int price, int shipType) {
        Ship ship = new Ship();
        ship.setCustomerId(customer.getId());
        ship.setShipNum(shipNum);
        ship.setYear(year);
        ship.setPrice(price);
        ship.setShipType(ShipType.values()[shipType - 1]);

        customer.setShip(ship);
        this.shipDAO.add(ship);
    }

    //보험 계약 체결
    //Main에서 Customer를 새로 만들고 전달하는 식으로
    //고객을 먼저 추가하고 가입을 할지 가입 할 때 저장을 할지 정확하게 정해야 함
    //일단 가입 시 고객을 저장하는 버전
    public boolean signContract(int insuranceId, Customer customer, Staff staff) {
        Contract contract = new Contract();
        contract.setContractId(setContractId());
        contract.setSalesId(staff.getId());
        contract.setCustomerId(customer.getId());
        contract.setInsuranceId(insuranceId);
        contract.setPremiumRate(this.insuranceDAO.get(insuranceId).getPremium());
        contract.setInsurancePrice(
                (int) this.calculatePremium.calculatePremium(customer, this.insuranceDAO.get(insuranceId).getPremium()));
        contract.setContractDate(Timestamp.valueOf(LocalDateTime.now()));
        customer.setPay(false);
        staff.setResult(staff.getResult() + 1);

        this.staffDAO.update(staff);
        this.customerDAO.update(customer);
        return this.contractDAO.add(contract);
    }

    public int setContractId() {
        int contractId = 1;
        while (true) {
            if (contractDAO.get(contractId) == null) {
                return contractId;
            } else {
                contractId++;
            }
        }
    }


}
