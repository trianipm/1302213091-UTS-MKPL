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

	// Memeriksa apakah jumlah bulan kerja melebihi 12 bulan
	private static void checkWorkingMonths(int numberOfMonthWorking) {
		if (numberOfMonthWorking > 12) {
			throw new IllegalArgumentException("Error: More than 12 months working per year");
		}
	}

	// Membatasi jumlah anak maksimal yang diproses menjadi 3
	private static int limitNumberOfChildren(int numberOfChildren) {
		return Math.min(numberOfChildren, 3);
	}

	// Menghitung penghasilan tidak kena pajak
	private static int calculateTaxExemption(boolean isMarried, int numberOfChildren) {
		int taxExemption = 54000000; // Penghasilan tidak kena pajak default

		if (isMarried) {
			taxExemption += 4500000; // Tambahan penghasilan tidak kena pajak jika menikah
			taxExemption += numberOfChildren * 1500000; // Tambahan penghasilan tidak kena pajak per anak
		}

		return taxExemption;
	}

}
