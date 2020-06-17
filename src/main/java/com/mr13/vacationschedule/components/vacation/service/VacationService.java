package com.mr13.vacationschedule.components.vacation.service;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.VacationForm;
import com.mr13.vacationschedule.core.service.CrudService;

import java.util.List;

public interface VacationService extends CrudService<Vacation> {

  Vacation save(VacationForm vacationForm);

  Vacation update(Long vacationId, VacationForm vacationForm);

  List<Vacation> getVacationsByEmployeeId(Long employeeId);
}
