package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroundTimeExceedsTwoHoursFilterTest {
    @Test
    void groundTimeExceedsTwoHoursTest() {
        // Создаем перелет с временем на земле более двух часов
        Flight flight = FlightBuilder.createFlight(
                LocalDateTime.now(), LocalDateTime.now().plusHours(1), // Первый сегмент
                LocalDateTime.now().plusHours(3).plusMinutes(30), LocalDateTime.now().plusHours(4) // Второй сегмент (время на земле 2.5 часа)
        );

        List<Flight> filteredFlights = new GroundTimeExceedsTwoHoursFilter().filter(List.of(flight));

        assertEquals(1, filteredFlights.size()); // Должен быть отфильтрован
    }

    @Test
    void groundTimeWithinTwoHoursTest() {
        Flight flight = FlightBuilder.createFlight(
                LocalDateTime.now(), LocalDateTime.now().plusHours(3),
                LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5) // Время на земле 1 час
        );

        List<Flight> filteredFlights = new GroundTimeExceedsTwoHoursFilter().filter(List.of(flight));

        assertEquals(1, filteredFlights.size()); // Должен остаться в списке
    }

    @Test
    void multipleSegmentsWithinTwoHoursTest() {
        Flight flight = FlightBuilder.createFlight(
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),
                LocalDateTime.now().plusMinutes(90), LocalDateTime.now().plusMinutes(120) // Время на земле 30 минут + 30 минут
        );

        List<Flight> filteredFlights = new GroundTimeExceedsTwoHoursFilter().filter(List.of(flight));

        assertEquals(1, filteredFlights.size()); // Должен остаться в списке
    }
}
