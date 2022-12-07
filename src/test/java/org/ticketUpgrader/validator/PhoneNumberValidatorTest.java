package org.ticketUpgrader.validator;

import org.junit.jupiter.api.Test;
import org.ticketUpgrader.model.TicketDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneNumberValidatorTest {
    @Test
    void shouldNotGiveAnyErrorWhenPhoneNumberIsValid() {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        CabinValidator cabinValidator = new CabinValidator();
        phoneNumberValidator.setNextValidator(cabinValidator);

        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzz.com", "2019-06-21", "9876543210", "Economy");
        phoneNumberValidator.validate(ticketDetail);
        assertEquals(0, ticketDetail.getError().size());
    }

    @Test
    void shouldGiveErrorWhenMobilePhoneDoesNotContains10Digit() {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishekzzz.com", "2019-08-21", "987654310", "Economys");
        phoneNumberValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Mobile Phone is invalid", ticketDetail.getError().get(0));
    }

    @Test
    void shouldGiveErrorWhenPhoneNumberContainsAlphabets() {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        TicketDetail ticketDetail = new TicketDetail("Abhishek", "Kumar", "ABC123", "F",
                "2019-07-31", "2", "abhishek@zzzcom", "201908-21", "AVBDCFGTHY", "" +
                "Economys");
        phoneNumberValidator.validate(ticketDetail);
        assertEquals(1, ticketDetail.getError().size());
        assertEquals("Mobile Phone is invalid", ticketDetail.getError().get(0));
    }
}