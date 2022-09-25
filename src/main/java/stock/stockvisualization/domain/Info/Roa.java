package stock.stockvisualization.domain.Info;

import stock.stockvisualization.domain.company.Company;

public class Roa {

    public String roa(Company company){
        // 공식 : 순이익/자산*100
        // 증가율 : (당기 / 전기 - 1) * 100
        float 총자산순이익률 = Float.parseFloat(String.valueOf(company.account_nm.equals("당기순이익")))/ Float.parseFloat(String.valueOf(company.account_nm.equals("자본총계"))) * 100;
        String ans = "";
        if(총자산순이익률 >= 0){
            if(총자산순이익률 < 30)
                ans += "약간 좋은";
            else if(총자산순이익률 < 70)
                ans += "좋은";
            else
                ans += "매우 좋은";
        }else{
            if(총자산순이익률 > -30)
                ans += "약간 나쁜";
            else if(총자산순이익률 > -70)
                ans += "나쁜";
            else
                ans += "매우 나쁜";
        }
        ans += " 상태";

        float 당기순이익증가율 =( Float.parseFloat(String.valueOf(company.account_nm.equals("당기순이익"))) /  (float)company.frmtrm_amount- 1) * 100;
        String diff_ans = "";
        if(당기순이익증가율 < -100)
            diff_ans += "매우 낮은";
        else if(-100 < 당기순이익증가율 && 당기순이익증가율 <= -70)
            diff_ans += "낮은";
        else if(-70 < 당기순이익증가율 && 당기순이익증가율 <= -30)
            diff_ans += "무난한";
        else if(-30 < 당기순이익증가율 && 당기순이익증가율 <= -10)
            diff_ans += "높은";
        else if(-10 < 당기순이익증가율 && 당기순이익증가율 <= 10)
            diff_ans += "매우 높은";
        else if(10 < 당기순이익증가율 && 당기순이익증가율 <= 30)
            diff_ans += "높은";
        else if(30 < 당기순이익증가율 && 당기순이익증가율 <= 70)
            diff_ans += "무난한";
        else if(70 < 당기순이익증가율 && 당기순이익증가율 <= 100)
            diff_ans += "낮은";
        else
            diff_ans += "매우 낮은";
        diff_ans += " 상태";

        return String.format("ROA가 %.1f%%로 %s이고, 증감률이 %.1f%%이므로 지속성은 %s",총자산순이익률,ans,당기순이익증가율,diff_ans);
    }

}
