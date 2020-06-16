package com.mr13.vacationschedule.components.excelreports.service;

import com.mr13.vacationschedule.components.excelreports.dto.ExcelVacationForm;
import com.mr13.vacationschedule.components.excelreports.workbook.ExcelVacationReportCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelVacationReportServiceImpl implements ExcelVacationReportService {

  private final ExcelVacationReportCreator excelVacationReportCreator;

  @Override
  public byte[] getVacancyReport(ExcelVacationForm excelVacationForm) {
      return excelVacationReportCreator.createExcel(excelVacationForm);
  }
}
