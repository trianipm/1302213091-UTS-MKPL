package lib;

public class Main {
    public static void main(String[] args) {
        // Membuat objek Employee
        Employee employee = new Employee("E001", "Triani", "Putri Mumpuni", "1302213091", "123 Street", 2020, 1, 1,
                false,
                true);

        // Mengatur gaji bulanan berdasarkan grade
        employee.setMonthlySalary(2); // Grade 2

        // Menampilkan informasi pegawai
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("Gender: " + (employee.getGender() ? "Perempuan" : "Laki-laki"));
        System.out.println("Address: " + employee.getAddress());
        System.out.println("Monthly Salary: Rp " + employee.getMonthlySalary());

        // Menghitung dan menampilkan jumlah pajak yang harus dibayarkan setahun
        int tax = TaxFunction.calculateTax(employee.getMonthlySalary(), 0, 12, 0, employee.isMarried(),
                employee.getNumberOfChildren());
        System.out.println("Yearly Tax: Rp " + tax);
    }
}
