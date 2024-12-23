package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FilterFlightsService flightFilter = new FilterFlightsService();

        System.out.println("Список всех полетов:");
        printResultFilterList(flights);

        System.out.println("\n Предстоящие полеты,исключая полеты до текущего момента времени: ");
        printResultFilterList(flightFilter.departureUpToNow(flights));

        System.out.println("\n Список полетов без сегментов с датой прилёта раньше даты вылета.");
        printResultFilterList(flightFilter.arrivalBeforeDeparture(flights));

        System.out.println("\n Список полетов, где общее время, проведённое на земле не превышает два часа");
        printResultFilterList(flightFilter.flightTimeMoreThanTwoHours(flights));
    }

    public static void printResultFilterList(List<Flight> list) {
        list.forEach(System.out::println);
    }
}