package guru.springframework.msscbreweryclient.web.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
     * Created by jt on 2019-08-08.
     */
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    @Value("${connection.maximum.pool.thread}")
    private int maxPoolThread;

    @Value("${connection.default.max.per.route}")
    private int defaultMaxPerRoute;

    @Value("${connection.request.timeout.ms}")
    private long requestTimeout;


    public ClientHttpRequestFactory clientHttpRequestFactory() {
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(maxPoolThread);
            connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

            RequestConfig requestConfig = RequestConfig
                    .custom()
                    .setConnectionRequestTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                    .build();

            CloseableHttpClient httpClient = HttpClients
                    .custom()
                    .setConnectionManager(connectionManager)
                    .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                    .setDefaultRequestConfig(requestConfig)
                    .build();

            return new HttpComponentsClientHttpRequestFactory(httpClient);

        }

        @Override
        public void customize(RestTemplate restTemplate) {
            restTemplate.setRequestFactory(this.clientHttpRequestFactory());
        }
    }

