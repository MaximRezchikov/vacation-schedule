package com.mr13.vacationschedule.components.vacation.converter;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.EmployeeVacationView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VacationToVacationEmployeeConverter implements Converter<Vacation, EmployeeVacationView> {

  private final ModelMapper modelMapper;

  @Override
  public EmployeeVacationView convert(Vacation vacation) {
    return modelMapper.map(vacation, EmployeeVacationView.class);
  }
}
