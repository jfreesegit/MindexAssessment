package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {
  private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private CompensationRepository compensationRepository;

  @Override
  public Compensation create(Compensation compensation) {
    if(compensation != null) {
      compensationRepository.save(compensation);
      return compensation;
    }
    else return null;
  }

  @Override
  public Compensation read(String id) {
    LOG.debug("Reading compensation by employee id [{}]", id);
    Compensation compensation = null;
    Employee employee = employeeService.read(id);

    if(employee != null) {
      compensation = compensationRepository.findByEmployee(employee);
    }
    else {
      LOG.error("Failed reading the compensation because the employee object was null with id [{}]", id);
    }
    return compensation;
  }
}