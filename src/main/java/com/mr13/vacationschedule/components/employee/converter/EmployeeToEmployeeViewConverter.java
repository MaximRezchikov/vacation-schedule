package com.mr13.vacationschedule.components.employee.converter;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.employee.dto.EmployeeView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeToEmployeeViewConverter implements Converter<Employee, EmployeeView> {

  private final ModelMapper modelMapper;

  @Override
  public EmployeeView convert(Employee employee) {
    return modelMapper.map(employee, EmployeeView.class);
  }
}
