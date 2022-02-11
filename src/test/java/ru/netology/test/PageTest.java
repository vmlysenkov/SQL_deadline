package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class PageTest {

    public PageTest() throws SQLException {
    }

    @Test
    void shouldLogIn() throws SQLException {
        open("http://0.0.0.0:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerify((DataHelper.VerificationCode) verificationCode);
    }
}
