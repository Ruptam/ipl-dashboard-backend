package com.ipl.ipl_dashboard.service.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ipl.ipl_dashboard.entity.IplData;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

    public static String type = "text/csv";

    public static boolean hasCSVFormat(MultipartFile multipartFile) {

        if (!type.equals(multipartFile.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<IplData> csvToIplData(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader, 
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<IplData> iplDatas = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                IplData iplData = new IplData();
                iplData.setId(Long.parseLong(csvRecord.get("id")));
                iplData.setCity(csvRecord.get("city"));
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(csvRecord.get("date"), dateTimeFormatter);
                iplData.setDate(localDate);
                iplData.setPlayerOfMatch(csvRecord.get("player_of_match"));
                iplData.setVenue(csvRecord.get("venue"));
                iplData.setTeam1(csvRecord.get("team1"));
                iplData.setTeam2(csvRecord.get("team2"));
                iplData.setTossWinner(csvRecord.get("toss_winner"));
                iplData.setTossDecision(csvRecord.get("toss_decision"));
                iplData.setWinner(csvRecord.get("winner"));
                iplData.setWinningResult(csvRecord.get("result"));
                iplData.setResultMargin(csvRecord.get("result_margin"));
                iplData.setEliminator(csvRecord.get("eliminator"));
                iplData.setUmpire1(csvRecord.get("umpire1"));
                iplData.setUmpire2(csvRecord.get("umpire2"));
                iplDatas.add(iplData);
            }
            return iplDatas;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse csv file :: " + e.getMessage());
        }
    }
    
}
