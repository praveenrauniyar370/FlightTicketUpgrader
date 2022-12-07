package org.ticketUpgrader.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorServiceTest {

    @AfterAll
    static void  deleteCreatedFile(){
        File validRecordFile = new File("src/test/java/data/valid-record-output.csv");
        File inValidRecordFile = new File("src/test/java/data/invalid-record-output.csv");
        assertTrue(validRecordFile.delete());
        assertTrue(inValidRecordFile.delete());

    }
    @Test
    void shouldValidateTicketInfoAndWriteInFileWithErrorAndDiscountCode() throws Exception {
        ValidatorService validatorService = new ValidatorService();
        validatorService.validateAndWriteValidationInFile("src/test/java/data/ticket-info.csv",
                "src/test/java/data/valid-record-output.csv",
                "src/test/java/data/invalid-record-output.csv");

        List<String> validTicketDetails = readFileInStringList("src/test/java/data/valid-record-output.csv");
        List<String> inValidTicketDetails = readFileInStringList("src/test/java/data/invalid-record-output.csv");
        assertEquals(3, validTicketDetails.size());
        assertEquals(2, inValidTicketDetails.size());
        assertEquals("Abhishek  Kumar  ABC123  F  2019-07-31  2  2019-05-21  abhishek@zzz.com  9876543210  Economy OFFER_30", validTicketDetails.get(0));
        assertEquals("Kalyani  Ben  A1B2C3  M 2019-06-30  1  2019-05-21  kben@zzz.com  9876543213  Premium Economy OFFER_25", validTicketDetails.get(1));
        assertEquals("Somnath  Batra  X1Y2Z4  Z  2019-07-25  3  2019-05-23  sbatra@zzz.com 9876543214  Economy ", validTicketDetails.get(2));
        assertEquals("Monin Sankar  PQ234  C  2019-08-30  2  2019-05-22  monin@zzz.com 9876543211  Economy PNR is invalid", inValidTicketDetails.get(0));
        assertEquals("Radhika  Suresh  ZZZ345  T  2019-03-31  4  2019-05-21  radhika@zzz  9876543212  Business Email Id is invalid", inValidTicketDetails.get(1));
    }

    private List<String> readFileInStringList(String path) throws Exception {
        FileReader filereader = new FileReader(path);

        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();
        List<String[]> allData = csvReader.readAll();
        List<String> fileData = new ArrayList<>();
        for (String[] row : allData) {
            String rowData = "";
            for (String cell : row) {
                rowData = rowData + cell;
            }
            fileData.add(rowData);
        }
        return fileData;
    }
}