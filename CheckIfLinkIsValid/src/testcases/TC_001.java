package testcases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_001 {
	
	public ChromeDriver driver;
	@BeforeMethod
	public void createDriverInstance() 
	{
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
	    driver = new ChromeDriver();
		driver.get("www.facebook.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void tcase1() throws Exception
	{
		int count = 0;
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for(WebElement ele: links) 
		{
			if(ele.getAttribute("href")!=null || !ele.getAttribute("href").contains("javascript")) 
			{
				boolean res = this.checkLink(ele.getAttribute("href"));
				if(res == false) 
				{
					count = count + 1;
				}
			}
		}
		System.out.println(count);
	}
	
	@AfterMethod
	public void dropDriverInstance() 
	{
		driver.quit();
	}
	
	public boolean checkLink(String linkURL)
	{
		boolean result = false;
		try {
		URL url = new URL(linkURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.connect();
		if(conn.getResponseCode()==200) 
		{
			result = true;
		}
		}catch(Exception e) {}
		
		return result;
	}
	

}
