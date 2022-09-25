package stock.stockvisualization.domain.member;

import lombok.Data;
import stock.stockvisualization.domain.company.Company;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class Member {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    private List<Company> company;

}
