package org.ticketUpgrader.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationReport {
    List<TicketDetail> validTicketDetails;
    List<TicketDetail> inValidTicketDetails;

    public ValidationReport() {
        this.inValidTicketDetails = new ArrayList<>();
        this.validTicketDetails = new ArrayList<>();
    }

    public List<TicketDetail> getValidTicketDetails() {
        return validTicketDetails;
    }

    public List<TicketDetail> getInValidTicketDetails() {
        return inValidTicketDetails;
    }

}
