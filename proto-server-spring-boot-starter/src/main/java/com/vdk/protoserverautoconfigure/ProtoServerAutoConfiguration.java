package com.vdk.protoserverautoconfigure;

import com.server.ProtobufServer;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

@AutoConfiguration
@ConditionalOnClass({ProtobufServer.class})
@EnableConfigurationProperties(ProtoServerAutoConfigurationProperties.class)
public class ProtoServerAutoConfiguration {

    private final List<BindableService> services;

    @Autowired
    private final ProtoServerAutoConfigurationProperties properties;

    @Autowired/*(required = false)*/
    public ProtoServerAutoConfiguration(List<BindableService> services, ProtoServerAutoConfigurationProperties properties) {
        this.services = services;
        this.properties = properties;
    }


    @Bean
    @ConditionalOnMissingBean
    public ProtobufServer protobufServer() {
        int port = properties.getPort();
        if (services == null || services.size() == 0) {
            return new ProtobufServer(port, Collections.singletonList(() -> ServerServiceDefinition.builder("default").build()));
        }
        return new ProtobufServer(port, services);
    }

}
