package lib;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Employee {

	private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;

    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames = new ArrayList<>();
    private List<String> childIdNumbers = new ArrayList<>();
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
                    int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.yearJoined = yearJoined;
        this.monthJoined = monthJoined;
        this.dayJoined = dayJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;
    }
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {
        switch (grade) {
            case 1 -> monthlySalary = GRADE_1_SALARY;
            case 2 -> monthlySalary = GRADE_2_SALARY;
            case 3 -> monthlySalary = GRADE_3_SALARY;
            default -> throw new IllegalArgumentException("Grade tidak valid: " + grade);
        }

        if (isForeigner) {
            monthlySalary *= 1.5;
        }
    }

	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
        LocalDate currentDate = LocalDate.now();
        monthWorkingInYear = (currentDate.getYear() == yearJoined)
                ? currentDate.getMonthValue() - monthJoined
                : 12;

        boolean hasNoSpouse = spouseIdNumber == null || spouseIdNumber.isEmpty();

        return TaxFunction.calculateTax(
                monthlySalary,
                otherMonthlyIncome,
                monthWorkingInYear,
                annualDeductible,
                hasNoSpouse,
                childIdNumbers.size()
        );
    }
}
