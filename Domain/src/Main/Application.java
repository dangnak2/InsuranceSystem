package Main;

import Contract.Contract;
import Contract.ContractListImpl;
import Control.CompensationMange.CompensationManage;
import Control.CompensationMange.Indemnification.*;
import Control.Design.Design;
import Control.Sale.CalculatePremiumImpl;
import Control.Sale.CalculatePremium;
import Control.Sale.Sale;
import Control.StaffManage.StaffManagement;
import Control.UnderWrite.UnderWrite;
import Customer.Customer;
import Customer.CustomerListImpl;
import Customer.MedicalHistoryListImpl;
import Customer.CarListImpl;
import Customer.HouseListImpl;
import Customer.ShipListImpl;
import Insurance.Insurance;
import Insurance.InsuranceListImpl;
import Insurance.*;
import Insurance.FireInsuranceListImpl;
import Insurance.CarInsuranceListImpl;
import Insurance.SeaInsuranceListImpl;
import Staff.Staff;
import Staff.*;
import Auth.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Staff staff = null;

        StaffList staffList = new StaffListImpl();
        Auth auth = new Auth(staffList);
        InsuranceListImpl insuranceList = new InsuranceListImpl();
        FireInsuranceList fireInsuranceList = new FireInsuranceListImpl();
        CarInsuranceList carInsuranceList = new CarInsuranceListImpl();
        SeaInsuranceList seaInsuranceList = new SeaInsuranceListImpl();
        CustomerListImpl customerList = new CustomerListImpl();
        MedicalHistoryListImpl medicalHistoryList = new MedicalHistoryListImpl();
        CarListImpl carList = new CarListImpl();
        HouseListImpl houseList = new HouseListImpl();
        ShipListImpl shipList = new ShipListImpl();
        ContractListImpl contractList = new ContractListImpl();
        MyAuthentication emailVerify = new MyAuthentication();

        Date date = new Date();

        CompensationManage compensationManagement = new CompensationManage(contractList,
                insuranceList, customerList);
        UnderWrite underWrite = new UnderWrite(contractList, insuranceList, customerList, staffList, medicalHistoryList);
        Design design = new Design(insuranceList, fireInsuranceList, carInsuranceList, seaInsuranceList);
        CalculatePremium calculatePremium = new CalculatePremiumImpl();
        Sale sale = new Sale(insuranceList, customerList, medicalHistoryList, carList, houseList, shipList, contractList, staffList, calculatePremium);
        StaffManagement staffManagement = new StaffManagement(staffList);

        main:
        while (true) {

            login:
            while (true) {
                System.out.println("========전과자들 보험사 프로그램======== \n");
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");

                String select1 = sc.nextLine();

                if (select1.equals("1")) {
                    // 사원 로그인
                    System.out.println("id : ");
                    String inputId;

                    while (true) {
                        inputId = sc.nextLine();
                        if (!inputId.matches("[+-]?\\d*(\\.\\d+)?")) {
                            System.out.println("숫자만 입력해 주세요.");
                            continue;
                        } else {
                            break;
                        }
                    }
                    System.out.println("pw : ");
                    String inputPW = sc.nextLine();

                    staff = auth.login(Integer.parseInt(inputId), inputPW);

                    if (staff != null) {
                        System.out.println(staff.getId()
                                + " " + staff.getDepartment()
                                + " " + staff.getName()
                                + "님 환영합니다!");
                        break login;
                    } else {
                        System.out.println("등록된 정보가 없습니다. 다시 시도해주시거나 회원 가입 후 진행해주세요");
                        continue login;
                    }
                } else if (Integer.parseInt(select1) == 2) {
                    // 사원 가입
                    join:
                    while (true) {
                        System.out.println("신규 사원 등록");

                        System.out.println(
                                "사원 부서 1. 설계 / 2. 인수 심사 / 3. 영업 관리 / 4. 인사 관리 / 5. 보상 운영");
                        String department;

                        while (true) {
                            department = sc.nextLine();
                            if (!department.matches("[+-]?\\d*(\\.\\d+)?")) {
                                System.out.println("숫자만 입력해 주세요.");
                                continue;
                            } else {
                                break;
                            }
                        }

                        System.out.println("직급 1. 평사원 / 2. 주임 / 3. 대리 / 4. 과장 / 5. 차장 / 6. 부장");
                        String position;

                        while (true) {
                            position = sc.nextLine();
                            if (!position.matches("[+-]?\\d*(\\.\\d+)?")) {
                                System.out.println("숫자만 입력해 주세요.");
                                continue;
                            } else {
                                break;
                            }
                        }
                        System.out.println("아이디 : ");
                        String id;

                        while (true) {
                            id = sc.nextLine();
                            if (!id.matches("[+-]?\\d*(\\.\\d+)?")) {
                                System.out.println("숫자만 입력해 주세요.");
                                continue;
                            } else {
                                break;
                            }
                        }
                        System.out.println("비밀번호 : ");
                        String pw = sc.nextLine();
                        System.out.println("이름 : ");
                        String name = sc.nextLine();
                        System.out.println("SSN : ");
                        String ssn = sc.nextLine();
                        System.out.println("성별 : 1. 남자 / 2. 여자");
                        String gender;
                        while (true) {
                            gender = sc.nextLine();
                            if (!gender.matches("[+-]?\\d*(\\.\\d+)?")) {
                                System.out.println("숫자만 입력해 주세요.");
                                continue;
                            } else {
                                break;
                            }
                        }

                        System.out.println("Email : ");
                        String email = sc.nextLine();
                        System.out.println("Phone : ");
                        String phone = sc.nextLine();

                        staff = staffManagement.createStaff(Integer.parseInt(id), pw, name, ssn,
                                Integer.parseInt(gender), email, phone, Integer.parseInt(department),
                                Integer.parseInt(position));

                        if (staff != null) {
                            System.out.println("가입을 축하합니다 " + staff.getName() + "님!");
                            System.out.println(
                                    "ID는 " + staff.getId() + "이며 기본 PW는" + staff.getPassword()
                                            + "입니다.");
                            break login;
                        } else {
                            System.out.println("가입에 실패하였습니다. 다시 시도해주세요.");
                            continue join;
                        }
                    }

                }
            }
            selectWork:
            while (true) {
                String select;
                switch (staff.getDepartment()) {
                    case Design:
                        System.out.println("1. 보험 관리");
                        System.out.println("q. 로그아웃");
                        select = sc.nextLine();

                        switch (select) {
                            case "1":
                                design:
                                while (true) {

                                    System.out.println(
                                            "자사의 총 보험 개수 : " + design.computeTotalSize());
                                    System.out.println(
                                            "자사의 인가받은 보험의 개수 : " + design.computeAuthorizeCount());
                                    System.out.println("자사의 인가받지 못한 보험의 개수 : "
                                            + design.computeNotAuthorizeCount());

                                    System.out.println("1. 보험 목록 조회하기");
                                    System.out.println("2. 뒤로가기");

                                    String select2 = sc.nextLine();

                                    if (select2.equals("1")) {
                                        selectDesign:
                                        while (true) {
                                            ArrayList<Insurance> insurances = design.getInsuranceList();
                                            for (Insurance insurance : insurances) {

                                                System.out.println(
                                                        insurance.getId() + ". " + " 보험 종류 : "
                                                                + insurance.getType().name() + " 보험 이름 : "
                                                                + insurance.getName() + " 보험 설명 : "
                                                                + insurance.getExplanation() + " 인가 여부: "
                                                                + insurance.isAuthorization());
                                            }

                                            System.out.println("1. 새로 만들기");
                                            System.out.println("2. 인가 받기");
                                            System.out.println("3. 뒤로가기");

                                            String select3 = sc.nextLine();

                                            if (select3.equals("1")) {
                                                createInsurance:
                                                while (true) {
                                                    System.out.println(
                                                            "보험 종류 : 1. 화재(기본) 2. 자동차 3. 해상");
                                                    String type;

                                                    while (true) {
                                                        type = sc.nextLine();
                                                        if (!type.matches("[+-]?\\d*(\\.\\d+)?")) {
                                                            System.out.println("숫자만 입력해 주세요.");
                                                            continue;
                                                        } else {
                                                            break;
                                                        }
                                                    }
                                                    System.out.println("보험 이름 : ");
                                                    String name = sc.nextLine();
                                                    System.out.println("보험 설명 : ");
                                                    String explanation = sc.nextLine();
                                                    System.out.println("보험료 : ");
                                                    String premium;

                                                    while (true) {
                                                        premium = sc.nextLine();
                                                        if (!premium.matches(
                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                            System.out.println("숫자만 입력해 주세요.");
                                                            continue;
                                                        } else {
                                                            break;
                                                        }
                                                    }

                                                    String surroundingDamageBasicMoney = "0";
                                                    String humanDamageBasicMoney = "0";
                                                    String buildingDamageBasicMoney = "0";
                                                    String carDamageBasicMoney = "0";
                                                    String generalDamageBasicMoney = "0";
                                                    String revenueDamageBasicMoney = "0";

                                                    switch (type) {
                                                        case "1":
                                                            System.out.println("주변 피해 기본 보상금 : ");
                                                            while (true) {
                                                                surroundingDamageBasicMoney = sc.nextLine();
                                                                if (!surroundingDamageBasicMoney.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            System.out.println("인적 피해 기본 보상금 : ");
                                                            while (true) {
                                                                humanDamageBasicMoney = sc.nextLine();
                                                                if (!humanDamageBasicMoney.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            System.out.println("건물 피해 기본 보상금 : ");

                                                            while (true) {
                                                                buildingDamageBasicMoney = sc.nextLine();
                                                                if (!buildingDamageBasicMoney.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            System.out.println("해당 보험 정보가 맞습니까?\n"
                                                                    + " 보험 종류 : " + type + "\n"
                                                                    + " 보험 이름 : " + name + "\n"
                                                                    + " 보험 설명 : " + explanation + "\n"
                                                                    + " 보험료 : " + premium + "\n"
                                                                    + " 주변 피해 기본 보상금 : : "
                                                                    + surroundingDamageBasicMoney + "\n"
                                                                    + " 인적 피해 기본 보상금 : : "
                                                                    + humanDamageBasicMoney + "\n"
                                                                    + " 건물 피해 기본 보상금 : : "
                                                                    + buildingDamageBasicMoney + "\n"
                                                                    + " 1. 예 2. 아니요 ");
                                                            break;
                                                        case "2":
                                                            System.out.println("주변 피해 기본 보상금 : ");

                                                            while (true) {
                                                                carDamageBasicMoney = sc.nextLine();
                                                                if (!carDamageBasicMoney.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }
                                                            System.out.println("인적 피해 기본 보상금 : ");

                                                            while (true) {
                                                                humanDamageBasicMoney = sc.nextLine();
                                                                if (!humanDamageBasicMoney.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            System.out.println("해당 보험 정보가 맞습니까?\n"
                                                                    + " 보험 종류 : " + type + "\n"
                                                                    + " 보험 이름 : " + name + "\n"
                                                                    + " 보험 설명 : " + explanation + "\n"
                                                                    + " 보험료 : " + premium + "\n"
                                                                    + " 자동차 피해 기본 보상금 : : "
                                                                    + carDamageBasicMoney + "\n"
                                                                    + " 인적 피해 기본 보상금 : : "
                                                                    + humanDamageBasicMoney + "\n"
                                                                    + " 1. 예 2. 아니요 ");
                                                            break;
                                                        case "3":
                                                            System.out.println("제반 손해 기본 보상금 : ");

                                                            while (true) {
                                                                generalDamageBasicMoney = sc.nextLine();
                                                                if (!generalDamageBasicMoney.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }
                                                            System.out.println("수익 손해 기본 보상금 : ");

                                                            while (true) {
                                                                revenueDamageBasicMoney = sc.nextLine();
                                                                if (!revenueDamageBasicMoney.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            System.out.println("해당 보험 정보가 맞습니까?\n"
                                                                    + " 보험 종류 : " + type + "\n"
                                                                    + " 보험 이름 : " + name + "\n"
                                                                    + " 보험 설명 : " + explanation + "\n"
                                                                    + " 보험료 : " + premium + "\n"
                                                                    + " 제반 피해 기본 보상금 : : "
                                                                    + generalDamageBasicMoney + "\n"
                                                                    + " 수익 피해 기본 보상금 : : "
                                                                    + revenueDamageBasicMoney + "\n"
                                                                    + " 1. 예 2. 아니요 ");
                                                            break;
                                                    }

                                                    String answer = sc.nextLine();
                                                    if (answer.equals("1")) {
                                                        Insurance createInsurance = design.design(
                                                            Integer.parseInt(type), name,
                                                            explanation, Integer.parseInt(premium),
                                                            Integer.parseInt(
                                                                surroundingDamageBasicMoney),
                                                            Integer.parseInt(humanDamageBasicMoney),
                                                            Integer.parseInt(
                                                                buildingDamageBasicMoney),
                                                            Integer.parseInt(carDamageBasicMoney),
                                                            Integer.parseInt(
                                                                generalDamageBasicMoney),
                                                            Integer.parseInt(
                                                                revenueDamageBasicMoney), staff);
                                                        if (createInsurance != null) {
                                                            System.out.println(
                                                                    "보험 생성이 완료되었습니다. 보험 관리 화면에서 인가를 받아야 해당 보험을 이용할 수 있습니다.");
                                                            continue selectDesign;
                                                        } else {
                                                            System.out.println(
                                                                    "보험 생성에 실패하였습니다. 다시 시도해 주세요.");
                                                            continue createInsurance;
                                                        }
                                                    } else if (answer.equals("2")) {
                                                        continue createInsurance;
                                                    }
                                                }
                                            } else if (select3.equals("2")) {
                                                authorize:
                                                while (true) {
                                                    System.out.println("=======보험 인가 받기=======");
                                                    for (Insurance insurance : insurances) {
                                                        if (!insurance.isAuthorization()) {
                                                            System.out.println(
                                                                    insurance.getId() + ". "
                                                                            + " 보험 종류 : "
                                                                            + insurance.getType().name()
                                                                            + " 보험 이름 : "
                                                                            + insurance.getName()
                                                                            + " 보험 설명 : "
                                                                            + insurance.getExplanation()
                                                                            + " 인가 여부: "
                                                                            + insurance.isAuthorization());
                                                        }
                                                    }
                                                    System.out.println();
                                                    System.out.println("1.인가 받기");
                                                    System.out.println("2.돌아 가기");
                                                    String select4 = sc.nextLine();

                                                    if (select4.equals("1")) {
                                                        System.out.println("인가 받을 보험 id를 입력해 주세요.");
                                                        String select5;

                                                        while (true) {
                                                            select5 = sc.nextLine();
                                                            if (!select5.matches(
                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                System.out.println("숫자만 입력해 주세요.");
                                                                continue;
                                                            } else {
                                                                break;
                                                            }
                                                        }

                                                        if (design.authorize(
                                                                Integer.parseInt(select5))) {
                                                            System.out.println(
                                                                    "해당 보험의 인가가 완료 되었습니다.");
                                                            continue selectDesign;
                                                        } else {
                                                            System.out.println(
                                                                    "입력한 id의 보험이 없습니다. 다시 입력해 주세요.");
                                                            continue authorize;
                                                        }
                                                    } else if (select4.equals("2")) {
                                                        continue selectDesign;
                                                    } else {
                                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                                        continue authorize;
                                                    }
                                                }
                                            } else if (select3.equals("3")) {
                                                continue design;
                                            } else {
                                                System.out.println("목록에 있는 번호를 입력해 주세요.");
                                                continue;
                                            }
                                        }


                                    } else if (select2.equals("2")) {
                                        continue selectWork;
                                    } else {
                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                        continue;
                                    }

                                }
                            case "q":
                                staff = null;
                                continue main;
                        }

                        break;

                    case Underwriting:
                        System.out.println("1. 인수 심사 관리");
                        System.out.println("q. 로그아웃");
                        select = sc.nextLine();

                        switch (select) {
                            case "1":
                                underWrite:
                                while (true) {
                                    System.out.println("인수 심사 할 고객 ID를 입력해 주세요.");
                                    String customerId;

                                    while (true) {
                                        customerId = sc.nextLine();
                                        if (!customerId.matches("[+-]?\\d*(\\.\\d+)?")) {
                                            System.out.println("숫자만 입력해 주세요.");
                                            continue;
                                        } else {
                                            break;
                                        }
                                    }
                                    Customer customer = underWrite.getCustomer(
                                            Integer.parseInt(customerId));
                                    if (customer == null) {
                                        System.out.println("해당 고객이 존재하지 않습니다.");
                                        continue underWrite;

                                    }

                                    ArrayList<Contract> contracts = underWrite.findCustomerContract(
                                            Integer.parseInt(customerId));
                                    if (contracts.isEmpty()) {
                                        System.out.println("해당 고객이 계약을 한 보험이 없습니다.");
                                        continue underWrite;
                                    }

                                    viewContract:
                                    while (true) {
                                        contracts = underWrite.findCustomerContract(
                                                Integer.parseInt(customerId));
                                        System.out.println(customer.getName() + "님의 인수 심사 현황");
                                        for (Contract contract : contracts) {
                                            Insurance insurance = underWrite.getInsurance(
                                                    contract.getInsuranceId());
                                            System.out.println(
                                                    "계약 ID : " + contract.getContractId() + " 보험 ID : "
                                                            + insurance.getId() + " 보험 이름 : "
                                                            + insurance.getName() + " 인수 심사 여부 : "
                                                            + contract.isUnderWrite());
                                        }
                                        System.out.println();
                                        System.out.println("1. 인수 심사");
                                        System.out.println("2. 돌아 가기");

                                        String select1 = sc.nextLine();

                                        if (select1.equals("1")) {
                                            findContract:
                                            while (true) {
                                                System.out.println("인수 심사 할 계약 ID를 입력해 주세요.");
                                                String contractId;

                                                while (true) {
                                                    contractId = sc.nextLine();
                                                    if (!contractId.matches(
                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                        System.out.println("숫자만 입력해 주세요.");
                                                        continue;
                                                    } else {
                                                        break;
                                                    }
                                                }

                                                Contract selectContract = underWrite.getContract(
                                                        Integer.parseInt(contractId));

                                                if (selectContract != null) {
                                                    Insurance insurance = underWrite.getInsurance(
                                                            selectContract.getInsuranceId());
                                                    if (!selectContract.isUnderWrite()) {
                                                        System.out.println("계약 ID : "
                                                                + selectContract.getContractId()
                                                                + " 보험 ID : " + insurance.getId()
                                                                + " 보험 이름 : " + insurance.getName()
                                                                + " 보험 설명 : "
                                                                + insurance.getExplanation());
                                                        System.out.println(
                                                                "인수 심사가 아직 진행되지 않았습니다. 인수 심사를 진행하시겠습니까?");
                                                        System.out.println("1. 예");
                                                        System.out.println("2. 아니요");
                                                        String select2 = sc.nextLine();

                                                        if (select2.equals("1")) {
                                                            System.out.println(
                                                                    "고객 ID : " + customer.getId()
                                                                            + " 고객 이름 : "
                                                                            + customer.getName()
                                                                            + " 고객 직업 : "
                                                                            + customer.getJob().name()
                                                                            + " 고객 병력 : "
                                                                            + medicalHistoryList.get(customer.getId())
                                                                            .getMyDisease().name());

                                                            System.out.println();
                                                            if (underWrite.checkDangerJob(
                                                                    customer)) {
                                                                System.out.println(
                                                                        "해당 고객은 위험 직업군이 아닙니다.");

                                                            } else {
                                                                System.out.println(
                                                                        "해당 고객은 위험 직업군입니다. 나이를 계산합니다");

                                                                if (!underWrite.checkAgeDangerJob(
                                                                        customer)) {
                                                                    System.out.println(
                                                                            "이 고객은 인수심사에 통과하지 못하여 보험에 가입할 수 없습니다.");
                                                                    System.out.println(
                                                                            "사유 : 위험 직업군 최대 나이 초과");
                                                                    continue viewContract;
                                                                }
                                                            }

                                                            if (!underWrite.checkDisease(
                                                                    customer)) {
                                                                System.out.println(
                                                                        "이 고객은 인수심사에 통과하지 못하여 보험에 가입할 수 없습니다.");
                                                                System.out.println(
                                                                        "사유 : 보험 가입에 제한되는 병력 보유");
                                                                continue viewContract;
                                                            }

                                                            System.out.println(
                                                                    "해당 고객은 보험 가입에 제한되는 병력이 없습니다. 인수심사가 통과되었습니다! 보험 가입이 완료되었습니다.");

                                                            underWrite.passUnderwrite(selectContract, staff);

                                                            continue viewContract;

                                                        } else if (select2.equals("2")) {
                                                            System.out.println("인수 심사를 취소하였습니다.");
                                                            continue viewContract;
                                                        } else {

                                                        }
                                                    }
                                                } else {
                                                    System.out.println(
                                                            "입력하신 ID의 계약이 존재하지 않습니다. 다시 시도해 주세요.");
                                                    continue findContract;
                                                }
                                            }
                                        } else if (select1.equals("2")) {
                                            continue selectWork;
                                        } else {
                                            System.out.println("목록에 있는 번호를 입력해 주세요.");
                                            continue viewContract;
                                        }
                                    }

                                }
                            case "q":
                                staff = null;
                                continue main;

                            default:
                                continue;
                        }

                    case Sales:
                        System.out.println("1. 보험가입자 관리");
                        System.out.println("2. 보험 계약관리");
                        System.out.println("q. 로그아웃");
                        select = sc.nextLine();

                        switch (select) {
                            case "1":
                                manageCustomer:
                                while (true) {
                                    System.out.println("총 보험 가입자 수 : " + sale.totalCustomerCount());
                                    System.out.println(
                                            "이 달에 가입한 보험 가입자 수 : " + sale.thisMonthCustomerCount());
                                    System.out.println(
                                            "보험료 미납한 보험 가입자의 수 : " + sale.unpaidCustomerCount());

                                    System.out.println();
                                    System.out.println("1. 보험 가입자 정보 상세보기");
                                    System.out.println("2. 돌아가기");

                                    String select1 = sc.nextLine();
                                    if (select1.equals("1")) {
                                        detailCustomer:
                                        while (true) {
                                            ArrayList<Customer> customers = sale.getTotalCustomer();
                                            for (Customer customer : customers) {

                                                System.out.print(customer.getId() + ". " + " 이름 : "
                                                        + customer.getName() + " SSN : "
                                                        + customer.getSSN() +
                                                        " 주소 : " + customer.getAddress() + " 전화번호 : "
                                                        + customer.getPhoneNumber()
                                                        + " E-mail : " + customer.getEmail()
                                                        + " 가입한 보험 : ");
                                                ArrayList<Insurance> insurances = sale.getJoinInsurances(customer.getId());

                                                for (Insurance insurance : insurances) {
                                                    System.out.print(insurance.getName() + " ");
                                                }
                                                System.out.println(
                                                        "이달의 납입 유무 : " + customer.isPay());
                                            }
                                            System.out.println();
                                            System.out.println("1. 고객 정보 수정 하기");
                                            System.out.println("2. 고객 정보 삭제 하기");
                                            System.out.println("3. 보험료 납입 받기");
                                            System.out.println("4. 뒤로 가기");

                                            String select2 = sc.nextLine();

                                            if (select2.equals("1")) {
                                                updateCustomer:
                                                while (true) {
                                                    System.out.println("수정 할 고객의 id를 입력해 주세요.");
                                                    String customerId;

                                                    while (true) {
                                                        customerId = sc.nextLine();
                                                        if (!customerId.matches(
                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                            System.out.println("숫자만 입력해 주세요.");
                                                            continue;
                                                        } else {
                                                            break;
                                                        }
                                                    }

                                                    System.out.println("수정 하는 주소 :");
                                                    String address = sc.nextLine();
                                                    System.out.println("수정 하는 전화번호 :");
                                                    String phoneNum = sc.nextLine();
                                                    System.out.println("수정 하는 이메일 :");
                                                    String email = sc.nextLine();

                                                    if (sale.updateCustomer(
                                                            Integer.parseInt(customerId), address,
                                                            phoneNum, email)) {
                                                        System.out.println("정보 수정이 성공적으로 완료되었습니다.");
                                                        continue detailCustomer;
                                                    } else {
                                                        System.out.println(
                                                                "정보 수정에 실패하였습니다. 다시 시도해 주세요.");
                                                        continue updateCustomer;
                                                    }
                                                }
                                            } else if (select2.equals("2")) {
                                                deleteCustomer:
                                                while (true) {
                                                    System.out.println("삭제 할 고객의 id를 입력해 주세요.");
                                                    String customerId;

                                                    while (true) {
                                                        customerId = sc.nextLine();
                                                        if (!customerId.matches(
                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                            System.out.println("숫자만 입력해 주세요.");
                                                            continue;
                                                        } else {
                                                            break;
                                                        }
                                                    }

                                                    System.out.println(sale.getCustomerName(
                                                            Integer.parseInt(customerId))
                                                            + "님의 정보를 삭제 하시겠습니까?");
                                                    System.out.println("1. 예");
                                                    System.out.println("2. 아니요");

                                                    String select3 = sc.nextLine();
                                                    if (select3.equals("1")) {
                                                        if (sale.deleteCustomer(
                                                                Integer.parseInt(customerId))) {
                                                            System.out.println("성공적으로 삭제 완료하였습니다.");
                                                            continue detailCustomer;
                                                        } else {
                                                            System.out.println(
                                                                    "정보 수정에 실패하였습니다. 다시 시도해 주세요.");
                                                            continue;
                                                        }
                                                    } else if (select3.equals("2")) {
                                                        continue detailCustomer;
                                                    } else {
                                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                                        continue;
                                                    }

                                                }
                                            } else if (select2.equals("3")) {
                                                claimPay:
                                                while (true) {
                                                    ArrayList<Customer> unpaidCustomers = sale.getUnpaidCustomer();
                                                    for (Customer customer : unpaidCustomers) {
                                                        System.out.print(
                                                                customer.getId() + ". " + " 이름 : "
                                                                        + customer.getName() + " SSN : "
                                                                        + customer.getSSN() +
                                                                        " 주소 : " + customer.getAddress()
                                                                        + " 전화번호 : "
                                                                        + customer.getPhoneNumber()
                                                                        + " E-mail : " + customer.getEmail()
                                                                        + " 가입한 보험 : ");
                                                        for (Insurance insurance : sale.getJoinInsurances(
                                                                customer.getId())) {
                                                            System.out.print(
                                                                    insurance.getName() + " ");
                                                        }
                                                        System.out.println();
                                                    }
                                                    System.out.println("1. 청구서 보내기");
                                                    System.out.println("2. 뒤로 가기");

                                                    String select3 = sc.nextLine();

                                                    if (select3.equals("1")) {
                                                        sendEmail:
                                                        while (true) {
                                                            System.out.println("체납된 보험 가입자 "
                                                                    + sale.unpaidCustomerCount()
                                                                    + "명에게 청구서를 보내시겠습니까?");

                                                            System.out.println("1. 확인");
                                                            System.out.println("2. 취소");

                                                            String select4 = sc.nextLine();

                                                            if (select4.equals("1")) {
                                                                if (sale.payCustomer(
                                                                        sale.getUnpaidCustomer())) {
                                                                    System.out.println(
                                                                            "성공적으로 청구서를 전송하였습니다.");
                                                                    continue detailCustomer;
                                                                } else {
                                                                    System.out.println(
                                                                            sale.unpaidCustomerCount()
                                                                                    + "명에게 청구서 전송을 실패하였습니다. 다시 시도해 주세요.");
                                                                    continue sendEmail;
                                                                }
                                                            } else if (select4.equals("2")) {
                                                                continue detailCustomer;
                                                            } else {
                                                                System.out.println(
                                                                        "목록에 있는 번호를 입력해 주세요.");
                                                                continue sendEmail;
                                                            }
                                                        }

                                                    } else if (select3.equals("2")) {
                                                        continue detailCustomer;
                                                    } else {
                                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                                        continue claimPay;
                                                    }
                                                }
                                            } else if (select2.equals("4")) {
                                                continue manageCustomer;
                                            } else {
                                                System.out.println("목록에 있는 번호를 입력해 주세요.");
                                                continue;
                                            }

                                        }
                                    } else if (select1.equals("2")) {
                                        continue selectWork;
                                    } else {
                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                        continue;
                                    }
                                }
                            case "2":
                                manageContract:
                                while (true) {
                                    if (sale.getInsuranceList() != null) {
                                        for (Insurance insurance : sale.getInsuranceList()) {
                                            if (insurance.isAuthorization()) {
                                                System.out.println(insurance.getId() + ". 이름 : "
                                                        + insurance.getName() + " 종류 : "
                                                        + insurance.getType().name() + " 가입자 수 : "
                                                        + sale.countContractCustomer(
                                                        insurance.getId()));
                                            }
                                        }
                                        System.out.println();
                                        System.out.println("1. 계약 검색");
                                        System.out.println("2. 계약 하기");
                                        System.out.println("3. 돌아 가기");

                                        String select1 = sc.nextLine();
                                        if (select1.equals("1")) {
                                            findContract:
                                            while (true) {
                                                System.out.println("검색하실 계약의 보험 가입자 ID를 입력해 주세요.");
                                                String customerId;

                                                while (true) {
                                                    customerId = sc.nextLine();
                                                    if (!customerId.matches(
                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                        System.out.println("숫자만 입력해 주세요.");
                                                        continue;
                                                    } else {
                                                        break;
                                                    }
                                                }

                                                if (!sale.findContract(Integer.parseInt(customerId))
                                                        .isEmpty()) {
                                                    selectCancelContract:
                                                    while (true) {
                                                        for (Contract contract : sale.findContract(
                                                                Integer.parseInt(customerId))) {
                                                            System.out.println(
                                                                    contract.getContractId()
                                                                            + ".  고객 ID : "
                                                                            + contract.getCustomerId()
                                                                            + " 고객 이름 : "
                                                                            + sale.getCustomerName(
                                                                            contract.getCustomerId())
                                                                            + " 보험 ID : "
                                                                            + contract.getInsuranceId()
                                                                            + " 보험 이름 : "
                                                                            + sale.getInsuranceName(
                                                                            contract.getInsuranceId()));
                                                        }

                                                        System.out.println();
                                                        System.out.println("1. 계약 해지");
                                                        System.out.println("2. 돌아 가기");

                                                        String select2 = sc.nextLine();

                                                        if (select2.equals("1")) {
                                                            cancelContract:
                                                            while (true) {
                                                                System.out.println(
                                                                        "계약 해지 할 계약 ID를 입력해 주세요.");
                                                                String contractId;

                                                                while (true) {
                                                                    contractId = sc.nextLine();
                                                                    if (!contractId.matches(
                                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                                        System.out.println(
                                                                                "숫자만 입력해 주세요.");
                                                                        continue;
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }

                                                                Contract contract = sale.getContract(
                                                                        Integer.parseInt(contractId));
                                                                if (contract != null) {
                                                                    System.out.println("정말로 "
                                                                            + sale.getCustomerName(
                                                                            contract.getCustomerId())
                                                                            + "님의 "
                                                                            + sale.getInsuranceName(
                                                                            contract.getInsuranceId()) +
                                                                            " 보험 계약을 해지하시겠습니까?");

                                                                    System.out.println("1. 예");
                                                                    System.out.println("2. 아니요");

                                                                    String select3 = sc.nextLine();
                                                                    if (select3.equals("1")) {
                                                                        if (sale.cancelContract(
                                                                                contract.getContractId())) {
                                                                            System.out.println(
                                                                                    date + ", "
                                                                                            + sale.getInsuranceName(
                                                                                            contract.getInsuranceId())
                                                                                            + "보험 계약해지가 완료되었습니다.");
                                                                            continue selectCancelContract;
                                                                        } else {
                                                                            System.out.println(
                                                                                    "시스템 오류로 계약 해지가 진행 되지 않았습니다. 잠시 후 다시 시도해 주세요.");
                                                                            continue cancelContract;
                                                                        }
                                                                    } else if (select3.equals(
                                                                            "2")) {
                                                                        continue selectCancelContract;
                                                                    } else {
                                                                        System.out.println(
                                                                                "목록에 있는 번호를 입력해 주세요.");
                                                                        continue cancelContract;
                                                                    }
                                                                } else {
                                                                    System.out.println(
                                                                            "오류로 인하여 보험 가입자 및 보험의 정보를 불러오지 못했습니다. 다시 시도해 주세요.");
                                                                    continue cancelContract;
                                                                }
                                                            }
                                                        } else if (select2.equals("2")) {
                                                            continue manageContract;
                                                        } else {
                                                            System.out.println(
                                                                    "목록에 있는 번호를 입력해 주세요.");
                                                            continue selectCancelContract;
                                                        }
                                                    }
                                                } else {
                                                    System.out.println(
                                                            "해당 정보의 보험 가입자를 조회할 수 없습니다. 다시 시도해 주세요.");
                                                    continue manageContract;
                                                }
                                            }
                                        } else if (select1.equals("2")) {
                                            findInsurance:
                                            while (true) {
                                                System.out.println("계약을 진행 할 보험 ID를 입력해 주세요.");
                                                String insuranceId;

                                                while (true) {
                                                    insuranceId = sc.nextLine();
                                                    if (!insuranceId.matches(
                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                        System.out.println("숫자만 입력해 주세요.");
                                                        continue;
                                                    } else {
                                                        break;
                                                    }
                                                }

                                                Insurance insurance = sale.getInsurance(
                                                        Integer.parseInt(insuranceId));

                                                if (insurance != null) {
                                                    findCustomer:
                                                    while (true) {
                                                        Customer customer;
                                                        System.out.println("이미 가입하신 고객이신가요?");
                                                        System.out.println("1. 예");
                                                        System.out.println("2. 아니요");
                                                        System.out.println("3. 돌아가기");

                                                        String select2 = sc.nextLine();
                                                        if (select2.equals("1")) {
                                                            System.out.println("고객 ID를 입력해 주세요.");

                                                            String customerId;

                                                            while (true) {
                                                                customerId = sc.nextLine();
                                                                if (!customerId.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            customer = sale.getCustomer(
                                                                    Integer.parseInt(customerId));

                                                            if (customer == null) {
                                                                System.out.println(
                                                                        "입력하신 ID의 고객 정보가 없습니다. 다시 입력해 주세요.");
                                                                continue findCustomer;
                                                            }

                                                            switch (insurance.getType()) {
                                                                case Car:
                                                                    if(customer.getCar() == null) {
                                                                        System.out.println(
                                                                                "자동차 번호 : ");
                                                                        String carNum;

                                                                        while (true) {
                                                                            carNum = sc.nextLine();
                                                                            if (!carNum.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "자동차 연식 : ");
                                                                        String carYear;

                                                                        while (true) {
                                                                            carYear = sc.nextLine();
                                                                            if (!carYear.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "자동차 배기량 : ");
                                                                        String carDisplacement;

                                                                        while (true) {
                                                                            carDisplacement = sc.nextLine();
                                                                            if (!carDisplacement.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "자동차 가격 : ");
                                                                        String carPrice;

                                                                        while (true) {
                                                                            carPrice = sc.nextLine();
                                                                            if (!carPrice.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        sale.setCustomerCar(
                                                                                customer,
                                                                                Integer.parseInt(
                                                                                        carNum),
                                                                                Integer.parseInt(
                                                                                        carYear),
                                                                                Integer.parseInt(
                                                                                        carDisplacement),
                                                                                Integer.parseInt(
                                                                                        carPrice));
                                                                    }
                                                                    break;
                                                                case Fire:
                                                                    if(customer.getHouse() == null) {
                                                                        System.out.println(
                                                                                "건물 종류 : 1. 아파트 / 2. 주택 / 3. 오피스텔");
                                                                        String houseType;

                                                                        while (true) {
                                                                            houseType = sc.nextLine();
                                                                            if (!houseType.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        System.out.println(
                                                                                "건물 가격 : ");
                                                                        String housePrice;
                                                                        while (true) {
                                                                            housePrice = sc.nextLine();
                                                                            if (!housePrice.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        sale.setCustomerHouse(
                                                                                customer,
                                                                                Integer.parseInt(
                                                                                        houseType),
                                                                                Integer.parseInt(
                                                                                        housePrice));
                                                                    }
                                                                    break;
                                                                case Sea:
                                                                    if(customer.getShip() == null) {
                                                                        System.out.println(
                                                                                "선박 번호 : ");
                                                                        String shipNum;

                                                                        while (true) {
                                                                            shipNum = sc.nextLine();
                                                                            if (!shipNum.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        System.out.println(
                                                                                "선박 연식 : ");
                                                                        String shipYear;

                                                                        while (true) {
                                                                            shipYear = sc.nextLine();
                                                                            if (!shipYear.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "선박 가격 : ");
                                                                        String shipPrice;

                                                                        while (true) {
                                                                            shipPrice = sc.nextLine();
                                                                            if (!shipPrice.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "선박 종류 : 1. 일반 화물 / 2. 컨테이너선 ");
                                                                        String shipType;

                                                                        while (true) {
                                                                            shipType = sc.nextLine();
                                                                            if (!shipType.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        sale.setCustomerSea(
                                                                                customer,
                                                                                Integer.parseInt(
                                                                                        shipNum),
                                                                                Integer.parseInt(
                                                                                        shipYear),
                                                                                Integer.parseInt(
                                                                                        shipPrice),
                                                                                Integer.parseInt(
                                                                                        shipType));
                                                                    }
                                                                    break;
                                                            }

                                                            signContract:
                                                            while (true) {
                                                                System.out.println(
                                                                        "청약서에 동의하시고 계약을 진행하시겠습니까?");
                                                                System.out.println(
                                                                        "1. 동의하고 계약하기");
                                                                System.out.println("2. 취소");

                                                                String select3 = sc.nextLine();

                                                                if (select3.equals("1")) {
                                                                    if (sale.signContract(
                                                                            insurance.getId(),
                                                                            customer, staff)) {
                                                                        System.out.println(
                                                                                "계약서 작성이 완료되었습니다. 인수 심사 후 최종 가입 여부가 결정됩니다.");
                                                                        continue manageContract;
                                                                    } else {
                                                                        System.out.println(
                                                                                "예기치 못한 오류로 보험 계약에 실패하였습니다. 계약을 다시 시도해주세요.");
                                                                        continue findInsurance;
                                                                    }
                                                                } else if (select3.equals(
                                                                        "2")) {
                                                                    System.out.println(
                                                                            "계약을 취소하였습니다.");
                                                                    continue manageContract;
                                                                } else {
                                                                    System.out.println(
                                                                            "목록에 있는 번호를 입력해 주세요.");
                                                                    continue signContract;
                                                                }
                                                            }

                                                        } else if (select2.equals("2")) {
                                                            createCustomer:
                                                            while (true) {
                                                                System.out.println(
                                                                        "계약을 진행하실 고객의 정보를 입력해 주세요.");

                                                                customer = new Customer();

                                                                System.out.println("고객 이름 : ");
                                                                String customerName = sc.nextLine();
                                                                System.out.println("고객 주민등록번호 : ");
                                                                String customerSSN = sc.nextLine();
                                                                System.out.println("고객 주소 : ");
                                                                String customerAddress = sc.nextLine();
                                                                System.out.println("고객 전화번호 : ");
                                                                String customerPhoneNum = sc.nextLine();
                                                                System.out.println("고객 이메일 : ");
                                                                String customerEmail = sc.nextLine();
                                                                System.out.println("고객 계좌번호 : ");
                                                                String customerAccount = sc.nextLine();
                                                                System.out.println(
                                                                        "고객 성별 : 1. 남자 / 2. 여자");
                                                                String customerSex;
                                                                while (true) {
                                                                    customerSex = sc.nextLine();
                                                                    if (!customerSex.matches(
                                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                                        System.out.println(
                                                                                "숫자만 입력해 주세요.");
                                                                        continue;
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }
                                                                System.out.println("고객 나이 : ");
                                                                String customerAge;
                                                                while (true) {
                                                                    customerAge = sc.nextLine();
                                                                    if (!customerAge.matches(
                                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                                        System.out.println(
                                                                                "숫자만 입력해 주세요.");
                                                                        continue;
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }

                                                                System.out.println(
                                                                        "고객 직업 : 1. 영업직 / 2. 생산직 / 3. 사무직 / 4. 자영업자 / 5. 무직");
                                                                String customerJob;

                                                                while (true) {
                                                                    customerJob = sc.nextLine();
                                                                    if (!customerJob.matches(
                                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                                        System.out.println(
                                                                                "숫자만 입력해 주세요.");
                                                                        continue;
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }

                                                                System.out.println(
                                                                        "고객 병력: 1. 암 / 2. 결핵 / 3. 수두 / 4. 홍역 / 5. 없음");
                                                                String customerDisease;
                                                                while (true) {
                                                                    customerDisease = sc.nextLine();
                                                                    if (!customerDisease.matches(
                                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                                        System.out.println(
                                                                                "숫자만 입력해 주세요.");
                                                                        continue;
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }
                                                                if (!customerDisease.equals("5")) {
                                                                    System.out.println("발병 년도: ");
                                                                    String customerHistoryYear;

                                                                    while (true) {
                                                                        customerHistoryYear = sc.nextLine();
                                                                        if (!customerHistoryYear.matches(
                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                            System.out.println(
                                                                                    "숫자만 입력해 주세요.");
                                                                            continue;
                                                                        } else {
                                                                            break;
                                                                        }
                                                                    }

                                                                    System.out.println(
                                                                            "완치 여부: 1. 완치 / 2. 불완치");
                                                                    String customerCureComplete;

                                                                    while (true) {
                                                                        customerCureComplete = sc.nextLine();
                                                                        if (!customerCureComplete.matches(
                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                            System.out.println(
                                                                                    "숫자만 입력해 주세요.");
                                                                            continue;
                                                                        } else {
                                                                            break;
                                                                        }
                                                                    }

                                                                    customer = sale.joinCustomer(
                                                                            customerName, customerSSN,
                                                                            customerAddress,
                                                                            customerPhoneNum,
                                                                            customerEmail,
                                                                            customerAccount,
                                                                            Integer.parseInt(
                                                                                    customerAge),
                                                                            Integer.parseInt(
                                                                                    customerSex),
                                                                            Integer.parseInt(
                                                                                    customerJob)
                                                                            , Integer.parseInt(
                                                                                    customerDisease),
                                                                            Integer.parseInt(
                                                                                    customerHistoryYear),
                                                                            Integer.parseInt(
                                                                                    customerCureComplete));
                                                                } else {
                                                                    customer = sale.joinCustomer(
                                                                            customerName, customerSSN,
                                                                            customerAddress,
                                                                            customerPhoneNum,
                                                                            customerEmail,
                                                                            customerAccount,
                                                                            Integer.parseInt(
                                                                                    customerAge),
                                                                            Integer.parseInt(
                                                                                    customerSex),
                                                                            Integer.parseInt(
                                                                                    customerJob)
                                                                            , Integer.parseInt(
                                                                                    customerDisease), 0, 0);
                                                                }

                                                                switch (insurance.getType()) {
                                                                    case Car:
                                                                        System.out.println(
                                                                                "자동차 번호 : ");
                                                                        String carNum;

                                                                        while (true) {
                                                                            carNum = sc.nextLine();
                                                                            if (!carNum.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "자동차 연식 : ");
                                                                        String carYear;

                                                                        while (true) {
                                                                            carYear = sc.nextLine();
                                                                            if (!carYear.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "자동차 배기량 : ");
                                                                        String carDisplacement;

                                                                        while (true) {
                                                                            carDisplacement = sc.nextLine();
                                                                            if (!carDisplacement.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "자동차 가격 : ");
                                                                        String carPrice;

                                                                        while (true) {
                                                                            carPrice = sc.nextLine();
                                                                            if (!carPrice.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        sale.setCustomerCar(
                                                                                customer,
                                                                                Integer.parseInt(
                                                                                        carNum),
                                                                                Integer.parseInt(
                                                                                        carYear),
                                                                                Integer.parseInt(
                                                                                        carDisplacement),
                                                                                Integer.parseInt(
                                                                                        carPrice));
                                                                        break;
                                                                    case Fire:
                                                                        System.out.println(
                                                                                "건물 종류 : 1. 아파트 / 2. 주택 / 3. 오피스텔");
                                                                        String houseType;

                                                                        while (true) {
                                                                            houseType = sc.nextLine();
                                                                            if (!houseType.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        System.out.println(
                                                                                "건물 가격 : ");
                                                                        String housePrice;
                                                                        while (true) {
                                                                            housePrice = sc.nextLine();
                                                                            if (!housePrice.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        sale.setCustomerHouse(
                                                                                customer,
                                                                                Integer.parseInt(
                                                                                        houseType),
                                                                                Integer.parseInt(
                                                                                        housePrice));
                                                                        break;
                                                                    case Sea:
                                                                        System.out.println(
                                                                                "선박 번호 : ");
                                                                        String shipNum;

                                                                        while (true) {
                                                                            shipNum = sc.nextLine();
                                                                            if (!shipNum.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        System.out.println(
                                                                                "선박 연식 : ");
                                                                        String shipYear;

                                                                        while (true) {
                                                                            shipYear = sc.nextLine();
                                                                            if (!shipYear.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "선박 가격 : ");
                                                                        String shipPrice;

                                                                        while (true) {
                                                                            shipPrice = sc.nextLine();
                                                                            if (!shipPrice.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        System.out.println(
                                                                                "선박 종류 : 1. 일반 화물 / 2. 컨테이너선 ");
                                                                        String shipType;

                                                                        while (true) {
                                                                            shipType = sc.nextLine();
                                                                            if (!shipType.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        sale.setCustomerSea(
                                                                                customer,
                                                                                Integer.parseInt(
                                                                                        shipNum),
                                                                                Integer.parseInt(
                                                                                        shipYear),
                                                                                Integer.parseInt(
                                                                                        shipPrice),
                                                                                Integer.parseInt(
                                                                                        shipType));
                                                                        break;
                                                                }
                                                                signContract:
                                                                while (true) {
                                                                    System.out.println(
                                                                            "청약서에 동의하시고 계약을 진행하시겠습니까?");
                                                                    System.out.println(
                                                                            "1. 동의하고 계약하기");
                                                                    System.out.println("2. 취소");

                                                                    String select3 = sc.nextLine();

                                                                    if (select3.equals("1")) {
                                                                        if (sale.signContract(
                                                                            insurance.getId(),
                                                                            customer, staff)) {
                                                                            System.out.println(
                                                                                    "계약서 작성이 완료되었습니다. 인수 심사 후 최종 가입 여부가 결정됩니다.");
                                                                            continue manageContract;
                                                                        } else {
                                                                            System.out.println(
                                                                                    "예기치 못한 오류로 보험 계약에 실패하였습니다. 계약을 다시 시도해주세요.");
                                                                            continue findInsurance;
                                                                        }
                                                                    } else if (select3.equals(
                                                                            "2")) {
                                                                        System.out.println(
                                                                                "계약을 취소하였습니다.");
                                                                        continue manageContract;
                                                                    } else {
                                                                        System.out.println(
                                                                                "목록에 있는 번호를 입력해 주세요.");
                                                                        continue signContract;
                                                                    }
                                                                }
                                                            }
                                                        } else if (select2.equals("3")) {
                                                            continue findInsurance;
                                                        } else {
                                                            System.out.println(
                                                                    "목록에 있는 번호를 입력해 주세요.");
                                                            continue findCustomer;
                                                        }


                                                    }
                                                } else {
                                                    System.out.println(
                                                            "입력하신 ID의 보험을 찾을 수 없습니다. 다시 시도해 주세요.");
                                                    continue findInsurance;
                                                }


                                            }
                                        } else if (select1.equals("3")) {
                                            continue selectWork;
                                        } else {
                                            System.out.println("목록에 있는 번호를 입력해 주세요.");
                                            continue;
                                        }
                                    }
                                }
                            case "q":
                                staff = null;
                                continue main;
                            default:
                                continue;
                        }

                    case Human:
                        System.out.println("1. 사원 관리");
                        System.out.println("q. 로그아웃");
                        select = sc.nextLine();

                        switch (select) {
                            case "1":
                                manageStaff:
                                while (true) {
                                    System.out.println("관리하실 부서를 선택해 주세요.");
                                    System.out.println("1. 보험 설계부");
                                    System.out.println("2. 인수 심사부");
                                    System.out.println("3. 영업 관리부");
                                    System.out.println("4. 인사 관리부");
                                    System.out.println("5. 보상 운영부");
                                    System.out.println("6. 돌아 가기");

                                    String department;

                                    while (true) {
                                        department = sc.nextLine();
                                        if (!department.matches("[+-]?\\d*(\\.\\d+)?")) {
                                            System.out.println("숫자만 입력해 주세요.");
                                            continue;
                                        } else {
                                            break;
                                        }
                                    }

                                    if (department.equals("1") || department.equals("2")
                                            || department.equals("3") || department.equals("4")
                                            || department.equals("5")) {
                                        findStaff:
                                        while (true) {
                                            ArrayList<Staff> staffs = new ArrayList<>();
                                            staffs = staffManagement.getDepartmentStaff(
                                                    Integer.parseInt(department));
                                            if (!staffs.isEmpty()) {

                                                for (Staff findStaff : staffs) {
                                                    staffManagement.calculateSalary(
                                                        findStaff.getId(), staff);
                                                    System.out.println(
                                                            "부서 : " + findStaff.getDepartment().name()
                                                                    + " 사원 번호 : " + findStaff.getId()
                                                                    + " 사원 이름 : " + findStaff.getName()
                                                                    + " 입사 일자 : "
                                                                    + findStaff.getJoinDate());
                                                }
                                                System.out.println("1. 상세 보기");
                                                System.out.println("2. 월급 관리");
                                                System.out.println("3. 부서 이동");
                                                System.out.println("4. 해고 하기");
                                                System.out.println("5. 돌아 가기");

                                                String select2 = sc.nextLine();
                                                if (select2.equals("1")) {
                                                    for (Staff findStaff : staffs) {
                                                        System.out.println(
                                                                " 사원 번호 : " + findStaff.getId()
                                                                        + " 사원 이름 : " + findStaff.getName()
                                                                        + " 부서 : "
                                                                        + findStaff.getDepartment().name()
                                                                        + "주민등록번호 : " + findStaff.getSSN()
                                                                        + " 이메일 : " + findStaff.getEmail()
                                                                        + " 전화 번호 : "
                                                                        + findStaff.getPhoneNum()
                                                                        + " 입사 일자 : "
                                                                        + findStaff.getJoinDate());
                                                    }
                                                    continue findStaff;
                                                } else if (select2.equals("2")) {
                                                    managementSalary:
                                                    while (true) {
                                                        System.out.println(
                                                                "월급을 관리할 사원의 ID를 입력해 주세요.");
                                                        String staffId;

                                                        while (true) {
                                                            staffId = sc.nextLine();
                                                            if (!staffId.matches(
                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                System.out.println("숫자만 입력해 주세요.");
                                                                continue;
                                                            } else {
                                                                break;
                                                            }
                                                        }

                                                        Staff manageStaff = staffManagement.getStaff(
                                                                Integer.parseInt(staffId));

                                                        if (manageStaff == null) {
                                                            System.out.println(
                                                                    "죄송합니다. 해당 사원의 월급 정보를 제대로 불러올 수 없습니다. 잠시 후 다시 시도해주세요.");
                                                            continue managementSalary;
                                                        }

                                                        System.out.println(
                                                                "직급 : " + manageStaff.getPosition()
                                                                        .name() + " 근무 일수: "
                                                                        + staffManagement.calculateWorkDate(
                                                                        manageStaff.getId())
                                                                        + " 보너스 실적 : "
                                                                        + manageStaff.getResult()
                                                                        + " 최종 월급 : "
                                                                        + manageStaff.getTotalSalary());

                                                        System.out.println("1. 직급 변경하기");
                                                        System.out.println("2. 돌아가기");
                                                        String select3 = sc.nextLine();
                                                        if (select3.equals("1")) {
                                                            System.out.println(
                                                                    "변경 하실 직급을 선택해 주세요.");
                                                            System.out.println(
                                                                    "직급 1. 평사원 / 2. 주임 / 3. 대리 / 4. 과장 / 5. 차장 / 6. 부장");

                                                            String position;

                                                            while (true) {
                                                                position = sc.nextLine();
                                                                if (!position.matches(
                                                                        "[+-]?\\d*(\\.\\d+)?")) {
                                                                    System.out.println(
                                                                            "숫자만 입력해 주세요.");
                                                                    continue;
                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            staffManagement.changePosition(
                                                                manageStaff,
                                                                Integer.parseInt(position), staff);

                                                            System.out.println(
                                                                    "직책이 변경되었습니다. 직책에 따라 기본 월급이 변경됩니다.");
                                                            continue manageStaff;

                                                        } else if (select3.equals("2")) {
                                                            continue manageStaff;
                                                        } else {
                                                            continue managementSalary;
                                                        }
                                                    }


                                                } else if (select2.equals("3")) {
                                                    changeDepartment:
                                                    while (true) {
                                                        System.out.println("부서를 이동할 사원의 ID를 입력해 주세요.");


                                                        String staffId;

                                                        while (true) {
                                                            staffId = sc.nextLine();
                                                            if (!staffId.matches(
                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                System.out.println("숫자만 입력해 주세요.");
                                                                continue;
                                                            } else {
                                                                break;
                                                            }
                                                        }

                                                        System.out.println("이동할 부서를 선택해 주세요.");
                                                        System.out.println("1. 보험 설계부");
                                                        System.out.println("2. 인수 심사부");
                                                        System.out.println("3. 영업 관리부");
                                                        System.out.println("4. 인사 관리부");
                                                        System.out.println("5. 보상 운영부");

                                                        String changeDepartment;

                                                        while (true) {
                                                            changeDepartment = sc.nextLine();
                                                            if (!changeDepartment.matches(
                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                System.out.println("숫자만 입력해 주세요.");
                                                                continue;
                                                            } else {
                                                                break;
                                                            }
                                                        }

                                                        if (staffManagement.updateDepartment(
                                                                Integer.parseInt(staffId),
                                                                Integer.parseInt(changeDepartment))) {
                                                            System.out.println(
                                                                    "성공적으로 부서가 이동되었습니다.");
                                                            continue findStaff;
                                                        } else {
                                                            System.out.println(
                                                                    "예기치 못한 오류로 인해 부서 이동에 실패하였습니다. 다시 시도해주세요.");
                                                            continue changeDepartment;
                                                        }
                                                    }

                                                } else if (select2.equals("4")) {
                                                    fireStaff:
                                                    while (true) {
                                                        System.out.println("해고하실 사원의 ID를 입력해 주세요.");
                                                        String staffId;

                                                        while (true) {
                                                            staffId = sc.nextLine();
                                                            if (!staffId.matches(
                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                System.out.println("숫자만 입력해 주세요.");
                                                                continue;
                                                            } else {
                                                                break;
                                                            }
                                                        }

                                                        System.out.println(
                                                                "정말로 해고하시겠습니까? 해당 사원의 정보가 시스템에서 삭제됩니다.");
                                                        System.out.println("1. 예");
                                                        System.out.println("2. 아니요");
                                                        String select3 = sc.nextLine();
                                                        if (select3.equals("1")) {
                                                            staffManagement.fireStaff(
                                                                    Integer.parseInt(staffId));
                                                            System.out.println("사원이 해고되었습니다.");
                                                            continue manageStaff;
                                                        } else if (select3.equals("2")) {
                                                            System.out.println("해고를 취소하였습니다.");
                                                            continue findStaff;
                                                        } else {
                                                            System.out.println(
                                                                    "목록에 있는 번호를 입력해 주세요.");
                                                            continue fireStaff;
                                                        }
                                                    }
                                                } else if (select2.equals("5")) {
                                                    continue manageStaff;
                                                } else {
                                                    System.out.println("목록에 있는 번호를 입력해 주세요.");
                                                    continue findStaff;
                                                }
                                            } else {
                                                System.out.println(
                                                        "죄송합니다. 사원 정보를 제대로 불러올 수 없습니다. 잠시 후 다시 시도해주세요.");
                                                continue manageStaff;
                                            }
                                        }
                                    } else if (department.equals("6")) {
                                        continue selectWork;
                                    } else {
                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                        continue manageStaff;
                                    }
                                }
                            case "q":
                                staff = null;
                                continue main;
                            default:
                                continue;
                        }
                    case CompensationManagement:
                        System.out.println("1. 보상 운영");
                        System.out.println("q. 로그아웃");
                        select = sc.nextLine();

                        switch (select) {
                            case "1":
                                compensationManage:
                                while (true) {
                                    System.out.println("1. 계약 검색하기");
                                    System.out.println("2. 뒤로 가기");

                                    String select1 = sc.nextLine();
                                    if (select1.equals("1")) {
                                        findContract:
                                        while (true) {
                                            System.out.println("보험 가입자 ID : ");
                                            String customerId;

                                            while (true) {
                                                customerId = sc.nextLine();
                                                if (!customerId.matches("[+-]?\\d*(\\.\\d+)?")) {
                                                    System.out.println("숫자만 입력해 주세요.");
                                                    continue;
                                                } else {
                                                    break;
                                                }
                                            }

                                            ArrayList<Contract> findContract = compensationManagement.findInsuranceContracts(
                                                    Integer.parseInt(customerId));
                                            if (!findContract.isEmpty()) {
                                                for (Contract contract : findContract) {
                                                    Insurance insurance = sale.getInsurance(
                                                            contract.getInsuranceId());
                                                    System.out.println(
                                                            "계약 ID : " + contract.getContractId()
                                                                    + " 보험 ID : " + insurance.getId()
                                                                    + ". 보험 이름 : " + insurance.getName()
                                                                    + " 보험 종류 : " + insurance.getType()
                                                                    .name());
                                                }
                                                System.out.println("보상 신청 할 계약 ID를 입력해 주세요.");
                                                String contractId;

                                                while (true) {
                                                    contractId = sc.nextLine();
                                                    if (!contractId.matches(
                                                            "[+-]?\\d*(\\.\\d+)?")) {
                                                        System.out.println("숫자만 입력해 주세요.");
                                                        continue;
                                                    } else {
                                                        break;
                                                    }
                                                }
                                                Insurance insurance = sale.getInsurance(
                                                    sale.getContract(Integer.parseInt(contractId)).getInsuranceId());

                                                if (insurance == null) {
                                                    System.out.println(
                                                            "입력하신 보험을 찾아오지 못하였습니다. 다시 시도해 주세요.");
                                                    continue findContract;
                                                }

                                                Contract contract = compensationManagement.getContract(
                                                        Integer.parseInt(contractId));
                                                Customer customer = sale.getCustomer(
                                                        Integer.parseInt(customerId));

                                                judgementSubject:
                                                while (true) {
                                                    System.out.println("사고 발생의 주체를 선택해 주세요.");
                                                    for (AccidentSubjectIndemnification accidentSubjectIndemnification : AccidentSubjectIndemnification.values()) {
                                                        System.out.println(
                                                                accidentSubjectIndemnification.ordinal()
                                                                        + 1 + ". "
                                                                        + accidentSubjectIndemnification.getExplanation());
                                                    }
                                                    String subject;

                                                    while (true) {
                                                        subject = sc.nextLine();
                                                        if (!subject.matches(
                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                            System.out.println("숫자만 입력해 주세요.");
                                                            continue;
                                                        } else {
                                                            break;
                                                        }
                                                    }

                                                    if (subject.equals("1") || subject.equals("2")
                                                            || subject.equals("3")) {
                                                        if (compensationManagement.judgeSubjectIndemnification(
                                                                Integer.parseInt(subject))) {
                                                            judgementIndemnification:
                                                            while (true) {
                                                                String indemnification;
                                                                System.out.println(
                                                                        "사고 발생 원인을 선택해 주세요.");
                                                                switch (insurance.getType()) {
                                                                    case Car:
                                                                        for (CarAccidentCauseIndemnification carAccidentCauseIndemnification : CarAccidentCauseIndemnification.values()) {
                                                                            System.out.println(
                                                                                    carAccidentCauseIndemnification.ordinal()
                                                                                            + 1 + ". "
                                                                                            + carAccidentCauseIndemnification.getExplanation());
                                                                        }
                                                                        while (true) {
                                                                            indemnification = sc.nextLine();
                                                                            if (!indemnification.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }
                                                                        if (indemnification.equals(
                                                                                "1")
                                                                                || indemnification.equals(
                                                                                "2")
                                                                                || indemnification.equals(
                                                                                "3")
                                                                                || indemnification.equals(
                                                                                "4")
                                                                                || indemnification.equals(
                                                                                "5")
                                                                                || indemnification.equals(
                                                                                "6")
                                                                                || indemnification.equals(
                                                                                "7")) {
                                                                            if (compensationManagement.judgeCarIndemnification(
                                                                                    Integer.parseInt(
                                                                                            indemnification))) {
                                                                                calculateCompensation:
                                                                                while (true) {
                                                                                    System.out.println(
                                                                                            "차 파손 (0 ~ 100) : ");
                                                                                    String carDamage;

                                                                                    while (true) {
                                                                                        carDamage = sc.nextLine();
                                                                                        if (!carDamage.matches(
                                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                                            System.out.println(
                                                                                                    "숫자만 입력해 주세요.");
                                                                                            continue;
                                                                                        } else {
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    System.out.println(
                                                                                            "인적 피해 (0 ~ 100) ");
                                                                                    String humanDamage;

                                                                                    while (true) {
                                                                                        humanDamage = sc.nextLine();
                                                                                        if (!humanDamage.matches(
                                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                                            System.out.println(
                                                                                                    "숫자만 입력해 주세요.");
                                                                                            continue;
                                                                                        } else {
                                                                                            break;
                                                                                        }
                                                                                    }

                                                                                    compensationManagement.compensation(

                                                                                        contract.getContractId(),
                                                                                        Integer.parseInt(
                                                                                            humanDamage),
                                                                                        0, 0,
                                                                                        Integer.parseInt(
                                                                                            carDamage),
                                                                                        0, 0, staff);
                                                                                    break calculateCompensation;
                                                                                }
                                                                                break;
                                                                            } else {
                                                                                System.out.println(
                                                                                        "죄송합니다. "
                                                                                                + CarAccidentCauseIndemnification.values()[
                                                                                                Integer.parseInt(
                                                                                                        indemnification)
                                                                                                        - 1].getExplanation()
                                                                                                + "로 인해 발생한 사고는 보험지급금 의무 면책 사유입니다.");
                                                                                continue findContract;
                                                                            }
                                                                        } else {
                                                                            System.out.println(
                                                                                    "목록에 있는 번호를 입력해 주세요.");
                                                                            continue judgementIndemnification;
                                                                        }
                                                                    case Sea:
                                                                        for (SeaAccidentCauseIndemnification seaAccidentCauseIndemnification : SeaAccidentCauseIndemnification.values()) {
                                                                            System.out.println(
                                                                                    seaAccidentCauseIndemnification.ordinal()
                                                                                            + 1 + ". "
                                                                                            + seaAccidentCauseIndemnification.getExplanation());
                                                                        }
                                                                        while (true) {
                                                                            indemnification = sc.nextLine();
                                                                            if (!indemnification.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        if (indemnification.equals(
                                                                                "1")
                                                                                || indemnification.equals(
                                                                                "2")
                                                                                || indemnification.equals(
                                                                                "3")
                                                                                || indemnification.equals(
                                                                                "4")) {
                                                                            if (compensationManagement.judgeSeaIndemnification(
                                                                                    Integer.parseInt(
                                                                                            indemnification))) {
                                                                                calculateCompensation:
                                                                                while (true) {
                                                                                    System.out.println(
                                                                                            "제반 손해 (0 ~ 100) : ");
                                                                                    String generalDamage;

                                                                                    while (true) {
                                                                                        generalDamage = sc.nextLine();
                                                                                        if (!generalDamage.matches(
                                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                                            System.out.println(
                                                                                                    "숫자만 입력해 주세요.");
                                                                                            continue;
                                                                                        } else {
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    System.out.println(
                                                                                            "수익 손해 (0 ~ 100) ");
                                                                                    String revenueDamage;

                                                                                    while (true) {
                                                                                        revenueDamage = sc.nextLine();
                                                                                        if (!revenueDamage.matches(
                                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                                            System.out.println(
                                                                                                    "숫자만 입력해 주세요.");
                                                                                            continue;
                                                                                        } else {
                                                                                            break;
                                                                                        }
                                                                                    }

                                                                                    compensationManagement.compensation(
                                                                                     contract.getContractId(),
                                                                                        0, 0, 0, 0,
                                                                                        Integer.parseInt(
                                                                                            generalDamage),
                                                                                        Integer.parseInt(
                                                                                            revenueDamage), staff);
                                                                                    break calculateCompensation;
                                                                                }
                                                                                break;
                                                                            } else {
                                                                                System.out.println(
                                                                                        "죄송합니다. "
                                                                                                + SeaAccidentCauseIndemnification.values()[
                                                                                                Integer.parseInt(
                                                                                                        indemnification)
                                                                                                        - 1].getExplanation()
                                                                                                + "로 인해 발생한 사고는 보험지급금 의무 면책 사유입니다.");
                                                                                continue findContract;
                                                                            }
                                                                        } else {
                                                                            System.out.println(
                                                                                    "목록에 있는 번호를 입력해 주세요.");
                                                                            continue judgementIndemnification;
                                                                        }
                                                                    case Fire:
                                                                        for (FireAccidentCauseIndemnification fireAccidentCauseIndemnification : FireAccidentCauseIndemnification.values()) {
                                                                            System.out.println(
                                                                                    fireAccidentCauseIndemnification.ordinal()
                                                                                            + 1 + ". "
                                                                                            + fireAccidentCauseIndemnification.getExplanation());
                                                                        }
                                                                        while (true) {
                                                                            indemnification = sc.nextLine();
                                                                            if (!indemnification.matches(
                                                                                    "[+-]?\\d*(\\.\\d+)?")) {
                                                                                System.out.println(
                                                                                        "숫자만 입력해 주세요.");
                                                                                continue;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                        }

                                                                        if (indemnification.equals(
                                                                                "1")
                                                                                || indemnification.equals(
                                                                                "2")
                                                                                || indemnification.equals(
                                                                                "3")
                                                                                || indemnification.equals(
                                                                                "4")) {
                                                                            if (compensationManagement.judgeFireIndemnification(
                                                                                    Integer.parseInt(
                                                                                            indemnification))) {
                                                                                calculateCompensation:
                                                                                while (true) {
                                                                                    System.out.println(
                                                                                            "화재 건물 파손 상태 (0 ~ 100) : ");
                                                                                    String buildingDamage;

                                                                                    while (true) {
                                                                                        buildingDamage = sc.nextLine();
                                                                                        if (!buildingDamage.matches(
                                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                                            System.out.println(
                                                                                                    "숫자만 입력해 주세요.");
                                                                                            continue;
                                                                                        } else {
                                                                                            break;
                                                                                        }
                                                                                    }

                                                                                    System.out.println(
                                                                                            "인적 피해 (0 ~ 100) ");
                                                                                    String humanDamage;

                                                                                    while (true) {
                                                                                        humanDamage = sc.nextLine();
                                                                                        if (!humanDamage.matches(
                                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                                            System.out.println(
                                                                                                    "숫자만 입력해 주세요.");
                                                                                            continue;
                                                                                        } else {
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    System.out.println(
                                                                                            "주변 피해 (0 ~ 100) ");
                                                                                    String surroundingDamage;

                                                                                    while (true) {
                                                                                        surroundingDamage = sc.nextLine();
                                                                                        if (!surroundingDamage.matches(
                                                                                                "[+-]?\\d*(\\.\\d+)?")) {
                                                                                            System.out.println(
                                                                                                    "숫자만 입력해 주세요.");
                                                                                            continue;
                                                                                        } else {
                                                                                            break;
                                                                                        }
                                                                                    }

                                                                                    compensationManagement.compensation(

                                                                                        contract.getContractId(),
                                                                                        Integer.parseInt(
                                                                                            humanDamage),
                                                                                        Integer.parseInt(
                                                                                            buildingDamage),
                                                                                        Integer.parseInt(
                                                                                            surroundingDamage),
                                                                                        0, 0, 0, staff);
                                                                                    break calculateCompensation;
                                                                                }
                                                                            } else {
                                                                                System.out.println(
                                                                                        "죄송합니다. "
                                                                                                + FireAccidentCauseIndemnification.values()[
                                                                                                Integer.parseInt(
                                                                                                        indemnification)
                                                                                                        - 1].getExplanation()
                                                                                                + "로 인해 발생한 사고는 보험지급금 의무 면책 사유입니다.");
                                                                                continue findContract;
                                                                            }
                                                                        } else {
                                                                            System.out.println(
                                                                                    "목록에 있는 번호를 입력해 주세요.");
                                                                            continue judgementIndemnification;
                                                                        }
                                                                }
                                                                System.out.println(
                                                                        customer.getName()
                                                                                + "님의 총 보상 금액은 "
                                                                                + contract.getCompensationAmount()
                                                                                + "원 입니다.");
                                                                continue compensationManage;
                                                            }
                                                        } else {
                                                            System.out.println("죄송합니다. "
                                                                    + AccidentSubjectIndemnification.values()[
                                                                    Integer.parseInt(subject)
                                                                            - 1].getExplanation()
                                                                    + "로 인해 발생한 사고는 보험지급금 의무 면책 사유입니다.");
                                                            continue findContract;
                                                        }
                                                    } else {
                                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                                        continue judgementSubject;
                                                    }
                                                }

                                            } else {
                                                System.out.println(
                                                        "가입된 보험이 없습니다. 인수 심사 완료 후 다시 진행해 주세요.");
                                                continue compensationManage;
                                            }
                                        }
                                    } else if (select1.equals("2")) {
                                        continue selectWork;
                                    } else {
                                        System.out.println("목록에 있는 번호를 입력해 주세요.");
                                        continue compensationManage;
                                    }
                                }
                            case "q":
                                staff = null;
                                continue main;
                            default:
                                continue;
                        }


                }

            }

        }
    }
}
