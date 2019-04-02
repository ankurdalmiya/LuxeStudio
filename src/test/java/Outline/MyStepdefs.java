package Outline;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyStepdefs {
    WebDriver driver = null;

    @Given("^User is on home page of Luxe Studio$")
    public void userIsOnHomePageOfLuxeStudio() {
        System.setProperty("webdriver.gecko.driver", "E:\\GeckoDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://wetech.digital/demo/test/");
    }

    @When("^USer navigates to Appointments Section$")
    public void userNavigatesToAppointmentsSection() throws InterruptedException {
       // System.out.println("check");
        WebDriverWait wait = new WebDriverWait(driver, 20);
//      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Appointments')]")));
//
//      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Appointments')]")));
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[contains(text(),'Appointments')]")).click();
        Thread.sleep(8000);
    }

    @And("^Selects a day from the calendar$")

    public void selectsADayFromTheCalendar() throws ParseException {
      //  driver.switchTo().frame(driver.findElement(By.id("//div[@id='sln-salon']")));
        driver.findElement(By.id("sln_date")).click();
        String setDateStr = "24/04/2019"; //dd/mm/yyyy

        String currDateStr = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/table/thead/tr[1]/th[2]")).getText(); //MMMM yyyy
        System.out.println("Current Month is " + currDateStr);

        Date setDate = new SimpleDateFormat("dd/MM/yyyy").parse(setDateStr);

        Date currDate = new SimpleDateFormat("MMMM yyyy").parse(currDateStr);

        // joda date dependency
        int monthDiff = Months.monthsBetween(new DateTime(currDate).withDayOfMonth(1), new DateTime(setDate).withDayOfMonth(1)).getMonths();
        System.out.println(monthDiff);

        boolean isFuture = true;

        if (monthDiff<0)    {
            isFuture = false;
            monthDiff = -1*monthDiff;
             }

        for (int i=0; i<monthDiff; i++)
        {
            if (isFuture)
                driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/table/thead/tr[1]/th[3]")).click();
            else
                driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/table/thead/tr[1]/th[1]")).click();
        }

        String day;
        day = new SimpleDateFormat("dd").format(setDate);
        driver.findElement(By.xpath("//td[text()='"+ Integer.parseInt(day) +"']")).click();




        /*WebElement dateWidget = driver.findElement(By.id("sln_date"));
        dateWidget.click();
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
        for (WebElement cell : columns) {
            //Select 13th Date
            if (cell.getText().equals("13")) {
                cell.findElement(By.linkText("13")).click();
                break;
            }
        }*/
    }
    @And("^Selects an hour$")
    public void selectsAnHour() throws ParseException, InterruptedException {
        driver.findElement(By.id("sln_time")).click();
        String setTimeStr = "10:25";    //hh:mm
//        Date setTime = new SimpleDateFormat("hh:mm").parse(setTimeStr);
//        System.out.println("AAA BBB" + setTime);
//        int time = setTime.getHours();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/table/tbody/tr/td/span[11]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/table/tbody/tr/td/span[6]")).click();
    }

    @And("^Proceed towards the next Step$")
    public void proceedTowardsTheNextStep() throws InterruptedException {
        driver.findElement(By.id("sln-step-submit")).click();
        Thread.sleep(10000);
    }

    @Then("^User needs to select the need$")
    public void userNeedsToSelectTheNeed() {
        driver.findElement(By.xpath("//h2[contains(text(),'Facials')]")).click();

        String checkToBeSelected = "Dermalogica Express Facial";

        List<WebElement> checklist = driver.findElements(By.xpath("//input[@type='checkbox']"));
        System.out.println(" Number of checkboxes present " + checklist.size());

        for (int i=0; i<checklist.size(); i++)
        {
            System.out.println(checklist.get(i).getText());

        }
        driver.findElement(By.id("sln_services_650")).click();
        driver.findElement(By.id("sln_services_652")).click();
    }


    @Then("^User needs to select any additional treatments if required$")
    public void userNeedsToSelectAnyAdditionalTreatmentsIfRequired() {
        driver.findElement(By.xpath("//h2[contains(text(),'Nails')]")).click();
        driver.findElement(By.id("sln_services_755")).click();
    }

    @And("^An Assistant is also chosen by the user$")
    public void anAssistantIsAlsoChosenByTheUser() throws InterruptedException {
   //     driver.switchTo().frame(driver.findElement(By.id("salon-step-attendant")));
//        Thread.sleep(3000);
//        WebElement radio1= driver.findElement(By.xpath("//div[contains(@class,'col-xs-12 col-sm-1 sln-radiobox sln-steps-check sln-attendant-check')]//label[contains(@for,'sln_attendant_256')]"));
//        radio1.click();
//        or
     //   WebDriverWait wait1 = new WebDriverWait(driver, 10);
    //    WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/section/div/div[1]/div/div/div/div[2]/div/div/div/div/div/div/form/div/div[1]/div/div[3]/div[1]/label")));
     //   element1.click();
    }

    @Then("^User needs to either Login or Checkout as a Guest$")
    public void userNeedsToEitherLoginOrCheckoutAsAGuest() throws InterruptedException {
        driver.findElement(By.name("login_name")).sendKeys("aaabbb@ccc.com");
        driver.findElement(By.name("login_password")).sendKeys( "aaabbb");
        driver.findElement(By.name("submit_details")).click();
        Thread.sleep(2000);

    }

    @And("^User can apply any Coupon Code also, if applicable$")
    public void userCanApplyAnyCouponCodeAlsoIfApplicable() {

    }

    @Then("^User needs to Finalize the booking$")
    public void userNeedsToFinalizeTheBooking() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("sln-step-submit")).click();
    }

    @And("^Complete the booking$")
    public void completeTheBooking() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/section/div/div[1]/div/div/div/div[2]/div/div/div/div/div/div/div[1]/div[2]/div/div/a")).click();
        Thread.sleep(2000);
        driver.close();

    }
}
