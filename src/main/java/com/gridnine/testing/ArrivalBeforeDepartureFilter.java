package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для удаления перелетов, в которых есть сегменты с датой прилета раньше даты вылета.
 */
public class ArrivalBeforeDepartureFilter implements FlightFilter{
    /**
     * Фильтрует список перелетов, удаляя те, где есть сегменты с датой прилета раньше даты вылета.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
