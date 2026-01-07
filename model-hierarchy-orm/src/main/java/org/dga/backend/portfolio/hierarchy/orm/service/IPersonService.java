package org.dga.backend.portfolio.hierarchy.orm.service;

import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelPersonGet;

import java.util.List;

public interface IPersonService {

    List<ModelPersonGet> getPersons();
}
