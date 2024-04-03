package com.tidiany.webservice.web.rest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class DayFinderResource {

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
        return ResponseEntity.ok(response);
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
        // Enregistrement dans la base de données
        Map<String, String> response = new HashMap<>();
        response.put("date", date);
        response.put("dayOfWeek", frenchDay);
        return response;
    }
}
