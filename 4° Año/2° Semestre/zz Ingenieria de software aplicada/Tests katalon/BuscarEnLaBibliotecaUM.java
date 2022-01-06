package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BuscarEnLaBibliotecaUM {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBuscarEnLaBibliotecaUM() throws Exception {
    driver.get("https://um.edu.ar/");
    driver.findElement(By.linkText("Biblioteca")).click();
    driver.findElement(By.linkText("Ir al Catálogo")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    driver.findElement(By.id("busqueda")).click();
    driver.findElement(By.id("busqueda")).clear();
    driver.findElement(By.id("busqueda")).sendKeys("ingenieria de software");
    driver.findElement(By.id("form1")).submit();
    assertEquals(driver.findElement(By.linkText("INGENIERIA DE SOFTWARE")).getText(), "INGENIERIA DE SOFTWARE");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
