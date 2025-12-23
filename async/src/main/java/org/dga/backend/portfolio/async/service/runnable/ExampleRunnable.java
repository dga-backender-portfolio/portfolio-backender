package org.dga.backend.portfolio.async.service.runnable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class ExampleRunnable implements Runnable{

    private Integer timeToSleep;

    /*
        Al implementar la interfaz Runnable, sobreescribiremos el método run() que es donde generaremos nuestra
        implementación para el método "asíncrono".
     */
    @Override
    public void run() {
        log.info("START RUNNABLE. Sleeping for {} ms", timeToSleep);
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Pass {} ms. END RUNNABLE.", timeToSleep);
    }
}
