package org.ticketUpgrader.validator;

import org.junit.jupiter.api.Test;
import org.ticketUpgrader.model.TicketDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateValidatorTest {

    @Test
    void shouldNotGiveAnyErrorWhenDateFormatIsCorrectAndTicketDateBeforeTravelDate() {
        DateValidator dateValidator = new DateValidator();
        CabinValidator cabinValidator = new CabinValidator();
        dateValidator.setNextValidator(cabinValidator);

        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-06-21", "9876543210", "Economy");
        dateValidator.validate(ticketDetail);
        assertEquals(0, ticketDetail.getError().size());
    }

    @Test
    void shouldGiveErrorWhenTicketDateIsAfterTravelDate() {
        DateValidator dateValidator = new DateValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-08-21", "9876543210", "Economys");
        dateValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Ticket Date should be before Travel Date", ticketDetail.getError().get(0));
    }

    @Test
    void shouldGiveErrorWhenDateFormatIsNotCorrect() {
        DateValidator dateValidator = new DateValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "201908-21", "9876543210", "" +
                "Economys");
        dateValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Date format is invalid It should be only yyyy-mm-dd", ticketDetail.getError().get(0));
    }


}