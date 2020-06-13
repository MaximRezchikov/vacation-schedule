package com.mr13.vacationschedule.components.vacation.repo;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

}
