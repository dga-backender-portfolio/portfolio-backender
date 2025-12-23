package org.dga.backend.portfolio.async.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dga.backend.portfolio.async.service.IExampleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ExampleService implements IExampleService {

    public List<String> getInformation(){
        return Stream.of("Dani", "Pani", "Cani", "Tali")
                .collect(Collectors.toList());
    }

}
