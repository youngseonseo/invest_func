package stock.stockvisualization.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import stock.stockvisualization.domain.company.Company;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@Data
@Slf4j
public class SeleniumService {

    private  WebDriver driver;
    private final String API_KEY = "12ed28c5a1ccad901a74e01b506d6690588f73f5";
    private final String COMPANY_INFO_URL = "https://opendart.fss.or.kr/api/company.json";
    private final String FINANCIAL_URL = "https://opendart.fss.or.kr/api/fnlttSinglAcnt.json";
    private  List<Company> company = new ArrayList<>();
    public SeleniumService(){

    }

    public void findCompanyInfo(String corp_code){
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get(FINANCIAL_URL
                +"?crtfc_key=" + API_KEY
                +"&corp_code=" + corp_code);

    }
    public Company findFinancialInfo(String corp_code, String bsns_year, String reprt_code) throws ParseException {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get(FINANCIAL_URL
                +"?crtfc_key=" + API_KEY
                +"&corp_code=" + corp_code
                +"&bsns_year=" + bsns_year
                +"&reprt_code=" + reprt_code);
        String pageSource = driver.getPageSource();

        //html -> json String
        String text = driver.findElement(By.xpath("/html/body/pre")).getText();

        //json String -> json
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(text);

        //extract list
        JSONArray list = (JSONArray) jsonObject.get("list");



        /**
         *     String name;
         *     Integer corpcode;
         *     int bsns_year;
         *     String account_nm;
         *     String fs_div;
         *     int thstrm_amount;
         *     int frmtrm_amount;
         */

            Company com = new Company();
            com.setCorpcode(Integer.parseInt(((JSONObject)list.get(0)).get("corp_code").toString()));
            com.setBsns_year(Integer.parseInt(((JSONObject)list.get(0)).get("bsns_year").toString()));
            com.setAccount_nm(((JSONObject)list.get(0)).get("account_nm").toString());
            com.setFs_div(((JSONObject)list.get(0)).get("fs_div").toString());

            // Integer.parse 사용을 위한 string 글자 자르기
            String thstrm_amount = ((JSONObject) list.get(0)).get("thstrm_amount").toString().replace(",", "");
            com.setThstrm_amount(Integer.parseInt(thstrm_amount.substring(0, thstrm_amount.length()-6)));
            String frmtrm_amount = ((JSONObject) list.get(0)).get("frmtrm_amount").toString().replace(",", "");
            com.setFrmtrm_amount(Integer.parseInt(frmtrm_amount.substring(0, frmtrm_amount.length()-6)));
            String account_nm = ((JSONObject) list.get(0)).get("account_nm").toString().replace(",", "");
            com.setThstrm_amount(Integer.parseInt(account_nm.substring(0, thstrm_amount.length()-6)));


        return com;
    }
    //
    public void xmlFindCode() throws ParserConfigurationException, IOException, SAXException {
        // xml 파싱 빌드업
        try {
            File file = new File("src/main/resources/corpCode/CORPCODE.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            log.info("Root Element :" + document.getDocumentElement().getNodeName());
            NodeList nList = document.getElementsByTagName("list");
            log.info("nList = " + nList.getLength());
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    log.info("corp_code : " + eElement.getElementsByTagName("corp_code").item(0).getTextContent());
                    log.info("corp_name : " + eElement.getElementsByTagName("corp_name").item(0).getTextContent());
                    log.info("modify_date : " + eElement.getElementsByTagName("modify_date").item(0).getTextContent());
                }
            }
        }
        catch(IOException e) {
        log.info("errors = {}",e);
    }
}
//
//    public void process() {
//
//
//
////        company.add("삼성전자");
////        company.add("sk하이닉스");
//
//        System.setProperty("webdriver.chrome.driver", "C:chromedriver.exe");
//        //크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)
//
//        //브라우저 선택
//        ChromeOptions options = new ChromeOptions();
//
//        //다운로드 경로
//        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//        chromePrefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "ChromeDriver" + File.separator + "BrowserDownloadedFiles");
//        options.setExperimentalOption("prefs", chromePrefs);
////        options.addArguments("--headless");
//        driver = new ChromeDriver(options);
//
//
////        for (String name : company) {
////            try {
////                searchAndDownload(name);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }
//
//
//
////        driver.quit();	//브라우저 닫기
//    }
//
//
//    /**
//     * data가져오기
//     */
//    private void searchAndDownload(String name) throws InterruptedException {
//
//
//        //삼성전자 분기보고서
//        driver.get(url);    //브라우저에서 url로 이동한다.
//        Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.
//        driver.findElement(By.xpath("//*[@id=\"textCrpNm2\"]")).sendKeys(name);
//        driver.findElement(By.xpath("//*[@id=\"searchForm2\"]/div[1]/div[3]/a")).click();
//        Thread.sleep(1000);
//
//        driver.findElement(By.xpath("//*[@id=\"li_01\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"publicTypeDetail_A003\"]")).click();
//        driver.findElement(By.xpath("//*[@id='searchForm']/div[2]/div[2]/a[1]")).click();
//
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@id=\"tbody\"]/tr[1]/td[3]/a")).click();
//
//
//
//        String currentUrl = driver.getCurrentUrl();
//        System.out.println("currentUrl = " + currentUrl);
//
//        //윈도우 창 스위칭
//        windowSwitch();
//
//
//        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[2]/div[2]/button[1]")).click();
//
//        windowSwitch();
//
//
//        String currentUrl1 = driver.getCurrentUrl();
//        System.out.println("currentUrl1 = " + currentUrl1);
//
//        driver.findElement(By.xpath("/html/body/div/div[3]/div/div/table/tbody/tr[2]/td[2]/a")).click();
//
//        driver.close();	//탭 닫기
//        windowSwitch();
//        driver.close();
//        windowSwitch();
//    }
//
//
//    private void windowSwitch() {
//        Set<String> windowHandles = driver.getWindowHandles();
//        for (String windowHandle : windowHandles) {
//            driver.switchTo().window(windowHandle);
//
//        }
//        log.info("CurrentUrl = {}", driver.getCurrentUrl());
//    }
}
