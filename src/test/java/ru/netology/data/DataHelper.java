package ru.netology.data;

import lombok.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataHelper {

    private DataHelper() throws SQLException {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static PreparedStatement getVerificationCodeFor() throws SQLException {
        String dataSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        PreparedStatement preparedVerificationCode = conn.prepareStatement(dataSQL);
        return preparedVerificationCode;
    }
}
