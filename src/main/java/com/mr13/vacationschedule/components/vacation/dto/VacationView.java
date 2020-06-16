package com.mr13.vacationschedule.components.vacation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mr13.vacationschedule.core.view.MainView;
import lombok.Data;

import java.time.LocalDate;

import static com.mr13.vacationschedule.core.constants.StringConstants.TIME_FORMAT;

@Data
public class VacationView extends MainView {

  private Long employeeId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT)
  private LocalDate startVacation;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT)
  private LocalDate endVacation;
}
