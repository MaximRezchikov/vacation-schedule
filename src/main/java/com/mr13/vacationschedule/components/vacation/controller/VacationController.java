package com.mr13.vacationschedule.components.vacation.controller;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.VacationForm;
import com.mr13.vacationschedule.components.vacation.dto.VacationView;
import com.mr13.vacationschedule.components.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.mr13.vacationschedule.core.constants.UrlConstant.EMPLOYEE_URL;
import static com.mr13.vacationschedule.core.constants.UrlConstant.VACATION_URL;

@RestController
@RequestMapping(VACATION_URL)
@RequiredArgsConstructor
public class VacationController {

  private final VacationService vacationService;
  private final ConversionService converter;

  @GetMapping("/{id}")
  public VacationView getOne(@PathVariable("id") Long vacationId) {

    Vacation vacation = vacationService.getOne(vacationId);

    return converter.convert(vacation, VacationView.class);
  }

  @GetMapping
  public List<VacationView> getAll() {

    List<Vacation> vacationList = vacationService.getAll();

    return vacationList.stream()
        .map(vacation -> converter.convert(vacation, VacationView.class))
        .collect(Collectors.toList());
  }

  @GetMapping(EMPLOYEE_URL + "/{employeeId}")
  public List<Vacation> getVacationByEmployeeId(@PathVariable Long employeeId) {
    return vacationService.getVacationsByEmployeeId(employeeId);
  }

  @PostMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public VacationView saveVacation(@PathVariable("id") Long employeeId, @Valid @RequestBody VacationForm vacationForm) {

    Vacation vacation = vacationService.save(employeeId, vacationForm);

    return converter.convert(vacation, VacationView.class);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public VacationView updateVacation(@PathVariable("id") Long vacationId,
      @Valid @RequestBody VacationForm vacationForm) {

    Vacation vacation = vacationService.update(vacationId, vacationForm);

    return converter.convert(vacation, VacationView.class);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long vacationId) {
    vacationService.delete(vacationId);
  }
}
