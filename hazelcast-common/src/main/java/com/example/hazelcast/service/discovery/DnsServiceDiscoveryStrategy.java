package com.example.hazelcast.service.discovery;

import com.hazelcast.config.NetworkConfig;
import com.hazelcast.logging.ILogger;
import com.hazelcast.nio.Address;
import com.hazelcast.spi.discovery.AbstractDiscoveryStrategy;
import com.hazelcast.spi.discovery.DiscoveryNode;
import com.hazelcast.spi.discovery.SimpleDiscoveryNode;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.hazelcast.service.discovery.DnsServiceDiscoveryProperties.HOSTNAME;
import static com.example.hazelcast.service.discovery.DnsServiceDiscoveryProperties.PORT;

public class DnsServiceDiscoveryStrategy extends AbstractDiscoveryStrategy {
  private final String hostname;
  private final int port;
  private final ILogger logger;

  public DnsServiceDiscoveryStrategy(ILogger logger, Map<String, Comparable> properties) {
    super(logger, properties);
    this.hostname = getOrNull(HOSTNAME);
    this.port = getOrDefault(PORT, NetworkConfig.DEFAULT_PORT);
    this.logger = logger;
    logger.info("DnsServiceDiscoveryStrategy: created {hostname=" + this.hostname + "}");
  }

  @Override
  public Iterable<DiscoveryNode> discoverNodes() {
    List<DiscoveryNode> discoveryNodes = new ArrayList<>();
    try {
      InetAddress[] addresses = InetAddress.getAllByName(hostname);
      for (InetAddress address : addresses) {
        discoveryNodes.add(
            new SimpleDiscoveryNode(new Address(address.getHostAddress(), this.port)));
      }
    } catch (UnknownHostException e) {
      this.logger.warning(this.hostname + " can not be resolved", e);
    }
    return discoveryNodes;
  }

  @Override
  public void start() {
    logger.info("DnsServiceDiscoveryStrategy: started ");
  }

  @Override
  public void destroy() {
    logger.info("DnsServiceDiscoveryStrategy: destroyed");
  }
}
