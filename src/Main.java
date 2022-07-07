import Models.LoanModel;

import java.sql.*;
import java.util.List;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost/yelo";
    static final String USER = "root";
    static final String PASS = "";


    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ) {
            removeAll(conn);//in order to delete all previously added records
            writeTo(conn);
            readFrom(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void removeAll(Connection conn) {
        String deleteSql = "Delete from Loans";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(deleteSql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void writeTo(Connection conn) throws SQLException {

        String insertSql = "INSERT INTO Loans VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSql);
        List<LoanModel> loans = LoanDividerUtil.calculateLoanOfEachMonth(18, 18);
        for (LoanModel loan : loans) {
            pstmt.setInt(1, loan.getNthMonth());
            pstmt.setDouble(2, loan.getPayment());
            pstmt.addBatch();
        }
        int[] result = pstmt.executeBatch();
//        System.out.println(Arrays.toString(result));
    }

    private static void readFrom(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        final String QUERY = "SELECT * FROM Loans";
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            // Retrieve by column name
            System.out.print( rs.getInt("nth_month")+" th month: ");
            System.out.print(rs.getInt("payment")+"₼\n");

        }
    }
}