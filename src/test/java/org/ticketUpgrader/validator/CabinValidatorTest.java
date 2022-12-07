package org.ticketUpgrader.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ticketUpgrader.model.TicketDetail;

class CabinValidatorTest {


    @Test
    void shouldGiveErrorForInValidCabin() {
        CabinValidator cabinValidator = new CabinValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-05-21", "9876543210", "" +
                "Economys");
        cabinValidator.validate(ticketDetail);
        Assertions.assertEquals(1, ticketDetail.getError().size());
        Assertions.assertEquals("Booked Cabin is invalid", ticketDetail.getError().get(0));
    }

    @Test
    void shouldNotGiveErrorForInValidCabin() {
        CabinValidator cabinValidator = new CabinValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-05-21", "9876543210", "" +
                "Economy");
        cabinValidator.validate(ticketDetail);
        Assertions.assertEquals(0, ticketDetail.getError().size());
    }
}