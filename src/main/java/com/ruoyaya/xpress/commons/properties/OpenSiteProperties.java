package com.ruoyaya.xpress.commons.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "opensite")
public class OpenSiteProperties {
    private boolean cluster;

    public OpenSiteProperties() {
    }

    public boolean isCluster() {
        return cluster;
    }

    public void setCluster(boolean cluster) {
        this.cluster = cluster;
    }
}
