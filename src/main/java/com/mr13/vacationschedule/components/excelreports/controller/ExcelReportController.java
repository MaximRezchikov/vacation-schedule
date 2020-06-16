package com.mr13.vacationschedule.components.excelreports.controller;

import com.mr13.vacationschedule.components.excelreports.dto.ExcelVacationForm;
import com.mr13.vacationschedule.components.excelreports.service.ExcelVacationReportService;
import com.mr13.vacationschedule.components.excelreports.utils.ExcelUtils;
import com.mr13.vacationschedule.components.excelreports.utils.ReportNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.mr13.vacationschedule.core.constants.StringConstants.XLSX_EXTENSION;
import static com.mr13.vacationschedule.core.constants.UrlConstant.BASE_REPORT_URL;
import static com.mr13.vacationschedule.core.constants.UrlConstant.EXCEL_VACATION_URL;
import static com.mr13.vacationschedule.core.constants.UrlConstant.VACATION_URL;

@RestController
@RequestMapping(BASE_REPORT_URL + VACATION_URL)
@RequiredArgsConstructor
public class ExcelReportController {

  private final ExcelUtils excelUtils;
  private final ReportNameService reportNameService;
  private final ExcelVacationReportService vacancyReportService;

  @PostMapping(EXCEL_VACATION_URL)
  @ResponseBody
  public HttpEntity<byte[]> getExcelVacationReport(@RequestBody ExcelVacationForm excelVacationForm) {

    return excelUtils.downloadFile(vacancyReportService.getVacancyReport(excelVacationForm),
        reportNameService.getFileName("vacation_report", XLSX_EXTENSION));
  }

}
