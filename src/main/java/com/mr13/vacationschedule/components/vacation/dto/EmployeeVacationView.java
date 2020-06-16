package com.mr13.vacationschedule.components.vacation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mr13.vacationschedule.components.employee.dto.EmployeeView;
import lombok.Data;

import java.time.LocalDate;

import static com.mr13.vacationschedule.core.constants.StringConstants.DATE_FORMAT;

@Data
public class EmployeeVacationView {

  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate startVacation;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate endVacation;

  private EmployeeView employee;
}
