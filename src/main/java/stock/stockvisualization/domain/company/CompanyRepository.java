package stock.stockvisualization.domain.company;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class CompanyRepository {
    private static Map<Long, Company> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    public Company save(Company company){
        company.setId(++sequence);
        store.put(company.getId(), company);
        return company;
    }



}
