package org.ticketUpgrader.model;

import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.List;

public class TicketDetail {
    @CsvBindByName(column = "First_Name")
    private String firstName;
    @CsvBindByName(column = "Last_name")
    private  String lastName;
    @CsvBindByName(column = "PNR")
    private  String pnr;
    @CsvBindByName(column = "Fare_class")
    private  String fairClass;
    @CsvBindByName(column = "Travel_date")
    private  String travelDate;
    @CsvBindByName(column = "Pax")
    private  String pax;
    @CsvBindByName(column = "Ticketing_date")
    private  String ticketingDate;
    @CsvBindByName(column = "Email")
    private  String emailId;
    @CsvBindByName(column = "Mobile_phone")
    private  String mobilePhone;
    @CsvBindByName(column = "Booked_cabin")
    private  String bookedCabin;
    private List<String> error;

    public TicketDetail() {
        this.error = new ArrayList<>();
    }

    public TicketDetail(String firstName, String lastName, String pnr, String fairClass, String travelDate, String pax, String emailId, String ticketingDate,  String mobilePhone, String bookedCabin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pnr = pnr;
        this.fairClass = fairClass;
        this.travelDate = travelDate;
        this.pax = pax;
        this.ticketingDate = ticketingDate;
        this.emailId = emailId;
        this.mobilePhone = mobilePhone;
        this.bookedCabin = bookedCabin;
        this.error = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPnr() {
        return pnr;
    }

    public String getFairClass() {
        return fairClass;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getPax() {
        return pax;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getTicketingDate() {
        return ticketingDate;
    }


    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getBookedCabin() {
        return bookedCabin;
    }

    public List<String> getError() {
        return error;
    }

    @Override
    public String toString() {
        return new StringBuilder(firstName)
                .append(", ")
                .append(lastName)
                .append(", ")
                .append(pnr)
                .append(", ")
                .append(fairClass)
                .append(", ")
                .append(travelDate)
                .append(", ")
                .append(pax)
                .append(", ")
                .append(ticketingDate)
                .append(", ")
                .append(emailId)
                .append(", ")
                .append(mobilePhone)
                .append(", ")
                .append(bookedCabin).toString();

    }
}
