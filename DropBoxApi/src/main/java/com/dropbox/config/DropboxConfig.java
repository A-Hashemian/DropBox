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

    @Value("sl.BFHlDVUkdZ0ODA85TqX6hJQow3wVlXQuBc-aYbS-C8A-y7yQy-1NJUKC08YpjUU5chACUO_SJsCDWKWYB9f6M067Bzxk6wWdufqZ0qBH649iDQrv8RgLLpqsmqThBoBte_vQLAcIryku")
    private String ACCESS_TOKEN;

    @Bean
    public DbxClientV2 dropboxClient() throws DbxException {
        DbxRequestConfig config = new DbxRequestConfig("dropbox/DbxSpringBoot");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        return client;
    }
}
