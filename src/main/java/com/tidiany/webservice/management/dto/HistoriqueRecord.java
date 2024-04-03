package com.tidiany.webservice.management.dto;

import java.util.List;

public record HistoriqueRecord(Long id, String searchDate, List<SearchItemRecord> searchItems) {}
