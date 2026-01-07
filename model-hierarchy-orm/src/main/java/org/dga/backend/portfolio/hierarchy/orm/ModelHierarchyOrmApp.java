package org.dga.backend.portfolio.hierarchy.orm;

import lombok.extern.slf4j.Slf4j;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Contracts;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Person;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Profile;
import org.dga.backend.portfolio.hierarchy.orm.mapper.MapperContracts;
import org.dga.backend.portfolio.hierarchy.orm.repository.ContractsRepository;
import org.dga.backend.portfolio.hierarchy.orm.repository.PersonTptRepository;
import org.dga.backend.portfolio.hierarchy.orm.repository.ProfileTptRepository;
import org.dga.backend.portfolio.hierarchy.orm.service.impl.PersonService;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelPersonGet;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelProfileGet;
import org.dga.backend.portfolio.hierarchy.orm.service.impl.ContractService;
import org.dga.backend.portfolio.hierarchy.orm.service.impl.ProfileService;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelContractsGet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
@Slf4j
public class ModelHierarchyOrmApp {
    public static void main(String[] args) {
        ApplicationContext s = SpringApplication.run(ModelHierarchyOrmApp.class, args);

        log.info("******** Example TPH **************");
        /**
         * Probamos las diferentes ejecuciones y pasos en el modelo TPH
         */
        ContractsRepository d = s.getBean(ContractsRepository.class);
        List<Contracts> l = d.findAll();
        l.stream().forEach(c->{
            log.info("Contract info Entity: {}", c.toString());
        });

        ContractService servContracts = s.getBean(ContractService.class);
        List<ModelContractsGet> contractsResult = servContracts.getContracts();
        contractsResult.stream().forEach(c->{
            log.info("Contract info Model: {}", c.toString());
        });

        //si ahora tuvieramos la transformación del modelo al DTO.
        log.info("Contract info DTO: {}",MapperContracts.INSTANCE.map(contractsResult).toString());





        log.info("\n\n");
        log.info("******** Example TPT (UNIONs) **************");
        ProfileTptRepository tptProfile = s.getBean(ProfileTptRepository.class);
        List<Profile> profile = tptProfile.findAll();
        profile.stream().forEach(p->{
            log.info("Profile info Entity: {}", p.toString());
        });

        ProfileService servProfile = s.getBean(ProfileService.class);
        List<ModelProfileGet> profileResult = servProfile.getProfiles();
        profileResult.stream().forEach(c->{
            log.info("Profile info Model: {}", c.toString());
        });

        log.info("\n\n");
        log.info("******** Example TPT (JOINS) **************");
        PersonTptRepository tptPerson = s.getBean(PersonTptRepository.class);
        List<Person> person = tptPerson.findAll();
        person.stream().forEach(p->{
            log.info("Person info Entity: {}", p.toString());
        });

        PersonService servPerson = s.getBean(PersonService.class);
        List<ModelPersonGet> personResult = servPerson.getPersons();
        personResult.stream().forEach(p->{
            log.info("Person info Model: {}", p.toString());
        });
    }
}