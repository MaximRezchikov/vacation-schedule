package com.mr13.vacationschedule.components.vacation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mr13.vacationschedule.core.view.MainView;
import lombok.Data;

import java.time.LocalDate;

import static com.mr13.vacationschedule.core.constants.StringConstants.DATE_FORMAT;

@Data
public class VacationView extends MainView {

  private Long employeeId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate startVacation;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate endVacation;
}
