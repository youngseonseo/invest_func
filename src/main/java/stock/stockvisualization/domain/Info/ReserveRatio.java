package stock.stockvisualization.domain.Info;

import stock.stockvisualization.domain.company.Company;

public class ReserveRatio {

    public String reserveRatio(Company company){
        float 유보율 = (Float.parseFloat(String.valueOf(company.account_nm.equals("자본총계"))) - Float.parseFloat(String.valueOf(company.account_nm.equals("자본금")))) / Float.parseFloat(String.valueOf(company.account_nm.equals("자본금"))) * 100;
        String ans;
        if(유보율 >= 0){
            ans = "유보율이 " + 유보율 + "%로, ";
            if(유보율 < 100)
                 ans += "약간 안정적";
            else if(유보율 < 1000)
                ans += "안정적";
            else
                ans += "매우 안정적";
        }
        else{
            ans = "잠식율이 " + 유보율 + "%로, ";
            if(유보율 > -100)
                ans += "약간 불안정적";
            else if(유보율 > -1000)
                ans += "불안정적";
            else
                ans += "매우 불안정적";
        }
        return ans;
    }
}

