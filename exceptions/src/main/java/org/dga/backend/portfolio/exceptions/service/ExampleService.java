package org.dga.backend.portfolio.exceptions.service;

import org.dga.backend.portfolio.exceptions.service.interfaz.IExampleService;
import org.dga.backend.portfolio.exceptions.service.model.ModelMapper1;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.QueryTimeoutException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public ModelMapper1 getDataMapper(){
        return new ModelMapper1("Pepito", "Fulanito");
    }

    public Integer generateExceptionExample() {
        //Generamos un nullPointer
        Integer i = null;
        i = i + 5;
        return i;
    }
    public void generateExceptionTimeoutExample() {
        //Generamos una exception sql
        SQLException excSql = new SQLException("1013", "72000");
        throw new QueryTimeoutException("Error timeout",excSql, "SELECT * FROM DUAL");
    }

    public void generateRandomException(){
        //generamos una excepción aleatoria
        switch ((int) ((Math.random()*10 + 1 )%2)){
            case 0:
                throw new IndexOutOfBoundsException();
            default:
                throw new RuntimeException();

        }
    }

}
