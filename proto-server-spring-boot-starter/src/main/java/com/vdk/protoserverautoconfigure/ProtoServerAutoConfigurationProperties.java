package com.vdk.protoserverautoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "protobuf.server")
public class ProtoServerAutoConfigurationProperties {

    private int port = 8080;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
