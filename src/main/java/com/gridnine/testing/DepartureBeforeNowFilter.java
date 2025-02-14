package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для удаления перелетов с вылетом до текущего момента времени.
 */
public class DepartureBeforeNowFilter implements FlightFilter{
    /**
     * Фильтрует список перелетов, удаляя те, которые имеют вылет до текущего момента времени.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
