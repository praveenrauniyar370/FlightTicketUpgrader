package org.ticketUpgrader.validator;

import org.ticketUpgrader.model.TicketDetail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator{
    private Validator nextValidator;

    public PhoneNumberValidator() {}

    public void setNextValidator(Validator nextValidator) {

        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(TicketDetail ticketDetail) {
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(ticketDetail.getMobilePhone().trim());
        if(m.matches()){
                this.nextValidator.validate(ticketDetail);
        } else {
            ticketDetail.getError().add("Mobile Phone is invalid");
        }
    }
}
