package com.mr13.vacationschedule.components.employee.service;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.dto.EmployeeForm;
import com.mr13.vacationschedule.core.service.CrudService;

public interface EmployeeService extends CrudService<Employee> {

  Employee save(EmployeeForm employeeForm);

  Employee update(Long employeeId, EmployeeForm employeeForm);

  Employee findByLogin(String login);
}
