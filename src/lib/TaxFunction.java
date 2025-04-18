package lib;

public class TaxFunction {

	private static final int BASIC_PTKP = 54000000;
	private static final int MARRIED_PTKP = 4500000;
	private static final int CHILD_PTKP = 1500000;
	private static final int MAX_CHILDREN = 3;
	private static final double TAX_RATE = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		int effectiveChildren = Math.min(numberOfChildren, MAX_CHILDREN);

		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		int totalPTKP = BASIC_PTKP;

		if (isMarried) {
			totalPTKP += MARRIED_PTKP;
		}
		totalPTKP += CHILD_PTKP * effectiveChildren;

		int taxableIncome = totalIncome - deductible - totalPTKP;
		if (taxableIncome <= 0) return 0;

		return (int) Math.round(taxableIncome * TAX_RATE);
	}
}