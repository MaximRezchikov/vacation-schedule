package com.mr13.vacationschedule.components.employee.service;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.dto.EmployeeForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService {

  Employee save(EmployeeForm employeeForm);

  Employee getOne(Long employeeId);

  Employee update(Long employeeId, EmployeeForm employeeForm);

  List<Employee> getAll();

  void delete(Long employeeId);

  Employee findByLogin(String userName);
}
