package com.mr13.vacationschedule.components.vacation.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class VacationForm {

  @NotNull
  private LocalDate startVacation;

  @NotNull
  private LocalDate endVacation;
}
