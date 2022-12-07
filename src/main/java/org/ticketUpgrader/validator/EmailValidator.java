package org.ticketUpgrader.validator;

import org.ticketUpgrader.model.TicketDetail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator{
    private Validator nextValidator;

    public EmailValidator( ) {
    }

    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(TicketDetail ticketDetail) {
        String emailId = ticketDetail.getEmailId();
        String[] emailIdSplit = emailId.split("@");
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(emailId);
        if(matcher.matches() && emailIdSplit[1].contains(".") ){
                this.nextValidator.validate(ticketDetail);
        } else {
            ticketDetail.getError().add("Email Id is invalid");
        }
    }
}
