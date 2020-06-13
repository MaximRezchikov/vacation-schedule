package com.mr13.vacationschedule.components.employee.service;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.dto.EmployeeForm;
import com.mr13.vacationschedule.components.employee.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public Employee save(EmployeeForm employeeForm) {
    return null;
  }

  @Override
  public Employee getOne(Long employeeId) {
    return null;
  }

  @Override
  public Employee update(Long employeeId, EmployeeForm employeeForm) {
    return null;
  }

  @Override
  public List<Employee> getAll() {
    return null;
  }

  @Override
  public void delete(Long employeeId) {

  }
}
