package org.dga.backend.portfolio.circuit_breaker.service;

import org.dga.backend.portfolio.circuit_breaker.model.InformationModel;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface IExampleService {
    Mono<InformationModel> getInformation(Integer id);
}
