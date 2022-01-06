# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class BuscarEnLaBibliotecaUM(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.google.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_buscar_en_la_biblioteca_u_m(self):
        driver = self.driver
        driver.get("https://um.edu.ar/")
        driver.find_element_by_link_text("Biblioteca").click()
        driver.find_element_by_link_text(u"Ir al Catálogo").click()
        # ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
        driver.find_element_by_id("busqueda").click()
        driver.find_element_by_id("busqueda").clear()
        driver.find_element_by_id("busqueda").send_keys("ingenieria de software")
        driver.find_element_by_id("form1").submit()
        self.assertEqual("INGENIERIA DE SOFTWARE", driver.find_element_by_link_text("INGENIERIA DE SOFTWARE").text)
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True
    
    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException as e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
