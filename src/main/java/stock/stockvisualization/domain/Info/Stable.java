package stock.stockvisualization.domain.Info;

import stock.stockvisualization.domain.company.Company;

public class Stable {
    public final String stable(Company company){
        float 부채비율 =(Float.parseFloat(String.valueOf(company.account_nm.equals("부채총계")))) / (Float.parseFloat(String.valueOf(company.account_nm.equals("자본총계"))))* 100;
        if(부채비율 < 50){
            return "우량";
        }else if( 50 <= 부채비율 && 부채비율 < 90){
            return "약간 우량";
        }else if( 90 <= 부채비율 && 부채비율 < 110){
            return "평범";
        }else if( 110 <= 부채비율 && 부채비율 < 150){
            return "약간 불량";
        }else{
            return "불량";
        }
    }

}
