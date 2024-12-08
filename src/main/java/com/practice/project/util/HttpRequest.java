package com.practice.project.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class HttpRequest {

    /*
    private static final HttpClient httpClient =
            HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                    .responseTimeout(Duration.ofSeconds(120))
                    .doOnConnected(conn -> conn
                            .addHandlerLast(new ReadTimeoutHandler(120, TimeUnit.SECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(120, TimeUnit.SECONDS)))
                    .wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);

    public static final WebClient secureWebClient = createSecureWebClient();
    public static final WebClient unsecureWebClient = createUnsecureWebClient();
    public static final WebClient proxyWebClient = createProxyWebClient(false);
    public static final WebClient proxySecureWebClient = createProxyWebClient(true);


    private static WebClient createSecureWebClient() {
        try {
            SslContext sslContext = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();

            HttpClient secureHttpClient = httpClient.secure(t -> t.sslContext(sslContext));

            return WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(secureHttpClient))
                    .build();
        } catch (SSLException e) {
            throw new GenericException("Failed to create secure WebClient", e);
        }
    }

    private static WebClient createUnsecureWebClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    private static WebClient createProxyWebClient(boolean isSecure) {
        HttpClient proxyHttpClient = httpClient.proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                .host("10.234.172.26")
                .port(8080));

        if (isSecure) {
            try {
                SslContext sslContext = SslContextBuilder.forClient()
                        .trustManager(InsecureTrustManagerFactory.INSTANCE)
                        .build();
                proxyHttpClient = proxyHttpClient.secure(t -> t.sslContext(sslContext));
            } catch (SSLException e) {
                throw new GenericException("Failed to create secure proxy WebClient", e);
            }
        }
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(proxyHttpClient))
                .build();
    }



    public static String executeSynchronousRequest(WebClient webClient, HttpMethod apiHttpMethod, String url, String requestBody, String contentType) {
        logRequest(apiHttpMethod, requestBody);
        try {
            return executeWebClientRequest(webClient, apiHttpMethod, url, requestBody, contentType)
                    .bodyToMono(String.class)
                    .doOnNext(response -> log.debug("Received response: {}", response))
                    .doOnError(WebClientResponseException.class, ex -> log.error("HTTP Status Code: {}", ex.getStatusCode(), ex))
                    .block();
        } catch (Exception e) {
            logException(e);
            return "";
        }
    }

    public static CompletableFuture<String> executeAsynchronousRequest(WebClient webClient, HttpMethod apiHttpMethod, String url, String requestBody, String contentType) {
        logRequest(apiHttpMethod, requestBody);
        try {
            return executeWebClientRequest(webClient, apiHttpMethod, url, requestBody, contentType)
                    .bodyToMono(String.class)
                    .doOnNext(response -> log.debug("Received response: {}", response))
                    .doOnError(WebClientResponseException.class, ex -> log.error("HTTP Status Code: {}", ex.getStatusCode(), ex))
                    .toFuture();
        } catch (Exception e) {
            logException(e);
            return CompletableFuture.completedFuture("");
        }
    }

    private WebClient.ResponseSpec executeWebClientRequest(WebClient webClient, HttpMethod apiHttpMethod, String url, String requestBody, String contentType) {
        WebClient.RequestBodySpec requestSpec = webClient.method(apiHttpMethod)
                .uri(url)
                .header("Content-Type", contentType);

        if (requestBody != null && !requestBody.isEmpty()) {
            return requestSpec.bodyValue(requestBody).retrieve();
        } else {
            return requestSpec.retrieve();
        }
    }

    private void logRequest(HttpMethod apiHttpMethod, String url) {
        log.info("Executing HTTP {} request to URL: {}", apiHttpMethod, url);
    }

    private void logException(Exception e) {
        log.error("Unexpected error during HTTP request: {}", e.getMessage(), e);
    }
     */
}