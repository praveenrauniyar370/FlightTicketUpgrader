package org.ticketUpgrader.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.ticketUpgrader.model.TicketDetail;


import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

public class CsvUtil {
    public static List<TicketDetail> readFile(String path) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(path));
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(TicketDetail.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

    }


    public static void writeToCsv(List<String> sampleList, String header, String filePath) throws Exception {
        PrintWriter writer = new PrintWriter(filePath);
        writer.println(header);

        for (String ticketInfo : sampleList) {
            writer.println(ticketInfo);
        }
        writer.close();
        }
    }

