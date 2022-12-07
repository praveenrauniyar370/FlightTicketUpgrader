package org.ticketUpgrader.validator;

import org.ticketUpgrader.model.TicketDetail;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PNRValidator implements Validator{
    private Validator nextValidator;

    public PNRValidator( ) {
    }

    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(TicketDetail ticketDetail) {
        Pattern p = Pattern.compile("^[a-zA-Z\\d]{6}$");
        Matcher m = p.matcher(ticketDetail.getPnr().trim());
        if(m.matches()){
                this.nextValidator.validate(ticketDetail);
        } else {
            ticketDetail.getError().add("PNR is invalid");
        }

    }
}
