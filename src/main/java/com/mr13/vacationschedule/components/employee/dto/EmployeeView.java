package com.mr13.vacationschedule.components.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mr13.vacationschedule.components.vacation.dto.VacationView;
import com.mr13.vacationschedule.core.view.MainView;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

import static com.mr13.vacationschedule.core.constants.StringConstants.DATE_FORMAT;

@Data
@NoArgsConstructor
public class EmployeeView extends MainView {

  private String username;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate birthday;

  private Long personnelNumber;

  private String post;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate startDate;

  private Set<VacationView> vacations;
}
