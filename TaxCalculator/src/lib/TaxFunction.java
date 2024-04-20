package lib;

public class TaxFunction {

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {
		// Mengecek apakah jumlah bulan kerja melebihi 12 bulan
		checkWorkingMonths(numberOfMonthWorking);

		// Membatasi jumlah anak maksimal yang diproses
		numberOfChildren = limitNumberOfChildren(numberOfChildren);

		// Menghitung penghasilan tidak kena pajak
		int taxExemption = calculateTaxExemption(isMarried, numberOfChildren);

		// Menghitung pajak
		int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible,
				isMarried, taxExemption, numberOfChildren);

		// Mengembalikan nilai pajak
		return Math.max(0, (int) Math.round(0.05 * taxableIncome));
	}

}
