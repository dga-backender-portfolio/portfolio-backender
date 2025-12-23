package org.dga.backend.portfolio.mappers.service;

import org.dga.backend.portfolio.mappers.service.model.ModelMapper1;
import org.dga.backend.portfolio.mappers.service.model.ModelMapper2;

import java.util.List;

public interface IExampleService {
    ModelMapper1 getDataMapper();
    List<ModelMapper2> getDataListMapper();
}
