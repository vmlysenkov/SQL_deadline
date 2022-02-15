package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class PageTest {

    public PageTest() {
    }

    @Test
    void shouldLogIn() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo.getLogin());
        verificationPage.validVerify((DataHelper.VerificationCode) verificationCode);
    }

    @AfterEach
    void shouldCleanData() throws SQLException {
        DataHelper dataHelper = new DataHelper();
        dataHelper.cleanDataFromTable();
    }
}
