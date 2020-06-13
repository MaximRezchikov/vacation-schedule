package com.mr13.vacationschedule.components.vacation.service;

import com.mr13.vacationschedule.components.vacation.repo.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {

  private final VacationRepository vacationRepository;
}
