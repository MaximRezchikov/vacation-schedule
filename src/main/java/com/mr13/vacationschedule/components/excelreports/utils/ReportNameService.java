package com.mr13.vacationschedule.components.excelreports.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.mr13.vacationschedule.core.constants.StringConstants.DATE_TIME_FORMAT;


@Component
public class ReportNameService {

  public String getFileName(String prefix, String postfix) {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    String currentDateTime = LocalDateTime.now().format(dateTimeFormatter);

    return prefix + "_" + currentDateTime + postfix;
  }
}
