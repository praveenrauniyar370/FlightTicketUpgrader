package org.ticketUpgrader.validator;

import org.ticketUpgrader.model.TicketDetail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FairClassValidator implements Validator{
    private Validator nextValidator;

    public FairClassValidator( ) {
    }

    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(TicketDetail ticketDetail) {
        Pattern pattern = Pattern.compile("^[A-Z]$");
        Matcher matcher = pattern.matcher(ticketDetail.getFairClass().trim());
        if(matcher.matches()){
                this.nextValidator.validate(ticketDetail);
        } else {
            ticketDetail.getError().add("Fair Class is invalid");
        }
    }

    public Validator getNextValidator() {
        return nextValidator;
    }
}
