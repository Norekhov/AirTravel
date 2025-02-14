package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartureBeforeNowFilterTest {
    @Test
    void departureBeforeNowTest() {
        Flight flight1 = FlightBuilder.createFlight(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusHours(1));
        Flight flight2 = FlightBuilder.createFlight(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2));

        List<Flight> filteredFlights = new DepartureBeforeNowFilter().filter(List.of(flight1, flight2));

        assertEquals(1, filteredFlights.size());
        assertEquals(flight2, filteredFlights.get(0));
    }
    @Test
    void noFlightsBeforeNowTest() {
        Flight flight = FlightBuilder.createFlight(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2));

        List<Flight> filteredFlights = new DepartureBeforeNowFilter().filter(List.of(flight));

        assertEquals(1, filteredFlights.size());
    }

    @Test
    void allFlightsBeforeNowTest() {
        Flight flight1 = FlightBuilder.createFlight(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusHours(1));
        Flight flight2 = FlightBuilder.createFlight(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1).plusHours(2));

        List<Flight> filteredFlights = new DepartureBeforeNowFilter().filter(List.of(flight1, flight2));

        assertEquals(0, filteredFlights.size());
    }
}
