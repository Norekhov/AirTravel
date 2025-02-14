package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий цепочку фильтров для обработки списка авиаперелетов.
 */
public class FlightFilterChain {
    private final List<FlightFilter> filters = new ArrayList<>();

    /**
     * Добавляет фильтр в цепочку фильтров.
     */
    void addFilter(FlightFilter filter) {
        filters.add(filter);
    }

    /**
     * Применяет все добавленные фильтры к списку авиаперелетов.
     */
    List<Flight> applyFilters(List<Flight> flights) {
        List<Flight> filteredFlights = flights;
        for (FlightFilter filter : filters) {
            filteredFlights = filter.filter(filteredFlights);
        }
        return filteredFlights;
    }
}
