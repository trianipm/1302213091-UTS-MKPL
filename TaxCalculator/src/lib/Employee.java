package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

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

	private List<String> childNames;
	private List<String> childIdNumbers;

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

		childNames = new LinkedList<>();
		childIdNumbers = new LinkedList<>();
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setDayJoined(int dayJoined) {
		this.dayJoined = dayJoined;
	}

	public int getDayJoined() {
		return dayJoined;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean getGender() {
		return gender;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setMonthlySalary(int grade) {
		monthlySalary = calculateGradeSalary(grade);
	}

	private int calculateGradeSalary(int grade) {
		final int GRADE_1_SALARY = 3000000;
		final int GRADE_2_SALARY = 5000000;
		final int GRADE_3_SALARY = 7000000;
		final double FOREIGNER_MULTIPLIER = 1.5;

		int baseSalary;
		switch (grade) {
			case 1:
				baseSalary = GRADE_1_SALARY;
				break;
			case 2:
				baseSalary = GRADE_2_SALARY;
				break;
			case 3:
				baseSalary = GRADE_3_SALARY;
				break;
			default:
				throw new IllegalArgumentException("Invalid grade provided.");
		}

		int salary = isForeigner ? (int) (baseSalary * FOREIGNER_MULTIPLIER) : baseSalary;

		return salary;
	}

	public int getMonthlySalary() { // Menambahkan method getMonthlySalary untuk mendapatkan gaji bulanan
		return monthlySalary;
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				spouseIdNumber.equals(""), childIdNumbers.size());
	}

	public int getNumberOfChildren() {
		return childNames.size(); // Mengembalikan jumlah anak dari list childNames
	}

	public boolean isMarried() {
		return (spouseName != null && !spouseName.isEmpty()); // Mengembalikan true jika nama pasangan tidak null atau
																// tidak kosong
	}
}
