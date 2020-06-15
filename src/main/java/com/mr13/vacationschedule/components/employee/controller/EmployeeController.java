package com.mr13.vacationschedule.components.employee.controller;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.dto.EmployeeForm;
import com.mr13.vacationschedule.components.employee.dto.EmployeeView;
import com.mr13.vacationschedule.components.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
import java.util.stream.Collectors;

import static com.mr13.vacationschedule.core.constants.UrlConstant.EMPLOYEE_URL;

@RestController
@RequestMapping(EMPLOYEE_URL)
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;
  private final ConversionService converter;

  @GetMapping("/{id}")
  public EmployeeView getOne(@PathVariable("id") Long employeeId) {

    Employee employee = employeeService.getOne(employeeId);

    return converter.convert(employee, EmployeeView.class);
  }

  @GetMapping
  public List<EmployeeView> getAll() {

    List<Employee> employeeList = employeeService.getAll();

    return employeeList.stream()
        .map(employee -> converter.convert(employee, EmployeeView.class))
        .collect(Collectors.toList());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EmployeeView saveEmployee(@Valid @RequestBody EmployeeForm employeeForm) {

    Employee employee = employeeService.save(employeeForm);

    return converter.convert(employee, EmployeeView.class);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EmployeeView updateEmployee(@PathVariable("id") Long employeeId,
      @Valid @RequestBody EmployeeForm employeeForm) {

    Employee employee = employeeService.update(employeeId, employeeForm);

    return converter.convert(employee, EmployeeView.class);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long employeeId) {
    employeeService.delete(employeeId);
  }
}
