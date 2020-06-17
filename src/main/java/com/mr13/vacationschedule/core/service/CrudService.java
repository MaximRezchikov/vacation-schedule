package com.mr13.vacationschedule.core.service;

import java.util.List;

public interface CrudService<T> {

  T getOne(Long entityId);

  List<T> getAll();

  void delete(Long entityId);
}
