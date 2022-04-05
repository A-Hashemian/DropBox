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

    @Value("sl.BFGp9rauCV8mO0IqiZ93g7hFhZsmRq84qPTAhnJvEx-AT5xsXJFRmQDm82oTmZc-Oqa4_JSAuhhf2xG-aOB0n0UMgrHYJQGbfXmmEH1Ox88M8joqHsJhujuymoBxpPkrFHmSTgQsV41p")
    private String ACCESS_TOKEN;

    @Bean
    public DbxClientV2 dropboxClient() throws DbxException {
        DbxRequestConfig config = new DbxRequestConfig("dropbox/DbxSpringBoot");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        return client;
    }
}
