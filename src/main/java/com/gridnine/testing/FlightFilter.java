package com.gridnine.testing;

import java.util.List;

/**
 * Интерфейс для фильтрации списка авиаперелетов.
 * </p>
 */
public interface FlightFilter {
    /**
     * Фильтрует список авиаперелетов в соответствии с параметрами.
     */
    List<Flight> filter(List<Flight> flights);
}
