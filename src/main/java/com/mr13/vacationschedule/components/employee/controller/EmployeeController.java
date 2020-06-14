package com.mr13.vacationschedule.components.employee.controller;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.dto.EmployeeForm;
import com.mr13.vacationschedule.components.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.mr13.vacationschedule.core.constants.UrlConstant.EMPLOYEE_URL;

@RestController
@RequestMapping(EMPLOYEE_URL)
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/{id}")
  public Employee getOne(@PathVariable("id") Long employeeId) {
    return employeeService.getOne(employeeId);
  }

  @GetMapping
  public List<Employee> getAll() {
    return employeeService.getAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Employee saveEmployee(@Valid @RequestBody EmployeeForm employeeForm) {
    return employeeService.save(employeeForm);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Employee updateEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody EmployeeForm employeeForm) {
    return employeeService.update(employeeId, employeeForm);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long employeeId) {
    employeeService.delete(employeeId);
  }
}
