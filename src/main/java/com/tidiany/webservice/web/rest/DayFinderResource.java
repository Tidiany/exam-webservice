package com.tidiany.webservice.web.rest;

import com.tidiany.webservice.management.HistoriqueService;
import com.tidiany.webservice.management.dto.HistoriqueRecord;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class DayFinderResource {

    private final HistoriqueService historiqueService;

    public DayFinderResource(HistoriqueService historiqueService) {
        this.historiqueService = historiqueService;
    }

    @PostMapping("/calendar/dayfinder")
    public ResponseEntity<Map<String, String>> findDayOfWeek(@RequestBody String date) {
        // Logique pour analyser la date et déterminer le jour de la semaine
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        //        LocalDate localDate = LocalDate.parse(date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String day = dayOfWeek.toString();

        System.out.println(day);

        Map<String, String> response = getFrenchDay(date, day);

        historiqueService.saveHistory(response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/historique/dayfinder")
    public ResponseEntity<List<HistoriqueRecord>> findHistorique() {
        List<HistoriqueRecord> allHistory = historiqueService.getAllHistory();

        return ResponseEntity.ok(allHistory);
    }

    private static Map<String, String> getFrenchDay(String date, String day) {
        String frenchDay =
            switch (day.toUpperCase()) {
                case "MONDAY" -> "Lundi";
                case "TUESDAY" -> "Mardi";
                case "WEDNESDAY" -> "Mercredi";
                case "THURSDAY" -> "Jeudi";
                case "FRIDAY" -> "Vendredi";
                case "SATURDAY" -> "Samedi";
                case "SUNDAY" -> "Dimanche";
                default -> "Jour inconnu";
            };
        Map<String, String> response = new HashMap<>();
        response.put("date", date);
        response.put("dayOfWeek", frenchDay);
        // Enregistrement dans la base de données
        return response;
    }
}
