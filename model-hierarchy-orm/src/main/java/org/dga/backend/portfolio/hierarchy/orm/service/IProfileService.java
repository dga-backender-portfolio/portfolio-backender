package org.dga.backend.portfolio.hierarchy.orm.service;

import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelProfileGet;

import java.util.List;

public interface IProfileService {

    List<ModelProfileGet> getProfiles();
}
