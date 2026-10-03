package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestClass.BaseTestClass;

public class TestClass_1 extends BaseTestClass {
	

	
	@Test
	public void testMethod1() throws InterruptedException {
		System.out.println("Nagivating to Given URL...!");
		
		driver.get("https://www.google.com/");
		
		String expectedTitle = "Google";
		String actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle,expectedTitle,"Actual Title and Expected Title not mached!");
		Thread.sleep(5000);
		
	}
	
	@Test
	public void testMethod2() throws InterruptedException {
		
		 WebElement searchBar = driver.findElement(By.name("q"));
		 searchBar.sendKeys("Akhil Akkineni", Keys.ENTER);
		 
		 Thread.sleep(5000);
		 String title = driver.getTitle();
		 System.out.println("Page2 Title is: " + title);
		 
	}
	
	
}

