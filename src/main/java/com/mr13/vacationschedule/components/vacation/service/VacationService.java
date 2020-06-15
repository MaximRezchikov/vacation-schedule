package com.mr13.vacationschedule.components.vacation.service;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.VacationForm;

import java.util.List;

public interface VacationService {

  Vacation save(Long employeeId, VacationForm vacationForm);

  Vacation getOne(Long vacationId);

  Vacation update(Long vacationId, VacationForm vacationForm);

  List<Vacation> getAll();

  void delete(Long vacationId);
}
