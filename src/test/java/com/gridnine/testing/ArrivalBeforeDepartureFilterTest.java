package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrivalBeforeDepartureFilterTest {

    @Test
    void arrivalBeforeDepartureTest() {
        Flight flight1 = FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Flight flight2 = FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now().minusHours(1)); // Неверный сегмент

        List<Flight> filteredFlights = new ArrivalBeforeDepartureFilter().filter(List.of(flight1, flight2));

        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
    }

    @Test
    void allSegmentsValidTest() {
        Flight flight = FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));

        List<Flight> filteredFlights = new ArrivalBeforeDepartureFilter().filter(List.of(flight));

        assertEquals(1, filteredFlights.size());
    }

    @Test
    void allSegmentsInvalidTest() {
        Flight flight = FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(1)); // Второй сегмент неверный

        List<Flight> filteredFlights = new ArrivalBeforeDepartureFilter().filter(List.of(flight));

        assertEquals(0, filteredFlights.size());
    }
}
