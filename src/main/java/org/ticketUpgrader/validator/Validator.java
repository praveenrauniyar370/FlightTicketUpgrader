package org.ticketUpgrader.validator;

import org.ticketUpgrader.model.TicketDetail;

public interface Validator {
    void validate(TicketDetail ticketDetail);
}
