package com.mr13.vacationschedule.components.excelreports.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExcelVacationForm {

  private List<Long> vacationIds;
  private List<String> columnTitles;
}
