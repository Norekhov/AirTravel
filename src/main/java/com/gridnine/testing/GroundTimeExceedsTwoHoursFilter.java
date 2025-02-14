package com.gridnine.testing;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для удаления авиаперелетов с общим временем на земле, превышающим два часа.
 */
public class GroundTimeExceedsTwoHoursFilter implements FlightFilter{
    /**
     * Фильтрует список авиаперелетов, удаляя те, у которых общее время на земле превышает два часа.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    Duration totalGroundTime = Duration.ZERO;

                    for (int i = 0; i < segments.size() - 1; i++) {
                        totalGroundTime = totalGroundTime.plus(Duration.between(
                                segments.get(i).getArrivalDate(),
                                segments.get(i + 1).getDepartureDate()));
                    }

                    return totalGroundTime.toHours() <= 2;
                })
                .collect(Collectors.toList());
    }
}
