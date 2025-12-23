package org.dga.backend.portfolio.async.web;

import lombok.extern.slf4j.Slf4j;
import org.dga.backend.portfolio.async.api.dto.OutputDto;
import org.dga.backend.portfolio.async.service.IExampleService;
import org.dga.backend.portfolio.async.service.runnable.ExampleRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequestMapping("/api/example/models/async")
public class ExampleModelsAsync {

    @Autowired
    private IExampleService service;
    @Autowired
    @Qualifier("SomeThreads")
    private ExecutorService execBean;

    @GetMapping("/example1")
    public ResponseEntity<OutputDto> getExample1(@RequestParam Integer timeToSleep){
        /*
        vamos a evidenciar la generación de una ejecución asíncrona, sin espera de resultados.
        Esto puede ser útil cuando queremos independizar hilos de ejecución, es decir, su resultado no se requiere entre sí.
        El ejemplo más sencillo sería el dar la respuesta en un endpoint y una ejecución concurrente de una operación que no determina la
        respuesta de esta petición que hemos dado antes de finalizar.
        */

        //realizamos llamada a un servicio para recuperar la respuesta del servicio.
        List<String> resultServ1 = service.getInformation();

        /*
            Instanciamos el ejecutor. En este caso lo estamos instanciando a demanda.
            en este caso, generamos un thread "simple", ya que no vamos a necesitar una concurrencia, sino un hilo simple.
         */
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            //realizamos la invocación a nuestra implementación de la interfaz Runnable
            log.info("First invocation");
            Future future = executor.submit(new ExampleRunnable(timeToSleep));
            log.info("Second invocation");
            Future future2 = executor.submit(new ExampleRunnable(timeToSleep));
            log.info("Third invocation");
            Future future3 = executor.submit(new ExampleRunnable(timeToSleep));
        }catch(Exception e){
            /**
             * Como hemos generado la respuesta asíncrona,
              */
        }finally {
            /*
                MUY IMPORTANTE, SIEMPRE EJECUTAR EL SHUTDOWN
             */
            log.info("Execute executor's shutdown.");
            executor.shutdown();
        }
        /*
            Respondemos en el endpoint mientras se está ejecutando
            Es decir, mientras se ejecuta el Runnable, vamos a ejecutar este código
            Imprimimos un log a la mitad del parámetro pasado para la espera, para que siempre se intercale con el logger del Runnable.
         */
        try {
            Thread.sleep(timeToSleep/2);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Execute before ending async request. Return response fast than service invoked.");
        return new ResponseEntity(new OutputDto(resultServ1, "Here is your information Async Example 1", null), HttpStatus.OK);
    }

    @GetMapping("/example2")
    public ResponseEntity<OutputDto> getExample2(@RequestParam Integer timeToSleep){
        /*
        vamos a evidenciar la generación de una ejecución asíncrona, sin espera de resultados.
        Esto puede ser útil cuando queremos independizar hilos de ejecución, es decir, su resultado no se requiere entre sí.
        El ejemplo más sencillo sería el dar la respuesta en un endpoint y una ejecución concurrente de una operación que no determina la
        respuesta de esta petición que hemos dado antes de finalizar.
        */

        //realizamos llamada a un servicio para recuperar la respuesta del servicio.
        List<String> resultServ1 = service.getInformation();

        /*
            En vez de instanciar el executor, lo tenemos inyectado a través de dependencia.
         */
        try {
            //realizamos la invocación a nuestra implementación de la interfaz Runnable
            log.info("First invocation");
            Future future = execBean.submit(new ExampleRunnable(timeToSleep));
            log.info("Second invocation");
            Future future2 = execBean.submit(new ExampleRunnable(timeToSleep));
            log.info("Third invocation");
            Future future3 = execBean.submit(new ExampleRunnable(timeToSleep));
        }catch(Exception e){
            /**
             * Como hemos generado la respuesta asíncrona, y no esperamos respuesta, no existen errores a manejar
             */
        }finally {
            /*
                No sería necesario el shutdown, ya que lo realizará al desaparecer el @bean. Eso, conllevaría tener siempre los hilos configurados en la configuración
                en la creación del @bean
                Hay que tener en cuenta que al no cerrarlo, todos los hilos que se vayan generando, ya quedarán "creados", siendo reutilizados en futuras peticiones.
                Ventajas: ahorro de tiempo en creación de hilos, y limitación de nº de hilos creados: al poner un límite, si es necesario más, se espera a la liberación dentro del hilo físico
                    a disponer de uno libre --> Si hay limitación de 2 y son necesarios 3, el tercero espera a que 1 o 2 terminen.
                Inconvenientes: consumo de recursos, ya que los hilos estarán siempre activos, así como tener "un tope": es inconveniente ya que hace más lenta la respuesta, siempre en caso
                    de que se necesiten más hilos para ejecutar la petición completa.
             */
        }
        /*
            Respondemos en el endpoint mientras se está ejecutando
            Es decir, mientras se ejecuta el Runnable, vamos a ejecutar este código
            Imprimimos un log a la mitad del parámetro pasado para la espera, para que siempre se intercale con el logger del Runnable.
         */
        try {
            Thread.sleep(timeToSleep/2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Execute before ending async request. Return response fast than service invoked.");
        return new ResponseEntity(new OutputDto(resultServ1, "Here is your information Async Example 2", null), HttpStatus.OK);
    }
    @GetMapping("/example3")
    public ResponseEntity<OutputDto> getExample3(@RequestParam Integer timeToSleep){
         /*
        vamos a evidenciar la generación de una ejecución asíncrona, sin espera de resultados.
        Esto puede ser útil cuando queremos independizar hilos de ejecución, es decir, su resultado no se requiere entre sí.
        El ejemplo más sencillo sería el dar la respuesta en un endpoint y una ejecución concurrente de una operación que no determina la
        respuesta de esta petición que hemos dado antes de finalizar.
        */

        //realizamos llamada a un servicio para recuperar la respuesta del servicio.
        List<String> resultServ1 = service.getInformation();

        /*
            Instanciamos el ejecutor. En este caso lo estamos instanciando a demanda.
            en este caso, generamos un pool de thread. Útil para casos en los que podamos calcular el número de hilos a utilizar.
         */
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            //realizamos la invocación a nuestra implementación de la interfaz Runnable
            log.info("First invocation");
            Future future = executor.submit(new ExampleRunnable(timeToSleep));
            log.info("Second invocation");
            Future future2 = executor.submit(new ExampleRunnable(timeToSleep));
            log.info("Third invocation");
            Future future3 = executor.submit(new ExampleRunnable(timeToSleep));
        }catch(Exception e){
            /**
             * Como hemos generado la respuesta asíncrona,
             */
        }finally {
            /*
                MUY IMPORTANTE, SIEMPRE EJECUTAR EL SHUTDOWN
             */
            log.info("Execute executor's shutdown.");
            executor.shutdown();
        }
        /*
            Respondemos en el endpoint mientras se está ejecutando
            Es decir, mientras se ejecuta el Runnable, vamos a ejecutar este código
            Imprimimos un log a la mitad del parámetro pasado para la espera, para que siempre se intercale con el logger del Runnable.
         */
        try {
            Thread.sleep(timeToSleep/2);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Execute before ending async request. Return response fast than service invoked.");
        return new ResponseEntity(new OutputDto(resultServ1, "Here is your information Async Example 3", null), HttpStatus.OK);
    }
}
