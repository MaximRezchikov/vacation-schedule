package com.mr13.vacationschedule.components.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class EmployeeForm {

  @NotNull
  private String username;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate birthday;

  @NotNull
  private Long personnelNumber;

  private String post;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate startDate;
}
