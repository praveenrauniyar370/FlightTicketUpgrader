package org.ticketUpgrader.util;

import org.junit.jupiter.api.Test;
import org.ticketUpgrader.model.TicketDetail;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVUtilTest {
    @Test
    public void testReadingPersonObjectsFromCsvData() throws Exception {

        List<TicketDetail> ticketDetailList = CsvUtil.readFile("src/test/java/data/ticket-info.csv");
        System.out.println(ticketDetailList.toString());
        assertEquals(5, ticketDetailList.size());
        TicketDetail person1 = ticketDetailList.get(0);
        assertEquals("Abhishek", person1.getFirstName());
        assertEquals(" Kumar", person1.getLastName());
        assertEquals(" ABC123", person1.getPnr());
        assertEquals(" F", person1.getFairClass());
        assertEquals(" 2019-07-31", person1.getTravelDate());
        assertEquals(" 2", person1.getPax());
        assertEquals(" 2019-05-21", person1.getTicketingDate());
        assertEquals(" abhishek@zzz.com", person1.getEmailId());
        assertEquals(" 9876543210", person1.getMobilePhone());
        assertEquals(" Economy", person1.getBookedCabin());
    }
}
