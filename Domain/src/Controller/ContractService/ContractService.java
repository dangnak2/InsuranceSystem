package Controller.ContractService;

import DAO.ContractDAO.ContractDAO;
import DAO.CustomerDAO.CustomerDAO;
import DAO.InsuranceDAO.InsuranceDAO;
import Domain.Contract.Contract;
import Domain.Customer.Customer;
import Domain.Insurance.CarInsurance;
import Domain.Insurance.FireInsurance;
import Domain.Insurance.Insurance;
import Domain.Insurance.SeaInsurance;
import Domain.Staff.Staff;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ContractService {
    private ContractDAO contractDAO;
    private InsuranceDAO insuranceDAO;
    private CustomerDAO customerDAO;
    private CalculatePremium calculatePremium;

    public ContractService(ContractDAO contractDAO, InsuranceDAO insuranceDAO, CustomerDAO customerDAO, CalculatePremium calculatePremium) {
        this.contractDAO = contractDAO;
        this.insuranceDAO = insuranceDAO;
        this.customerDAO = customerDAO;
        this.calculatePremium = calculatePremium;
    }


    public ArrayList<Contract> findInsuranceContracts(int customerId) {
        ArrayList<Contract> customerContractList = new ArrayList<>();
        for (Contract contract : this.contractDAO.getContractList()) {
            if (customerId == contract.getCustomerId() && contract.isUnderWrite()) {
                customerContractList.add(contract);
            }
        }
        return customerContractList;
    }

    public Contract getContract(int contractId) {
        for (Contract contract : this.contractDAO.getContractList()) {
            if(contractId == contract.getContractId()) return contract;
        }

        return null;
    }

    //고객이 체결한 계약 찾기
    public ArrayList<Contract> findContract(int customerId) {
        ArrayList<Contract> findContracts = new ArrayList<>();
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId) {
                findContracts.add(contract);
            }

        }
        return findContracts;
    }

    //고객이 체결한 계약들의 보험 찾기
    public ArrayList<Insurance> getJoinInsurances(int customerId) {
        ArrayList<Insurance> joinInsurance = new ArrayList<>();

        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId) {
                joinInsurance.add(this.insuranceDAO.get(contract.getInsuranceId()));
            }
        }

        return joinInsurance;
    }

    //미납한 고개 수
    public int unpaidCustomerCount() {
        int count = 0;
        boolean addCustomer = false;
        for (Customer customer : this.customerDAO.getCustomerList()) {
            addCustomer = false;
            for (Contract contract : this.contractDAO.getContractList()) {
                if (contract.getCustomerId() == customer.getId() && contract.isUnderWrite() && !contract.isPay() && !addCustomer) {
                    count++;
                    addCustomer = true;
                }
            }
        }
        return count;
    }

    public ArrayList<Customer> getUnpaidCustomer() {
        boolean addCustomer = false;
        ArrayList<Customer> unpaidCustomerList = new ArrayList<>();
        for (Customer customer : this.customerDAO.getCustomerList()) {
            addCustomer = false;
            for (Contract contract : this.contractDAO.getContractList()) {
                if (contract.getCustomerId() == customer.getId() && contract.isUnderWrite() && !contract.isPay() && !addCustomer) {
                    unpaidCustomerList.add(customer);
                    addCustomer = true;
                }
            }
        }
        return unpaidCustomerList;
    }

    public ArrayList<Insurance> getUnpaidInsurance(int customerId) {
        ArrayList<Insurance> joinInsurance = new ArrayList<>();

        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId && !contract.isPay()) {
                joinInsurance.add(this.insuranceDAO.get(contract.getInsuranceId()));
            }
        }

        return joinInsurance;
    }


    public int getUnPaidAmount(int customerId) {
        int amount = 0;
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId && contract.isUnderWrite() && !contract.isPay()) {
                amount += contract.getInsurancePrice();
            }
        }
        return amount;
    }


    public boolean payCustomer(ArrayList<Customer> unpaidCustomerList) {
        if (verifyEmail(unpaidCustomerList)) {
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
                    if (contract.getCustomerId() == customer.getId() && contract.isUnderWrite() && !contract.isPay()) {
                        message.setRecipient(Message.RecipientType.TO, new InternetAddress(customer.getEmail()));
                        message.setSubject("[전과자들]"+this.insuranceDAO.get(contract.getInsuranceId()).getName()+" 보험 보험료 체납 안내", "UTF-8");
                        message.setText("안녕하세요 전과자들입니다.\n" +
                                "고객님이 가입하신 "+this.insuranceDAO.get(contract.getInsuranceId()).getName()+
                                " 보험의 보험료 "+ contract.getInsurancePrice() +" 원이 정상적으로 입금되지 않았습니다.\n" +
                                "확인 후 입금 부탁드립니다.\n" +
                                "갑사합니다.", "UTF-8");
                        Transport.send(message);

                        contract.setPay(true);
                        this.contractDAO.update(contract);
                    }
                }
            }
            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }

    //보험마다 계약한 고객 수
    public int countContractCustomer(int insuranceId) {
        int count = 0;
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getInsuranceId() == insuranceId && contract.isUnderWrite()) {
                count++;
            }
        }

        return count;
    }

    public boolean signContract(int insuranceId, Customer customer, int staffId) {
        Contract contract = new Contract();
        contract.setContractId(setContractId());
        contract.setSalesId(staffId);
        contract.setCustomerId(customer.getId());
        contract.setInsuranceId(insuranceId);
        contract.setPremiumRate(this.insuranceDAO.get(insuranceId).getPremium());
        contract.setInsurancePrice(
                (int) this.calculatePremium.calculatePremium(customer, this.insuranceDAO.get(insuranceId).getPremium()));
        contract.setContractDate(Timestamp.valueOf(LocalDateTime.now()));

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

    //고객이 돈을 냈는지?
    public boolean getPaid(int customerId) {
        for (Contract contract : this.contractDAO.getContractList()) {
            if (contract.getCustomerId() == customerId && contract.isUnderWrite() && !contract.isPay()) {
                return false;
            }
        }
        return true;
    }

    //계약 해지
    public boolean cancelContract(int contractId) {
        return this.contractDAO.delete(contractId);
    }









    //보상


    public boolean judgeSubjectIndemnification(int accidentSubjectIndemnification) {
        return Indemnification.AccidentSubjectIndemnification.values()[accidentSubjectIndemnification-1].isJudgment();
    }

    public boolean judgeFireIndemnification(int fireAccidentCauseIndemnification) {
        return Indemnification.FireAccidentCauseIndemnification.values()[fireAccidentCauseIndemnification-1].isJudgment();
    }
    public boolean judgeCarIndemnification(int carAccidentCauseIndemnification) {
        return Indemnification.CarAccidentCauseIndemnification.values()[carAccidentCauseIndemnification-1].isJudgment();

    }
    public boolean judgeSeaIndemnification(int seaAccidentCauseIndemnification) {
        return Indemnification.SeaAccidentCauseIndemnification.values()[seaAccidentCauseIndemnification-1].isJudgment();
    }

    public void compensation(int contractId, int humanDamage, int buildingDamage, int surroundingDamage, int carDamage, int generalDamage, int revenueDamage) {
        Date date = new Date();
        Contract selectContract = this.contractDAO.get(contractId);
        Insurance selectInsurance = this.insuranceDAO.get(selectContract.getInsuranceId());
        Customer customer = this.customerDAO.get(selectContract.getCustomerId());

        double totalPrice = 0;

        if (selectInsurance instanceof FireInsurance) {


            double compensationHumanDamage = (1 + humanDamage / 100) * ((FireInsurance) selectInsurance).getHumanDamageBasicMoney();
            double compensationBuildingDamage = (1 + buildingDamage / 100) * ((FireInsurance) selectInsurance).getBuildingDamageBasicMoney();
            double compensationSurroundingDamage = (1 + surroundingDamage / 100) * ((FireInsurance) selectInsurance).getSurroundingDamageBasicMoney();

            totalPrice = compensationHumanDamage + compensationBuildingDamage +compensationSurroundingDamage;


            if (customer.getHouse().getPrice() > 0 &&
                    customer.getHouse().getPrice() <= 100000000) {
                totalPrice *= 1;
            } else if (customer.getHouse().getPrice() <= 400000000) {
                totalPrice *= 1.2;
            } else if (customer.getHouse().getPrice() <= 700000000) {
                totalPrice *= 1.3;
            } else {
                totalPrice *= 1.4;
            }

            selectContract.setCompensationAmount(totalPrice);

        } else if (selectInsurance instanceof CarInsurance) {


            double compensationHumanDamage = (1 + humanDamage / 100) * ((CarInsurance) selectInsurance).getHumanDamageBasicMoney();
            double compensationAccidentDegree = (1 + carDamage / 100) * ((CarInsurance) selectInsurance).getCarDamageBasicMoney();

            if (customer.getCar().getPrice() > 0 &&
                    customer.getCar().getPrice() <= 30000000) {
                totalPrice *= 1;
            } else if (customer.getCar().getPrice() <= 50000000) {
                totalPrice *= 1.2;
            } else if (customer.getCar().getPrice() <= 70000000) {
                totalPrice *= 1.3;
            } else {
                totalPrice *= 1.4;
            }

            totalPrice += compensationHumanDamage;
            totalPrice += compensationAccidentDegree;

            selectContract.setCompensationAmount(totalPrice);

        } else if (selectInsurance instanceof SeaInsurance) {

            double compensationGeneralDamage = (1 + generalDamage / 100) * ((SeaInsurance) selectInsurance).getGeneralDamageBasicMoney();
            double compensationRevenueDamage = (1 + revenueDamage / 100) * ((SeaInsurance) selectInsurance).getRevenueDamageBasicMoney();


            totalPrice += compensationGeneralDamage;
            totalPrice += compensationRevenueDamage;

            if (customer.getShip().getPrice() > 0 &&
                    customer.getShip().getPrice() < 50000000) {
                totalPrice *= 1;
            } else if (customer.getShip().getPrice() <= 100000000) {
                totalPrice *= 1.2;
            } else if (customer.getShip().getPrice() <= 500000000) {
                totalPrice *= 1.3;
            } else {
                totalPrice *=  1.4;
            }

            selectContract.setCompensationAmount(totalPrice);
        }

        this.contractDAO.update(selectContract);
    }




    public boolean checkDangerJob(Customer customer) {
        return customer.getJob().isDanger();
    }

    public boolean checkAgeDangerJob(Customer customer) {
        if (customer.getAge() > customer.getJob().getMaxAge()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkDisease(Customer customer) {
        return customer.getMedicalHistory().getMyDisease().isPass();
    }

    public void passUnderwrite(Contract contract) {
        contract.setUnderWrite(true);

        this.contractDAO.update(contract);
    }

}
