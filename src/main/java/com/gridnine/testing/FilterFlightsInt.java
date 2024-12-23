package com.gridnine.testing;

import java.util.List;

/**
 * Интерфейс фильтра полетов.
 */
public interface FilterFlightsInt {

    /**
     * Метод для определения полетов до текущего момента времени.
     */
    List<Flight> departureUpToNow(List<Flight> flights);

    /**
     * Метод определения полетов с сегментами прилёта раньше даты вылета.
     */
    List<Flight> arrivalBeforeDeparture(List<Flight> flights);

    /**
     * Метод определения списка рейсов, у которых общее время, проведённое на земле, не превышает два часа.
     */
    List<Flight> flightTimeMoreThanTwoHours(List<Flight> flights);
}
