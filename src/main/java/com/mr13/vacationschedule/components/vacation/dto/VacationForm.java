package com.mr13.vacationschedule.components.vacation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.mr13.vacationschedule.core.constants.StringConstants.DATE_FORMAT;

@Data
public class VacationForm {

  private Long employeeId;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate startVacation;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate endVacation;
}
