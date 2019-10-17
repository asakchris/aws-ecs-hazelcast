package com.example.hazelcast.service.discovery;

import com.hazelcast.config.properties.PropertyDefinition;
import com.hazelcast.config.properties.SimplePropertyDefinition;

import static com.hazelcast.config.properties.PropertyTypeConverter.INTEGER;
import static com.hazelcast.config.properties.PropertyTypeConverter.STRING;

public class DnsServiceDiscoveryProperties {
  public static final PropertyDefinition HOSTNAME =
      new SimplePropertyDefinition("hostname", false, STRING);
  public static final PropertyDefinition PORT = new SimplePropertyDefinition("port", true, INTEGER);
}
