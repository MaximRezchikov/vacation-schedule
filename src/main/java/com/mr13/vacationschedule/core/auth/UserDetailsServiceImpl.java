package com.mr13.vacationschedule.core.auth;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final EmployeeService employeeService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Employee employee = employeeService.findByLogin(username);

    if (employee == null) {
      throw new UsernameNotFoundException("Employee not found");
    }

    return employee;
  }
}
