package stock.stockvisualization.domain;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import stock.stockvisualization.domain.Info.*;
import stock.stockvisualization.domain.company.Company;


import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class SeleniumController {
    private final SeleniumService seleniumTest;
    @GetMapping("/")
    public String home(){
        return "ok";
    }

    @PostMapping
    public String addCompany(){

        return "";
    }



    @PostConstruct
    public void crawlingTest() throws ParserConfigurationException, IOException, SAXException, ParseException {
        OpenRevenue a=new OpenRevenue();
        ReserveRatio b=new ReserveRatio();
        Roa c=new Roa();
        Size d=new Size();
        Stable e=new Stable();

//        seleniumTest.process();
//        seleniumTest.xmlFindCode();
        Company company= seleniumTest.findFinancialInfo("00126380","2022","11013");

        System.out.println(a.openRevenue(company));
        System.out.println(b.reserveRatio(company));
        System.out.println(c.roa(company));
        System.out.println(d.size(company));
        System.out.println(e.stable(company));




    }

}
