package com.mr13.vacationschedule.components.vacation.converter;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.VacationView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
@RequiredArgsConstructor
public class VacationToVacationViewConverter implements Converter<Vacation, VacationView> {

  private final ModelMapper modelMapper;

  @Override
  public VacationView convert(Vacation vacation) {
    return modelMapper.map(vacation , VacationView.class);
  }
}
