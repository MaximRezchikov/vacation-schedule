package com.mr13.vacationschedule.components.employee.service;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.dto.EmployeeForm;
import com.mr13.vacationschedule.components.employee.repo.EmployeeRepository;
import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.VacationForm;
import com.mr13.vacationschedule.components.vacation.service.VacationService;
import com.mr13.vacationschedule.core.errors.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final VacationService vacationService;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Employee save(EmployeeForm employeeForm) {

    String username = employeeForm.getUsername();
    LocalDate birthday = employeeForm.getBirthday();
    Long personnelNumber = employeeForm.getPersonnelNumber();
    String post = employeeForm.getPost();
    LocalDate startDate = employeeForm.getStartDate();

    Employee employee = Employee.builder()
        .username(username)
        .birthday(birthday)
        .personnelNumber(personnelNumber)
        .post(post)
        .startDate(startDate)
        .build();

    return employeeRepository.save(employee);
  }

  @Override
  @Transactional
  public Employee getOne(Long employeeId) {
    return employeeRepository.findById(employeeId)
        .orElseThrow(NotFoundException::new);
  }

  @Override
  @Transactional
  public Employee update(Long employeeId, EmployeeForm employeeForm) {

    String username = employeeForm.getUsername();
    LocalDate birthday = employeeForm.getBirthday();
    Long personnelNumber = employeeForm.getPersonnelNumber();
    String post = employeeForm.getPost();
    LocalDate startDate = employeeForm.getStartDate();

    boolean exists = employeeRepository.existsById(employeeId);

    if (exists) {
      Employee employeeToUpdate = getOne(employeeId);

      employeeToUpdate.setUsername(username);
      employeeToUpdate.setBirthday(birthday);
      employeeToUpdate.setPersonnelNumber(personnelNumber);
      employeeToUpdate.setPost(post);
      employeeToUpdate.setStartDate(startDate);

      return employeeRepository.save(employeeToUpdate);
    }
    else {
      throw new NotFoundException();
    }
  }

  @Override
  @Transactional
  public List<Employee> getAll() {
    return employeeRepository.findAll();
  }

  @Override
  @Transactional
  public void delete(Long employeeId) {
    employeeRepository.deleteById(employeeId);
  }

  @Override
  @Transactional
  public void addVacationToEmployee(Long employeeId, VacationForm vacationForm) {
    Employee employee = getOne(employeeId);

    Vacation vacation = vacationService.save(employeeId, vacationForm);
    employee.addVacation(vacation);
    employeeRepository.save(employee);
  }
}
