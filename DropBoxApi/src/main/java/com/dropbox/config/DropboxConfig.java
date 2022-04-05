package com.dropbox.config;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:data.properties")
class DropboxConfig {

    @Value("sl.BFGtmdp_vteOOfbOCZvWSuEqd0JGHpXVSoE8sURdh38chP7M3hPXUaXrFoBDPJFT5-P4d6l5T9TOm-vPrwiDJaKFgk9UuRRASJLHCR9PDih4vRnLNv4mtpLeUReSv5PVg4WthPrMnw9Y")
    private String ACCESS_TOKEN;

    @Bean
    public DbxClientV2 dropboxClient() throws DbxException {
        DbxRequestConfig config = new DbxRequestConfig("dropbox/DbxSpringBoot");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        return client;
    }
}
