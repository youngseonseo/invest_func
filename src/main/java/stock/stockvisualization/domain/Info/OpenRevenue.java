package stock.stockvisualization.domain.Info;

import stock.stockvisualization.domain.company.Company;


public class OpenRevenue {
    public final String openRevenue(Company company){
        // 증가율 : (당기 / 전기 - 1) * 100
        float 영업이익증가율 = (((float)company.thstrm_amount/ (float)company.frmtrm_amount - 1) * 100);
        String diff_ans = "";
        if(영업이익증가율 < -100)
            diff_ans += "심각한";
        else if(-100 < 영업이익증가율 && 영업이익증가율 <= -70)
            diff_ans += "매우 낮은";
        else if(-70 < 영업이익증가율 && 영업이익증가율 <= -30)
            diff_ans += "낮은";
        else if(-30 < 영업이익증가율 && 영업이익증가율 <= -10)
            diff_ans += "약간 낮은";
        else if(-10 < 영업이익증가율 && 영업이익증가율 <= 10)
            diff_ans += "평범한";
        else if(10 < 영업이익증가율 && 영업이익증가율 <= 30)
            diff_ans += "약간 높은";
        else if(30 < 영업이익증가율 && 영업이익증가율 <= 70)
            diff_ans += "높은";
        else if(70 < 영업이익증가율 && 영업이익증가율 <= 100)
            diff_ans += "매우 높은";
        else
            diff_ans += "굉장한";
        diff_ans += " 상태";

        return String.format("영업이익증감률이 %.1f%%이므로, %s",영업이익증가율,diff_ans);
    }

}
