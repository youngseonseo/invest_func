package stock.stockvisualization.domain.Info;

import stock.stockvisualization.domain.company.Company;

public class Size {
    public final String size(Company company){

        if((Float.parseFloat(String.valueOf(company.account_nm.equals("자본총계")))) >= 5000000000000L){
            return "대";
        }else if((Float.parseFloat(String.valueOf(company.account_nm.equals("자본총계")))) >= 500000000000L){
            return "중견";
        }else{
            return "중소";
        }
    }
}
