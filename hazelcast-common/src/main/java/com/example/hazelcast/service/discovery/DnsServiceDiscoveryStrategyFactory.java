package com.example.hazelcast.service.discovery;

import com.hazelcast.config.properties.PropertyDefinition;
import com.hazelcast.logging.ILogger;
import com.hazelcast.spi.discovery.DiscoveryNode;
import com.hazelcast.spi.discovery.DiscoveryStrategy;
import com.hazelcast.spi.discovery.DiscoveryStrategyFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.example.hazelcast.service.discovery.DnsServiceDiscoveryProperties.HOSTNAME;
import static com.example.hazelcast.service.discovery.DnsServiceDiscoveryProperties.PORT;

public class DnsServiceDiscoveryStrategyFactory implements DiscoveryStrategyFactory {
  private static final Collection<PropertyDefinition> PROPERTY_DEFINITIONS =
      Collections.unmodifiableCollection(Arrays.asList(HOSTNAME, PORT));

  @Override
  public Class<? extends DiscoveryStrategy> getDiscoveryStrategyType() {
    return DnsServiceDiscoveryStrategy.class;
  }

  @Override
  public DiscoveryStrategy newDiscoveryStrategy(
      DiscoveryNode discoveryNode, ILogger logger, Map<String, Comparable> properties) {
    logger.info(
        "DnsServiceDiscoveryStrategyFactory.newDiscoveryStrategy(properties=" + properties + ")");
    return new DnsServiceDiscoveryStrategy(logger, properties);
  }

  @Override
  public Collection<PropertyDefinition> getConfigurationProperties() {
    return PROPERTY_DEFINITIONS;
  }
}
