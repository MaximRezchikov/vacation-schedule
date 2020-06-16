package com.mr13.vacationschedule.components.excelreports.service;

import com.mr13.vacationschedule.components.excelreports.dto.ExcelVacationForm;

public interface ExcelVacationReportService {

  byte[] getVacancyReport(ExcelVacationForm excelVacationForm);
}
