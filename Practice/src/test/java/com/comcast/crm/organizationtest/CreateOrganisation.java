package com.comcast.crm.organizationtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.generic.fileutility.ExcelUtility;
import com.comcast.generic.fileutility.PropertyFileUtility;
import com.comcast.generic.webdriverutility.JavaUtility;
import com.comcast.generic.webdriverutility.UtilityClassObject;
import com.comcast.generic.webdriverutility.WebDriverUtility;

public class CreateOrganisation extends BaseClass {
	@Test(groups = "Smoke")
	public void CreateOrg() throws IOException {
		// To read data from Excel
		UtilityClassObject.getTest().log(Status.INFO,"read data from excel");
		String orgName = e.getDataFromExcel("org", 1, 2) + j.getRandomNumber();

		// navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO,"navigate to organisation pages");
		HomePage h = new HomePage(driver);
		h.getOrglink().click();

		// Click on create Organization button
		UtilityClassObject.getTest().log(Status.INFO,"navigate to create organisation pages");
		OrganizationsPage o = new OrganizationsPage(driver);
		o.getCreateNewOrg().click();

		// enter all details and create new organisation
		UtilityClassObject.getTest().log(Status.INFO,"create new organisation");
		CreateNewOrganizationPage co = new CreateNewOrganizationPage(driver);
		co.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+ "create new  organisation");

		// verify header orgName info Expected Result
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		String actualOrgName = oi.getHeadermsg().getText();
		boolean status = actualOrgName.contains(orgName);
		Assert.assertEquals(status, true);
		/*
		 * if (actualOrgName.contains(orgName)) { System.out.println(orgName +
		 * "is verified==>PASS"); } else { System.out.println(orgName +
		 * "is not verified==>FAIL"); }
		 */
	}

	@Test(groups = "Regression")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException {
		// To read data from Excel
		String orgName = e.getDataFromExcel("org", 4, 2) + j.getRandomNumber();
		String Industry = e.getDataFromExcel("org", 4, 3);
		String Type = e.getDataFromExcel("org", 4, 4);

		// navigate to Organization module
		HomePage h = new HomePage(driver);
		h.getOrglink().click();

		// Click on create Organization button
		OrganizationsPage o = new OrganizationsPage(driver);
		o.getCreateNewOrg().click();

		// enter all details and create new organisation
		CreateNewOrganizationPage co = new CreateNewOrganizationPage(driver);
		co.createOrg(orgName, Industry, Type);

		// verify Industry and Type
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustry.contains(Industry)) {
			System.out.println(Industry + "Information is Verified==>PASS");
		} else {
			System.out.println(Industry + "Information is not Verified==>FAIL");
		}

		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actualType.contains(Type)) {
			System.out.println(Type + "Information is Verified==>PASS");
		} else {
			System.out.println(Type + "Information is not Verified==>FAIL");
		}
	}

	@Test(groups = "Regression")
	public void createOrgWithPhno() throws EncryptedDocumentException, IOException {
		// To read data from Excel
		String orgName = e.getDataFromExcel("org", 7, 2) + j.getRandomNumber();
		String phno = e.getDataFromExcel("org", 7, 3);

		// navigate to Organization module
		HomePage h = new HomePage(driver);
		h.getOrglink().click();

		// Click on create Organization button
		OrganizationsPage o = new OrganizationsPage(driver);
		o.getCreateNewOrg().click();

		// enter all details and create new organisation
		CreateNewOrganizationPage co = new CreateNewOrganizationPage(driver);
		co.createOrg(orgName, phno);

		// verify phoneNumber
		String phnoinfo = driver.findElement(By.id("mouseArea_Phone")).getText();
		if (phnoinfo.contains(phno)) {
			System.out.println(phno + "is created==>PASS");
		} else {
			System.out.println(phno + "is not Created==>FAIL");
		}
	}
	@Test
	public void createOrgfailtest()
	{
		System.out.println("Failed to create Organisation");
		Assert.fail();
	}
}
