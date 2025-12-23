package org.dga.backend.portfolio.exceptions.service;

import org.dga.backend.portfolio.exceptions.service.model.ModelMapper1;

import java.util.List;

public interface IExampleService {
    List<String> getInformation();
    ModelMapper1 getDataMapper();
    Integer generateExceptionExample();
    void generateExceptionTimeoutExample();
    void generateRandomException();

}
