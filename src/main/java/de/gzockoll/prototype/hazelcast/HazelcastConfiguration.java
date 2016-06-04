package de.gzockoll.prototype.hazelcast;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {
    @Bean
    public Config config() {
        return new Config().addMapConfig(
                new MapConfig()
                        .setName("doit")
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setTimeToLiveSeconds(20))
                .setNetworkConfig(networkConfig())
                .setProperty("hazelcast.logging.type","slf4j");
    }

    private NetworkConfig networkConfig() {
        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.getJoin().setMulticastConfig(new MulticastConfig().setEnabled(false))
                    .setTcpIpConfig(new TcpIpConfig().setEnabled(false));
        return networkConfig;
    }
}
