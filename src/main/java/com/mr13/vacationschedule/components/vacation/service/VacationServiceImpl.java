package com.mr13.vacationschedule.components.vacation.service;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.VacationForm;
import com.mr13.vacationschedule.components.vacation.repo.VacationRepository;
import com.mr13.vacationschedule.core.errors.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {

  private final VacationRepository vacationRepository;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Vacation save(VacationForm vacationForm) {

    Long employeeId = vacationForm.getEmployeeId();
    LocalDate startVacation = vacationForm.getStartVacation();
    LocalDate endVacation = vacationForm.getEndVacation();

    Vacation vacation = Vacation.builder()
        .employeeId(employeeId)
        .startVacation(startVacation)
        .endVacation(endVacation)
        .build();

    return vacationRepository.save(vacation);
  }

  @Override
  @Transactional
  public Vacation getOne(Long vacationId) {
    return vacationRepository.findById(vacationId)
        .orElseThrow(NotFoundException::new);
  }

  @Override
  @Transactional
  public Vacation update(Long vacationId, VacationForm vacationForm) {

    LocalDate startVacation = vacationForm.getStartVacation();
    LocalDate endVacation = vacationForm.getEndVacation();

    boolean exists = vacationRepository.existsById(vacationId);

    if (exists) {
      Vacation vacationToUpdate = getOne(vacationId);

      vacationToUpdate.setStartVacation(startVacation);
      vacationToUpdate.setEndVacation(endVacation);

      return vacationRepository.save(vacationToUpdate);
    }
    else {
      throw new NotFoundException();
    }
  }

  @Override
  @Transactional
  public List<Vacation> getAll() {
    return vacationRepository.findAll();
  }

  @Override
  @Transactional
  public List<Vacation> getVacationsByEmployeeId(Long employeeId) {
    return vacationRepository.getVacationsByEmployeeId(employeeId);
  }

  @Override
  @Transactional
  public void delete(Long vacationId) {
    vacationRepository.deleteById(vacationId);
  }
}
