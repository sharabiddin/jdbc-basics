import Models.LoanModel;

import java.util.ArrayList;
import java.util.List;

public class LoanDividerUtil {

    static List<LoanModel> calculateLoanOfEachMonth(int monthCount, double total) {

        List<LoanModel> loans = new ArrayList<>();


        int roundedValue = (int) total / monthCount;
        int roundedSum = roundedValue * monthCount;

        double lastMonth = (total - roundedSum)+roundedValue;
        for (int i = 0; i < monthCount - 1; i++) {
            loans.add(new LoanModel(i + 1, roundedValue));
        }

        loans.add(new LoanModel(monthCount, lastMonth));

        return loans;
    }

}
