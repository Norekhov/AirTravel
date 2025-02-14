package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Все перелеты:");
        printFlights(flights);

        FlightFilterChain filterChain = new FlightFilterChain();
        filterChain.addFilter(new DepartureBeforeNowFilter());
        System.out.println("\nПерелеты с вылетом в будущем:");
        printFlights(filterChain.applyFilters(flights));

        filterChain = new FlightFilterChain();
        filterChain.addFilter(new ArrivalBeforeDepartureFilter());
        System.out.println("\nПерелеты с корректными сегментами:");
        printFlights(filterChain.applyFilters(flights));

        filterChain = new FlightFilterChain();
        filterChain.addFilter(new GroundTimeExceedsTwoHoursFilter());
        System.out.println("\nПерелеты с временем на земле не более двух часов:");
        printFlights(filterChain.applyFilters(flights));
    }

    private static void printFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            System.out.println("Нет доступных перелетов.");
            return;
        }
        flights.forEach(System.out::println);
    }
}