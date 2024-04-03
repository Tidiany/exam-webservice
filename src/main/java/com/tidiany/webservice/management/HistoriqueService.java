package com.tidiany.webservice.management;

import com.tidiany.webservice.domain.Historique;
import com.tidiany.webservice.domain.Response;
import com.tidiany.webservice.domain.SearchItem;
import com.tidiany.webservice.management.dto.HistoriqueRecord;
import com.tidiany.webservice.repository.HistoriqueRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueService {

    private final HistoriqueRepository historiqueRepository;

    public HistoriqueService(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    public String saveHistory(Map<String, String> response) {
        Historique historique = new Historique();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        historique.setSearchDate(LocalDate.now().format(formatter));
        SearchItem searchItem = new SearchItem();
        String date = response.get("date");
        String dayOfWeek = response.get("dayOfWeek");
        searchItem.setRequest(date);
        Response response1 = new Response();
        response1.setDate(date);
        response1.setDay(dayOfWeek);
        searchItem.setResponse(response1);
        historique.setSearchItems(List.of());
        historiqueRepository.save(historique);
        return "OK";
    }

    public List<HistoriqueRecord> getAllHistory() {
        return null;
    }
}
