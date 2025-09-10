/*
 * Copyright 2025 Conductor Authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.rest.config;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Configuration
@ConditionalOnProperty(name = "consul.registration.enabled", havingValue = "true")
public class ConsulServiceRegistry {

    private final ConsulClient consulClient;

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${server.hostname:conductor}")
    private String serverHostname;

    @Value("${consul.host:localhost}")
    private String consulHost;

    @Value("${consul.port:8500}")
    private int consulPort;

    private String serviceId;

    public ConsulServiceRegistry(
            @Value("${consul.host:localhost}") String host,
            @Value("${consul.port:8500}") int port) {
        this.consulClient = new ConsulClient(host, port);
    }

    @PostConstruct
    public void register() {
        serviceId = "conductor-" + UUID.randomUUID();

        NewService newService = new NewService();
        newService.setId(serviceId);
        newService.setName("conductor");
        newService.setTags(Arrays.asList("workflow", "oss"));
        newService.setPort(serverPort);
        newService.setAddress(serverHostname);

        NewService.Check check = new NewService.Check();
        check.setHttp(String.format("http://%s:%s/health", serverHostname, serverPort));
        check.setInterval("10s");
        check.setDeregisterCriticalServiceAfter("1m");

        newService.setCheck(check);

        consulClient.agentServiceRegister(newService);
    }

    @PreDestroy
    public void deregister() {
        if (serviceId != null) {
            consulClient.agentServiceDeregister(serviceId);
        }
    }
}
