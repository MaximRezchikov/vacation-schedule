package com.mr13.vacationschedule.components.employee.controller;

import com.mr13.vacationschedule.components.employee.service.EmployeeService;
import com.mr13.vacationschedule.components.vacation.dto.VacationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.mr13.vacationschedule.core.constants.UrlConstant.EMPLOYEE_URL;
import static com.mr13.vacationschedule.core.constants.UrlConstant.VACATION_URL;

@RestController
@RequestMapping(EMPLOYEE_URL)
@RequiredArgsConstructor
public class EmployeeVacationController {

  private final EmployeeService employeeService;

  @PostMapping("/{id}" + VACATION_URL)
  @ResponseStatus(HttpStatus.CREATED)
  public void addVacationToEmployee(@PathVariable Long id, @RequestBody VacationForm vacationForm) {
    employeeService.addVacationToEmployee(id, vacationForm);
  }
}
