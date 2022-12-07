package org.ticketUpgrader.validator;

import org.ticketUpgrader.model.TicketDetail;

import java.util.Arrays;
import java.util.List;

public class CabinValidator implements Validator{
    private final List<String> validCabins =  Arrays.asList("Economy", "Premium Economy", "Business", "First");;


    public CabinValidator( ) {
    }


    @Override
    public void validate(TicketDetail ticketDetail) {
        if(!validCabins.contains(ticketDetail.getBookedCabin().trim())){
            ticketDetail.getError().add("Booked Cabin is invalid");
        }
    }
}
