package org.dga.backend.portfolio.exceptions.aspectj;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
public class ExceptionTraceExample {

	private static final Logger log = LoggerFactory.getLogger(ExceptionTraceExample.class);

	@AfterThrowing(
			pointcut="execution(* org.dga.backend.portfolio.exceptions.web..*(..))",
			throwing = "e"
	)
	public void logExceptions(JoinPoint jp, Exception e) {
		//pasamos los argumentos del método interceptado a un outputStream, para a continuación, obtener el array de bytes
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Map<String, String> headersRequest = new HashMap<>();
		try {
			//recolectamos los datos que han sido utilizados en la invocación del servicio
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(Arrays.stream(jp.getArgs()).map(Object::toString).collect(Collectors.toList()));
			oos.flush();
			oos.close();
		} catch (IOException ex) {
			log.error("Error during byte conversion of service arguments generated exception");
		}
		try{
			//recuperamos la cabeceras de la petición. Podemos filtrar las que nos interesen
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				if (headerName.toLowerCase().startsWith("x-testing-prueba")) {
					headersRequest.put(headerName, request.getHeader(headerName));
				}
			}
		}catch(Exception ex){
			log.error("Error during reading headers requests with exception");
		}
		log.error("Error captured.\n" +
				"Exception Captured: {}\n"+
				"Exception Message: {}\n"+
				"ServiceName: {}\n" +
				"MethodName: {}\n" +
				"LineNumberError: {}\n" +
				"Headers: {}\n" +
				"Params Request: {}",
				e.getClass().getCanonicalName(),
				e.getMessage(),
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				Arrays.stream(e.getStackTrace()).findFirst().get().getLineNumber(),
				headersRequest.toString(),
				//pasamos a base64
				//Base64.getEncoder().encodeToString(headersRequest.toString().getBytes()),
				jp.getArgs());
				//pasamos a base64
				//Base64.getEncoder().encodeToString(bos.toByteArray()));
	}

}
