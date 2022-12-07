package org.ticketUpgrader;

import org.ticketUpgrader.service.ValidatorService;



public class Main {
    public static void main(String[] args) throws Exception {
        ValidatorService validatorService = new ValidatorService();
        validatorService.validateAndWriteValidationInFile("src/test/java/data/ticket-info.csv",
                "src/test/java/data/valid-record-output.csv",
                "src/test/java/data/invalid-record-output.csv");
    }
}