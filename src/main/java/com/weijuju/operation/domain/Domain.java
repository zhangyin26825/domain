//package com.weijuju.operation.domain;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.io.File;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by zhangyin on 2016/8/24.
// */
//public class Domain {
//
//    private WebDriver driver;
//    public static ChromeDriverService service;
//    private String baseUrl;
//    private boolean acceptNextAlert = true;
//    private StringBuffer verificationErrors = new StringBuffer();
//
//    private String newDomain;
//
//    public static void main(String[] args) {
//        try {
//            Domain d=new Domain("sf-schoolseason");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Domain(String newDomain) throws Exception{
//        this.newDomain = newDomain;
//        try {
//            init();
//            login();
//           // toWeijujuManage();
//            boolean b = searchDomainExist();
//            if(b){
//                System.out.println("域名已存在");
//            }else{
//                addDomain();
//                System.out.println("域名新建");
//            }
//        } catch (Exception e) {
//            throw e;
//        }finally {
//            try {
//                driver.close();
//                service.stop();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public  void addDomain(){
//        driver.findElement(By.id("create-record")).click();
//        System.out.println("点击 添加按钮");
////        WebDriverWait wait = new WebDriverWait(driver, 20);
////        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.mini.ac_input")));
////        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("medium-130 ac_input")));
//        driver.findElement(By.cssSelector("input.mini.ac_input")).clear();
//        driver.findElement(By.cssSelector("input.mini.ac_input")).sendKeys(newDomain);
//        driver.findElement(By.cssSelector("input.medium-130.ac_input")).clear();
//        driver.findElement(By.cssSelector("input.medium-130.ac_input")).sendKeys("");
//        driver.findElement(By.cssSelector("button.button.secondary.tiny.save")).click();
//        try {
//            //这个确认框，有时候有，有时候没有
//            driver.findElement(By.id("pass-confirmation")).click();
//        } catch (Exception e) {
//        }
//    }
//
//
//    public void init() {
//        try {
// //            driver = new FirefoxDriver();
//            System.setProperty(
//                    "webdriver.chrome.driver",
//                    "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
//            service = new ChromeDriverService.Builder()
//                    .usingDriverExecutable(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"))
//                    .usingAnyFreePort().build();
//            service.start();
//             //创建一个 Chrome 的浏览器实例
//            driver = new RemoteWebDriver(service.getUrl(),
//                    DesiredCapabilities.chrome());
//
//       //    driver= new HtmlUnitDriver(true);
//            baseUrl = "https://www.dnspod.cn";
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            driver.get(baseUrl + "/Login?default=email");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     *  登录
//     * @throws Exception
//     */
//    public void login() throws Exception {
//        new WebDriverWait(driver, 15 ).until( ExpectedConditions.presenceOfElementLocated(By.name("email")));
//       // By.tagName("input").
//                //driver.findElement(By.tagName("input")).findElement(By.name("email"))
//        driver.findElement(By.name("email")).clear();
//        driver.findElement(By.name("email")).sendKeys("luofan@365huaer.com");
////        WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/form/div[1]/input"));
////        element.clear();
////        boolean displayed = ((HtmlUnitWebElement) element).isDisplayed();
////        boolean enabled = ((HtmlUnitWebElement) element).isEnabled();
////        element.sendKeys("luofan@365huaer.com");
//
//        driver.findElement(By.name("password")).clear();
//        driver.findElement(By.name("password")).sendKeys("apusic123");
////        WebElement element1 = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/form/div[2]/input"));
////        element1.clear();
////        element1.sendKeys("apusic123");
//
//        driver.findElement(By.cssSelector("button.btn.success")).click();
//        //System.out.println("点击确认按钮");
//        new WebDriverWait(driver, 15 ).until( ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='main-body']/div/div/div/ul/li[2]/a/span")));
//        driver.findElement(By.xpath("//div[@id='main-body']/div/div/div/ul/li[2]/a/span")).click();
//        driver.switchTo().frame(0);
//        new WebDriverWait(driver, 15 ).until( ExpectedConditions.presenceOfElementLocated(By.linkText("weijuju.com")));
//        driver.findElement(By.linkText("weijuju.com")).click();
//       // driver.switchTo().frame(0);
//    }
//
//    /**
//     *  跳转到 weijuju.com根域名管理页面
//     */
//    public void toWeijujuManage(){
//        driver.navigate().to("https://www.dnspod.cn/console/dns/weijuju.com");
//    }
//    public boolean searchDomainExist(){
//        driver.findElement(By.cssSelector("input.record_search")).clear();
//        driver.findElement(By.cssSelector("input.record_search")).sendKeys(newDomain);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        WebElement element=driver.findElement(By.id("record-list"));
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        List<WebElement> entry = element.findElements(By.className("entry"));
//        if(entry.isEmpty()){
//            return false;
//        }
//        return true;
//    }
//
//    public void searchDomain(){
////        driver.findElement(By.xpath("//div[@id='main-body']/div/div/div/ul/li[2]/a/span")).click();
////        driver.findElement(By.linkText("weijuju.com")).click();
////        driver.findElement(By.id("page")).click();
//
//        driver.findElement(By.cssSelector("input.record_search")).clear();
//        driver.findElement(By.cssSelector("input.record_search")).sendKeys("sf-schoolseason");
//        driver.findElement(By.id("create-record")).click();
//        driver.findElement(By.cssSelector("input.mini.ac_input")).clear();
//        driver.findElement(By.cssSelector("input.mini.ac_input")).sendKeys("df");
//        driver.findElement(By.xpath("(//input[@value=''])[2]")).click();
//        driver.findElement(By.xpath("//input[@value='']")).click();
//        driver.findElement(By.cssSelector("input.mini.ac_input")).clear();
//        driver.findElement(By.cssSelector("input.mini.ac_input")).sendKeys("");
//        driver.findElement(By.xpath("//div[@id='record-list']/div/div/table/tbody/tr/th[2]/button")).click();
//
//    }
//
//
//
//    private boolean isElementPresent(By by) {
//        try {
//            driver.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
//    private boolean isAlertPresent() {
//        try {
//            driver.switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException e) {
//            return false;
//        }
//    }
//
//    private String closeAlertAndGetItsText() {
//        try {
//            Alert alert = driver.switchTo().alert();
//            String alertText = alert.getText();
//            if (acceptNextAlert) {
//                alert.accept();
//            } else {
//                alert.dismiss();
//            }
//            return alertText;
//        } finally {
//            acceptNextAlert = true;
//        }
//    }
//}
