package org.ticketUpgrader.service;

import org.ticketUpgrader.model.TicketDetail;
import org.ticketUpgrader.model.ValidationReport;
import org.ticketUpgrader.util.CsvUtil;
import org.ticketUpgrader.validator.CabinValidator;
import org.ticketUpgrader.validator.DateValidator;
import org.ticketUpgrader.validator.EmailValidator;
import org.ticketUpgrader.validator.FairClassValidator;
import org.ticketUpgrader.validator.PNRValidator;
import org.ticketUpgrader.validator.PhoneNumberValidator;
import org.ticketUpgrader.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorService {
    private final Validator validator;

    public ValidatorService() {
        this.validator = setUpValidator();
    }

    public void validateAndWriteValidationInFile(String csvFileToValidate, String validDataPath, String invalidDataPath) throws Exception {
        ValidationReport validationReport = validateTicketInfo(csvFileToValidate);
        processInValidTicketInfo(validationReport.getInValidTicketDetails(), invalidDataPath);
        processValidTicketInfo(validationReport.getValidTicketDetails(), validDataPath);
    }

    private ValidationReport validateTicketInfo(String csvFileToValidate) {
        try {
            List<TicketDetail> ticketDetails = CsvUtil.readFile(csvFileToValidate);
            ticketDetails.forEach(this.validator::validate);
            return filterValidAndInvalidRecord(ticketDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void processInValidTicketInfo(List<TicketDetail> inValidTicketDetails, String filePathToWrite) throws Exception {
        String invalidRecordHeader = "First_name, Last_name, PNR, Fare_class, Travel_date, Pax, Ticketing_date, Email, Mobile_phone, Booked_cabin, Error";
        List<String> invalidTicketDetailsWithError = inValidTicketDetails
                .stream()
                .map(this::addErrorInInValidTicketDetails).collect(Collectors.toList());
        CsvUtil.writeToCsv(invalidTicketDetailsWithError, invalidRecordHeader, filePathToWrite);

    }

    private void processValidTicketInfo(List<TicketDetail> validTicketDetails, String filePathToWrite) throws Exception {
        String invalidRecordHeader = "First_name, Last_name, PNR, Fare_class, Travel_date, Pax, Ticketing_date, Email, Mobile_phone, Booked_cabin, Discount_code";
        List<String> invalidTicketDetailsWithError = validTicketDetails
                .stream()
                .map(this::addDiscountInValidTicketDetails).collect(Collectors.toList());
        CsvUtil.writeToCsv(invalidTicketDetailsWithError, invalidRecordHeader, filePathToWrite);

    }

    private String addDiscountInValidTicketDetails(TicketDetail ticketDetail){
        return ticketDetail.toString() + ", " + DiscountCodeService.getDiscountCode(ticketDetail.getFairClass().trim());
    }

    private String addErrorInInValidTicketDetails(TicketDetail ticketDetail){
        return ticketDetail.toString() + ", " + ticketDetail.getError().get(0);
    }

    private ValidationReport filterValidAndInvalidRecord(List<TicketDetail> ticketDetails) {
        ValidationReport validationReport = new ValidationReport();
        List<TicketDetail> inValidTicketDetails = validationReport.getInValidTicketDetails();
        List<TicketDetail> validTicketDetails = validationReport.getValidTicketDetails();
        for (TicketDetail ticketDetail : ticketDetails) {
            if(ticketDetail.getError().size() > 0){
                inValidTicketDetails.add(ticketDetail);
            } else {
                validTicketDetails.add(ticketDetail);
            }
        }
        return validationReport;
    }

    private Validator setUpValidator() {
        EmailValidator emailValidator = new EmailValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        DateValidator dateValidator = new DateValidator();
        PNRValidator pnrValidator = new PNRValidator();
        FairClassValidator fairClassValidator = new FairClassValidator();
        CabinValidator cabinValidator = new CabinValidator();
        emailValidator.setNextValidator(phoneNumberValidator);
        phoneNumberValidator.setNextValidator(dateValidator);
        dateValidator.setNextValidator(pnrValidator);
        pnrValidator.setNextValidator(fairClassValidator);
        fairClassValidator.setNextValidator(cabinValidator);
        return emailValidator;
    }
}
