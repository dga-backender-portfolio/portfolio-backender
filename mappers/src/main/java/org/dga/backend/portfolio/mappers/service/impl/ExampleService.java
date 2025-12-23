package org.dga.backend.portfolio.mappers.service.impl;

import org.dga.backend.portfolio.mappers.service.IExampleService;
import org.dga.backend.portfolio.mappers.service.model.ModelMapper1;
import org.dga.backend.portfolio.mappers.service.model.ModelMapper2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ExampleService implements IExampleService {

    public ModelMapper1 getDataMapper(){
        return new ModelMapper1("Pepito", "Fulanito");
    }

    public List<ModelMapper2> getDataListMapper(){
        return Stream.of(
                new ModelMapper2("abcd", "XXDD", LocalDate.MIN, 2.56),
                new ModelMapper2("efgh", "WWCC", LocalDate.MAX, 776.02),
                new ModelMapper2("ijkl", "YYKK", LocalDate.now(), -109.14)
        ).collect(Collectors.toList());
    }

}
