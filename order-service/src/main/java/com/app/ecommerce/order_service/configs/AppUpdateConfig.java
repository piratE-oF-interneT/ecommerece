package com.app.ecommerce.order_service.configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class AppUpdateConfig {

    @Value("${app.downTime}")
    private boolean downTime;

    public boolean isDownTime() {
        return downTime;
    }

    public void setDownTime(boolean downTime) {
        this.downTime = downTime;
    }
}
