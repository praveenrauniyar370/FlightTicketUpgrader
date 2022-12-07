package org.ticketUpgrader.validator;

import org.junit.jupiter.api.Test;
import org.ticketUpgrader.model.TicketDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FairClassValidatorTest {
    @Test
    void shouldNotGiveAnyErrorWhenFairClassIsValid() {
        FairClassValidator fairClassValidator = new FairClassValidator();
        CabinValidator cabinValidator = new CabinValidator();
        fairClassValidator.setNextValidator(cabinValidator);

        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-06-21", "9876543210", "Economy");
        fairClassValidator.validate(ticketDetail);
        assertEquals(0, ticketDetail.getError().size());
    }

    @Test
    void shouldGiveErrorWhenFairClassIsInvalid() {
        FairClassValidator fairClassValidator = new FairClassValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "9",
                "2019-07-31", "2", "abhishekzzz.com", "2019-08-21", "9876543210", "Economys");
        fairClassValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Fair Class is invalid", ticketDetail.getError().get(0));
    }

    @Test
    void shouldGiveErrorWhenFairClassContains2Letter() {
        FairClassValidator fairClassValidator = new FairClassValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "AF",
                "2019-07-31", "2", "abhishek@zzzcom", "201908-21", "9876543210", "" +
                "Economys");
        fairClassValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Fair Class is invalid", ticketDetail.getError().get(0));
    }
}