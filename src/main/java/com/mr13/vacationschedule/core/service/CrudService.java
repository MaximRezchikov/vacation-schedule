package com.mr13.vacationschedule.core.service;

import com.mr13.vacationschedule.components.employee.domain.Employee;

import java.util.List;

public interface CrudService<T> {

  T getOne(Long entityId);

  List<T> getAll();

  void delete(Long entityId);
}
