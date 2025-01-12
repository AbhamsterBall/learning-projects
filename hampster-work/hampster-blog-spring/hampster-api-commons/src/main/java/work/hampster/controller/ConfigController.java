package work.hampster.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
//    @NacosConfig(dataId = "blog-prod.yaml", group = "DEFAULT_GROUP", key = "config.useLocalCache", defaultValue = "false")
    private boolean useLocalCache;

    @RequestMapping("/useLocalCache")
    public boolean getUseLocalCache() {
        return useLocalCache;
    }
}