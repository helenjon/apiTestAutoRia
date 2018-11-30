package theinternetherokuappcom;

import org.testng.Assert;
import resourcemethods.SetupTestDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import resourcemethods.*;


import java.net.MalformedURLException;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.testng.Reporter.log;


public class testcases {
    public SetupTestDriver setup;
    public WebDriver driver;
    public Actions builder;
    pageselements pageselements = new pageselements();
    public String result;
    baseresourceclass baseresources = new baseresourceclass();



   @BeforeMethod (alwaysRun = true)
   @Parameters({"os","browser", "baseUrl", "node"})
    public void beforeMethod(String os,String browser, String baseUrl, String node) throws MalformedURLException {
       setup = new SetupTestDriver(os, browser, baseUrl, node);
       driver = setup.getDriver();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        baseresources.afterMethod(driver, result);
        driver.close();
    }

    @Test  //done
    public void selectdropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        //Element which needs to drag.
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByValue("1");
    }


    @Test  //done
    public void jqueryuimenu() {
        driver.get("https://the-internet.herokuapp.com/jqueryui/menu");
        driver.findElement(By.id("ui-id-3")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-4")));
        driver.findElement(By.id("ui-id-4")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-5")));
        driver.findElement(By.id("ui-id-5")).click();
    }

    @Test //done
    public void floatingmenu() {
        driver.get("https://the-internet.herokuapp.com/floating_menu");
        driver.findElement(By.xpath("//a[@href='#news']")).click();
        String currentURL = driver.getCurrentUrl();
        String[] res = currentURL.split("#");
        Assert.assertEquals(res[1], "news");
    }

/*

    @Test  //done
    public void forgot_password() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/forgot_password");
        driver.findElement(By.name("email")).sendKeys("olena_mokina@epam.com");
        driver.findElement(By.xpath(".//*[@id='form_submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));
        String sonfirmation_message = driver.findElement(By.id("content")).getText();
        Assert.assertEquals(sonfirmation_message, "Your e-mail's been sent!");
        driver.close();
    }

    @Test  //done
    public void logintest_pass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/login");
        pageselements.getUserName(driver).sendKeys("tomsmith");
        pageselements.getPassword(driver).sendKeys("SuperSecretPassword!");
        pageselements.getLogin(driver).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@href='/logout']")));
        String sonfirmation_message = driver.findElement(By.id("flash")).getAttribute("innerText");
        Assert.assertEquals(sonfirmation_message.substring(0, sonfirmation_message.length() - 2), "You logged into a secure area!");
        driver.close();
    }

    @Test //done
    public void logintest_failed() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/login");
        testdatareadfromfile test = new testdatareadfromfile("src/test/resources/test_data_for_login_form.csv");
        List<String> username = test.getusername();
        List<String> password = test.getPassword();
        List<String> expectedmessage = test.getExpectedmessage();
        for (int i = 0; i < expectedmessage.size(); i++) {
            pageselements.getUserName(driver).sendKeys(username.get(i));
            pageselements.getPassword(driver).sendKeys(password.get(i));
            pageselements.getLogin(driver).click();
            String sonfirmation_message = driver.findElement(By.id("flash")).getAttribute("innerText");
            Assert.assertEquals(sonfirmation_message.substring(0, sonfirmation_message.length() - 2), expectedmessage.get(i));
        }
        driver.quit();
    }


    @DataProvider(name = "dataforlogintest")
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                { "wrong username", "SuperSecretPassword!", " Your username is invalid!" },
                { "tomsmith", "wrong password", " Your password is invalid!" },
                { "wrong username", "wrong password", " Your username is invalid!" },

        };
    }

    @Test (dataProvider = "dataforlogintest")  //done
    public void logintest_failed_withdataprovider (String username, final String password,  final String expectedmessage) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/login");
        pageselements.getUserName(driver).sendKeys(username);
        pageselements.getPassword(driver).sendKeys(password);
        pageselements.getLogin(driver).click();
        String sonfirmation_message = driver.findElement(By.id("flash")).getAttribute("innerText");
        Assert.assertEquals(sonfirmation_message.substring(0, sonfirmation_message.length() - 2), expectedmessage);
        //driver.close();
    }


    @Test //done
    public void iframe() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/iframe");
        WebElement iFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrame);
        driver.findElement(By.xpath("html/body[@id='tinymce']")).sendKeys("my test");
        driver.switchTo().defaultContent();
        driver.findElement(By.id("mceu_15-open")).click();
        driver.quit();
    }

    @Test //done
    public void nested_frames() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        // go to frame-left and check text in it
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String leftframetext = driver.findElement(By.xpath("//html/body")).getAttribute("outerText");
        Assert.assertEquals(leftframetext, "LEFT");
        // go to frame-right and check text in it
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        String rightframetext = driver.findElement(By.xpath("//html/body")).getAttribute("outerText");
        Assert.assertEquals(rightframetext, "RIGHT");
        // go to frame-bottom and check text in it
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        String bottomframetest = driver.findElement(By.xpath("//html/body")).getAttribute("outerText");
        Assert.assertEquals(bottomframetest, "BOTTOM");
        driver.quit();
    }


    @Test  //done
    public void status_codes() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/status_codes");
  //list of urls on page (for testing)
        ArrayList<String> listofurls = pageselements.listoflinks(driver);
        testrequestresponse requesttest = new testrequestresponse();
        for (String url : listofurls) {
            try {
                Integer response = requesttest.test(url);
                Integer expectedvalie = Integer.parseInt(url.substring(url.length() - 3, url.length()));
                Assert.assertEquals(expectedvalie, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.close();
    }


    @Test // (enabled = false) //done
    public void simple_alert() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        pageselements.getJSAlertbutton(driver).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        //System.out.println(pageselements.getActionResult(driver).getAttribute("textContent"));
        String result = pageselements.getActionResult(driver).getAttribute("innerText");
        Assert.assertEquals(result,"You successfuly clicked an alert");
      //  driver.close();
    }

    @Test (enabled = false) //done
    public void confirmation_alert() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        pageselements.getJSConfirmButton(driver).click();
  //      System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
        String result = pageselements.getActionResult(driver).getAttribute("innerText");
   //     System.out.println(pageselements.getActionResult(driver).getAttribute("innerText"));
        Assert.assertEquals(result, "You clicked: Cancel");
        driver.close();
    }

    @Test //done
    public void prompt_alert() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        pageselements.getJSPromptButton(driver).click();
        //System.out.println(driver.switchTo().alert().getText());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().alert().sendKeys("Some text");
        driver.switchTo().alert().accept();
        String result = pageselements.getActionResult(driver).getAttribute("outerText");
        Assert.assertEquals(result, "You entered: Some text");
        driver.close();
    }


    @Test // (enabled = false) //done
    public void hovers(){
        driver = new ChromeDriver();
        // add actions to support mouse moving;
        Actions action = new Actions(driver);
        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement userAvataar = driver.findElement(By.xpath("//img[@alt='User Avatar']"));
        action.moveToElement(userAvataar).build().perform();
    }


    @Test  // done
    public void horizontal_slider(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
        WebElement slider = driver.findElement(By.cssSelector("input"));
        driver.manage().window().maximize();
        int sliderWidth = slider.getSize().getWidth();
        System.out.println(sliderWidth);
        Actions moveslider = new Actions(driver);
        //two variants of moverent of slider
        /// ????? can't move to proper place
        moveslider.click().dragAndDropBy(slider, 0, 0).release().build().perform();
        //moveslider.clickAndHold(slider).moveByOffset(sliderWidth, 0).release().build().perform();
        driver.close();

    }
    @Test  // done  methods dragAndDropBy(slider, 0, 0)  works well just for Jquery sliders.
    public void jqueryui_com_slider(){
        driver = new ChromeDriver();
        driver.get("http://jqueryui.com/slider/");
        driver.manage().window().maximize();
        driver.switchTo().frame(0);
        WebElement slider = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
        int sliderWidth = slider.getSize().getWidth();
        System.out.println(sliderWidth);
        Actions moveslider = new Actions(driver);
        //two variants of moverent of slider
        /// ????? can't move to proper place
        moveslider.click().dragAndDropBy(slider, sliderWidth, 0).release().build().perform();
        //moveslider.clickAndHold(slider).moveByOffset(sliderWidth, 0).release().build().perform();
        driver.close();

    }


    @Test // done
    public void keyPresses(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/key_presses");
        Actions action = new Actions(driver);
        // action.sendKeys(Keys.NUMPAD0).perform();
        action.sendKeys(Keys.chord("a")).build().perform();
        action.sendKeys(Keys.ADD).build().perform();
        driver.close();
        }

    @Test // done - No Class or ID attributes to signify groupings of rows and columns - in the table
    public void sortableDataTables() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");
        // get list of non sorted emails
        List<WebElement> emails = driver.findElements(By.cssSelector("#table1>tbody>tr>:nth-child(3)"));
        List<String> nonsortedemails = new ArrayList<String>();
        for (WebElement email:emails){
            nonsortedemails.add(email.getText());
        }
        //find header or email column and click on it to sort
        driver.findElement(By.cssSelector("#table1>thead>tr>:nth-child(3)")).click();
        emails = driver.findElements(By.cssSelector("#table1>tbody>tr>:nth-child(3)"));
        // get list sorted emails
        List<String> sortedemails = new ArrayList<String>();
        for (WebElement email:emails){
            sortedemails.add(email.getText());
        }
        Assert.assertEquals( !(Ordering.natural().isOrdered(nonsortedemails)), Ordering.natural().isOrdered(sortedemails) );
        driver.close();

    }

    @Test // done - Class and ID attributes to signify groupings of rows and columns - in the table
    public void sortableDataTableswithClass() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");
        // get list of non sorted emails
        List<WebElement> emails = driver.findElements(By.cssSelector("td.email"));
        List<String> nonsortedemails = new ArrayList<String>();
        for (WebElement email:emails){
            nonsortedemails.add(email.getText());
        }
        //find header or email column and click on it to sort
        driver.findElement(By.cssSelector("span.email")).click();
        emails = driver.findElements(By.cssSelector("td.email"));
        // get list sorted emails
        List<String> sortedemails = new ArrayList<String>();
        for (WebElement email:emails){
            sortedemails.add(email.getText());
        }
        // was used google method for cheking sorting
        Assert.assertEquals( !(Ordering.natural().isOrdered(nonsortedemails)), Ordering.natural().isOrdered(sortedemails) );
        driver.close();
    }

    @Test //done
    public void multipleWindows(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        //open new tab
        driver.findElement(By.cssSelector("a[href='/windows/new']")).click();
        //wait till it opend
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //get list of available tabs ot set or to List
        //Set <String> set = driver.getWindowHandles();
        //List<String> setlist = new ArrayList<String>();
        //setlist.addAll(set);
        ArrayList <String> setlist = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(setlist.get(1));
        Assert.assertEquals(driver.getTitle(), "New Window");
        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "New Window");
        driver.close();
        }


    @Test //done
    public void checkboxes(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkboxeslist = driver.findElements(By.cssSelector("input"));

        if ( !checkboxeslist.get(0).isSelected() )
        { checkboxeslist.get(0).click();}
            checkboxeslist.get(1).click();
        driver.close();
    }

    @Test //done
    public void notificationMessage() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        driver.findElement(By.cssSelector("a[href='/notification_message']")).click();
        String notifmessage = driver.findElement(By.cssSelector("#flash")).getText();
        System.out.println(notifmessage.substring(0, notifmessage.length()-1));
        driver.close();
    }


    @Test //done
    public void runscroll_floating_menu(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/floating_menu");
        driver.manage().window().maximize();
        // scroll down with JS script run
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
        // scroll down with actions methods
        //Actions actions = new Actions(driver);
        //actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        System.out.println(driver.findElement(By.cssSelector("a[href='#news'] ")).getText());
        driver.findElement(By.cssSelector("a[href='#news'] ")).click();
        Assert.assertEquals("https://the-internet.herokuapp.com/floating_menu#news", driver.getCurrentUrl());
        driver.close();
    }

    @Test //done
    public void dynamic_controls_RemoveAdd() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[contains(text(), 'Remove')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Add')]")));
        String result = driver.findElement(By.cssSelector("#message")).getText();
        Assert.assertEquals(result, "It's gone!");
    }

    @Test //done
    public void dynamic_controls_EnableDisable() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[contains(text(), 'Enable')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement inputfield = driver.findElement(By.cssSelector("input[type='text']"));
        wait.until(ExpectedConditions.elementToBeClickable(inputfield));
        inputfield.sendKeys("test");
        driver.findElement(By.xpath("//button[contains(text(), 'Disable')]")).click();
        wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.cssSelector("#loading")),"style", "display: none;"));
        result = driver.findElement(By.cssSelector("#message")).getText();
        Assert.assertEquals(result, "It's disabled!");

    }


    @Test //done
    public void dynamic_loading_1() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        System.out.println(driver.findElement(By.cssSelector("#finish")).getAttribute("style"));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.cssSelector("#loading")), "style", "display: none;"));
        driver.findElement(By.cssSelector("#finish > h4")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish > h4")).getText(), "Hello World!");
        driver.close();
    }


    @Test  //done
    public  void uploadfile(){
        driver = new ChromeDriver();
        String uploadfilepath = "C:\\Pic\\Feeds-1.png";
        // Launch the Postmyimage Website
        driver.get("http://the-internet.herokuapp.com/upload");
        try {
            UploadChromeFile.uploadChromeFie(driver, uploadfilepath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        // Click on Submit Button
        driver.findElement(By.cssSelector("#file-submit")).click();
        result = driver.findElement(By.cssSelector(".example > h3")).getText();
        Assert.assertEquals(result, "File Uploaded!");
        driver.close();
    }


    @Test //done
    public void basic_auth (){
        String url = "the-internet.herokuapp.com/basic_auth";
        String  username = "admin";
        String password = "admin";
        String baseUrl="https://" + username + ":" + password + "@" + url;
        driver.get(baseUrl);
        result = driver.findElement(By.xpath("//p")).getText();
        Assert.assertEquals(result, "Congratulations! You must have the proper credentials.");
    }


    @Test //done
    public void broken_images() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/broken_images");
        List <WebElement> imagelibray = driver.findElements(By.cssSelector("div > img"));
        for (WebElement im : imagelibray){
            try {
                Reporter.log( im.getAttribute("currentSrc"));
                log("Test");
                log(Reporter.getCurrentTestResult() + "get current test tesults");
                softAssert.assertTrue(im.getAttribute("currentSrc").contains("/img/"));
            }
            catch(AssertionError ae){
                ae.printStackTrace();
            }
         }
        log("MY LOG ");
         softAssert.assertAll();

        driver.close();
    }


    @Test
    public void DragnDrop() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        //Element which needs to drag.
        WebElement From = driver.findElement(By.xpath("//div[@id='column-a']"));
        //Element on which need to drop.
        WebElement To = driver.findElement(By.id("column-b"));
        //Using Action class for drag and drop.
        Actions act = new Actions(driver);
        //Dragged and dropped.
        act.click();
        act.dragAndDrop(From, To).perform();
    }

        @Test
    public void exit_intent() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/exit_intent");
        WebElement e = driver.findElement(By.cssSelector("h3"));
        Actions action = new Actions(driver);
        action.moveToElement(e).moveByOffset(600, -1).build().perform();
        driver.findElement(By.xpath(".//*[@id='ouibounce-modal']/div[2]/div[3]/p")).click();
    }

    */


    }
























































