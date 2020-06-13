package com.mr13.vacationschedule.components.employee.controller;

import com.mr13.vacationschedule.components.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mr13.vacationschedule.core.constants.UrlConstant.EMPLOYEE_URL;

@RestController
@RequestMapping(EMPLOYEE_URL)
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;
}
