package com.mr13.vacationschedule.components.employee.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class EmployeeForm {

  @NotNull
  private String username;

  @NotNull
  private LocalDate birthday;

  @NotNull
  private Long personnelNumber;

  private String post;

  @NotNull
  private LocalDate startDate;
}
