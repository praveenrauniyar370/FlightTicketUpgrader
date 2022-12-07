package org.ticketUpgrader.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketDetailTest {

    @Test
    void shouldReturnToStringAsCSVDataForTicketDetail() {
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-05-21", "9876543210", "Economy");
        String expectedToString = "Abhishek, Kumar, ABC123, F, 2019-07-31, 2, 2019-05-21, abhishek@zzz.com, 9876543210, Economy";
        assertEquals(expectedToString, ticketDetail.toString());

    }
}