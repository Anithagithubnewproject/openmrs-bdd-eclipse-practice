package utils;

import pages.LoginPage;

public class SanityCheck {
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.open(ConfigReader.baseUiUrl());
        loginPage.login(ConfigReader.username(), ConfigReader.password());

        System.out.println("Login successful: " + loginPage.isHomePageDisplayed());

        DriverFactory.quitDriver();
    }
}
