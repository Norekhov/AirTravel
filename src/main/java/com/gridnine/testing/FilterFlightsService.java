package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Сервис фильтра полетов.
 */
public class FilterFlightsService implements FilterFlightsInt {

    private final LocalDateTime localDateTime;

    public FilterFlightsService() {
        this.localDateTime = LocalDateTime.now();
    }
    /**
     * Метод для вычисления разницы между двумя промежутками времени(прибытия и отбытия).
     */
    private long checkTime(LocalDateTime arrival, LocalDateTime departure) {
        return ChronoUnit.HOURS.between(arrival, departure);
    }
    /**
     * Метод возвращает список полетов до текущего момента времени.
     */
    @Override
    public List<Flight> departureUpToNow(List<Flight> flights) {
        if (flights != null) {
            return flights.stream()
                    .filter(flight -> flight.getSegments().stream()
                            .anyMatch(segment -> localDateTime.isBefore(segment.getDepartureDate())))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    /**
     * Метод возвращает список полетов с сегментами прилёта раньше даты вылета.
     */
    @Override
    public List<Flight> arrivalBeforeDeparture(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();
        if (flights != null) {
            flights.forEach(flight -> flight.getSegments()
                    .stream()
                    .filter(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())).limit(1)
                    .forEach(segment -> result.add(flight)));
            return result;
        }
        return result;
    }
    /**
     * Метод возвращает список рейсов, у которых общее время, проведённое на земле, не превышает два часа.
     */
    @Override
    public List<Flight> flightTimeMoreThanTwoHours(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();
        if (flights != null) {
            result = flights.stream()
                    .filter(flight -> flight.getSegments().size() > 1)
                    .filter(flight -> {
                        long countHours = IntStream.range(1, flight.getSegments().size())
                                .map(i -> Math.toIntExact(checkTime(flight.getSegments().get(i - 1).getArrivalDate(),
                                        flight.getSegments().get(i).getDepartureDate())))
                                .sum();
                        return countHours <= 2;
                    })
                    .collect(Collectors.toList());
            result.addAll(flights.stream()
                    .filter(flight -> flight.getSegments().size() <= 1)
                    .toList());
            return result;
        }
        return result;
    }
}
