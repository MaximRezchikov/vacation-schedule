package com.mr13.vacationschedule.components.excelreports.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class ExcelVacationForm {

  private List<Long> vacationIds;
  private List<String> columnTitles;
}
