package org.ticketUpgrader.validator;

import org.ticketUpgrader.model.TicketDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class DateValidator implements Validator{
    private final List<String> validCabins =  Arrays.asList("Economy", "Premium Economy", "Business", "First");;
    private Validator nextValidator;


    public DateValidator( ) {
    }

    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(TicketDetail ticketDetail) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if(sdf.parse(ticketDetail.getTicketingDate()).before(sdf.parse(ticketDetail.getTravelDate()))) {
                this.nextValidator.validate(ticketDetail);
            } else {
                ticketDetail.getError().add("Ticket Date should be before Travel Date");
            }
        } catch (ParseException e){
            ticketDetail.getError().add("Date format is invalid It should be only yyyy-mm-dd");
        }
    }
}
