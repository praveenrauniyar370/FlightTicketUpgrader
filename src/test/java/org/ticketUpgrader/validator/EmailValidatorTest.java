package org.ticketUpgrader.validator;

import org.junit.jupiter.api.Test;
import org.ticketUpgrader.model.TicketDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailValidatorTest {
    @Test
    void shouldNotGiveAnyErrorWhenEmailIdIsValid() {
        EmailValidator emailValidator = new EmailValidator();
        CabinValidator cabinValidator = new CabinValidator();
        emailValidator.setNextValidator(cabinValidator);

        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-06-21", "9876543210", "Economy");
        emailValidator.validate(ticketDetail);
        assertEquals(0, ticketDetail.getError().size());
    }

    @Test
    void shouldGiveErrorWhenEmailInvalid() {
        EmailValidator emailValidator = new EmailValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishekzzz.com", "2019-08-21", "9876543210", "Economys");
        emailValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Email Id is invalid", ticketDetail.getError().get(0));
    }

    @Test
    void shouldGiveErrorWhenEmailDoesNotContainAnyDot() {
        EmailValidator emailValidator = new EmailValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzzcom", "201908-21", "9876543210", "" +
                "Economys");
        emailValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Email Id is invalid", ticketDetail.getError().get(0));
    }
}