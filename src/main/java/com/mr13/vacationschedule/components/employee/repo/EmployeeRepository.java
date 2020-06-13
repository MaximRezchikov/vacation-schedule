package com.mr13.vacationschedule.components.employee.repo;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
