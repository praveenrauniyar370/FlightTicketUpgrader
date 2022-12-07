package org.ticketUpgrader.validator;

import org.junit.jupiter.api.Test;
import org.ticketUpgrader.model.TicketDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PnrValidatorTest {
    @Test
    void shouldNotGiveAnyErrorWhenPNRIsValid() {
        PNRValidator pnrValidator = new PNRValidator();
        CabinValidator cabinValidator = new CabinValidator();
        pnrValidator.setNextValidator(cabinValidator);

        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-06-21", "9876543210", "Economy");
        pnrValidator.validate(ticketDetail);
        assertEquals(0, ticketDetail.getError().size());
    }

    @Test
    void shouldGiveErrorWhenPnrIsInvalid() {
        PNRValidator pnrValidator = new PNRValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "AB23", "F",
                "2019-07-31", "2", "abhishekzzz.com", "2019-08-21", "9876543210", "Economys");
        pnrValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("PNR is invalid", ticketDetail.getError().get(0));
    }
}