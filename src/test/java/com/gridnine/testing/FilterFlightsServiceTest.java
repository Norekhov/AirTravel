package com.gridnine.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterFlightsServiceTest {

    private FilterFlightsService flightFilterTest;

    private Segment testOne;
    private Segment testTwo;
    private Segment testThree;
    private Segment testFour;

    private Flight testFlightOne;
    private Flight testFlightTwo;
    private Flight testFlightThree;
    private Flight testFlightFour;
    private Flight testFlightFive;

    @BeforeEach
    void setUp() {
        flightFilterTest = new FilterFlightsService();

        testOne = new Segment(
                LocalDateTime.of(2024, 12, 23, 10, 40),
                LocalDateTime.of(2024, 12, 23, 20, 40));
        testTwo = new Segment(
                LocalDateTime.of(2024, 12, 15, 8, 50),
                LocalDateTime.of(2024, 12, 15, 9, 20));
        testThree = new Segment(
                LocalDateTime.of(2024, 7, 19, 12, 50),
                LocalDateTime.of(2024, 7, 19, 17, 20));
        testFour = new Segment(
                LocalDateTime.of(2024, 10, 15, 12, 50),
                LocalDateTime.of(2024, 10, 14, 8, 20));

        testFlightOne = new Flight(List.of(testOne));
        testFlightTwo = new Flight(new ArrayList<>());
        testFlightThree = new Flight(List.of(testTwo));
        testFlightFour = new Flight(List.of(testFour));
        testFlightFive = new Flight(List.of(testThree, testTwo));
    }

    @AfterEach
    void clearFlight() {
    }

    @Test
    void departureUpToNowTest() {
        List<Flight> actual = List.of(testFlightOne);
        List<Flight> result = flightFilterTest.departureUpToNow(actual);
        assertEquals(0, result.size());
    }

    @Test
    void arrivalBeforeDepartureTest() {
        List<Flight> actual = List.of(testFlightFour, testFlightTwo);
        List<Flight> result = flightFilterTest.arrivalBeforeDeparture(actual);
        assertEquals(0, result.size());
    }

    @Test
    void flightTimeMoreThanTwoHoursTest() {
        List<Flight> actual = List.of(testFlightFive);
        List<Flight> result = flightFilterTest.flightTimeMoreThanTwoHours(actual);
        assertEquals(0, result.size());
    }
}