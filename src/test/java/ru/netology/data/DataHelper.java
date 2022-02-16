package ru.netology.data;

import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {

    public DataHelper() {
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

    public static VerificationCode getVerificationCodeFor(String login) {
        String dataSQL = "SELECT code FROM auth_codes INNER JOIN users ON auth_codes.user_id = users.id " +
                "WHERE users.login = ? ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app"
                , "pass")) {
            var code = runner.query(conn, dataSQL, new ScalarHandler<String>(), login);
            return new VerificationCode(code);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void cleanDataFromTable() {
        String clearCodes = "DELETE FROM auth_codes;";
        String clearCards = "DELETE FROM cards;";
        String clearUsers = "DELETE FROM users;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app"
                , "pass")) {
            var code = runner.update(conn, clearCodes);
            var card = runner.update(conn, clearCards);
            var user = runner.update(conn, clearUsers);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

