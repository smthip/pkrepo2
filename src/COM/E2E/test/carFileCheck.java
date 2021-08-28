package COM.E2E.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class carFileCheck {

	public static WebDriver driver = null;
	public static void main(String[] args) throws IOException, InterruptedException 
	{
	
		 
		 String carInputFilePath	= "C:\\E2E\\E2E_Automation\\src\\COM\\E2E\\data\\car_input.txt";
		 String carOutputFilePath	= "C:\\E2E\\E2E_Automation\\src\\COM\\E2E\\data\\car_output.txt";
		 String CarCheckUrl 		= "https://cartaxcheck.co.uk";
		 
		 String result="";
		 String carOutputContent="";
		 FileWriter writer = null; 
		
		 String carRegistration=null;
		
		 System.setProperty("webdriver.chrome.driver","C:\\E2E\\E2E_Automation\\chromedriver.exe");
		 driver= new ChromeDriver(); 
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 
		 File file= new File(carInputFilePath);
		 Scanner scan= new Scanner(file);
		 
		 while(scan.hasNextLine())
		 {
			 carRegistration = scan.next();
			 driver.navigate().to(CarCheckUrl);
			 driver.findElement(By.xpath("//*[@id=\'vrm-input\']")).sendKeys(carRegistration);
			 driver.findElement(By.linkText("Get a Full Check")).click();
			 driver.findElement(By.xpath("//*[@class='jsx-1164392954 ']")).click();
			 Thread.sleep(2000);
			 Boolean text= driver.findElement(By.tagName("H1")).isDisplayed();
			 if(text)
				 result ="Vehicle Found";
			 else 
			 { result = "Vehicle Not Found";
			 	 driver.findElement(By.xpath("//*[@id=\'m\']/div[3]/div/div/dl/div/dd[2]/a")).click();	
			 }
			 String model = driver.findElement(By.xpath("//*[@id=\'m\']/div[2]/div[1]/div/div[2]/dl[2]/dd")).getText();
			 String year = driver.findElement(By.xpath("//*[@id=\'m\']/div[2]/div[1]/div/div[2]/dl[3]/dd")).getText();
			 String colour = driver.findElement(By.xpath("//*[@id=\'m\']/div[2]/div[1]/div/div[2]/dl[4]/dd")).getText();
			 
			
			 carOutputContent=carOutputContent+ (carRegistration +"\t"+model  +"\t"+ year +"\t"+ colour +"\t" +result + "\n");
			
		 }
		 writer= new FileWriter(carOutputFilePath);
		 writer.write(carOutputContent);
		 scan.close();
		 writer.close();
		 driver.quit();
	}
	

}
