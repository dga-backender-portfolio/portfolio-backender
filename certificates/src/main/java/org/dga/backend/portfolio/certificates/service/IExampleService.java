package org.dga.backend.portfolio.certificates.service;

import reactor.core.publisher.Mono;

public interface IExampleService {
    Mono<String> callBadSslAndReturnPkixError();
    Mono<String> callBadSslWithoutCertificateReturnError();
    Mono<String> callBadSsl();
}
