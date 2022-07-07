package Models;

public class LoanModel {
    private final int nthMonth;
    private final double payment;

    public LoanModel(int nthMonth, double payment) {
        this.nthMonth = nthMonth;
        this.payment = payment;
    }

    public int getNthMonth() {
        return nthMonth;
    }

    public double getPayment() {
        return payment;
    }
}
