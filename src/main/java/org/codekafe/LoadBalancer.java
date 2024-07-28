package org.codekafe;


import java.util.List;
import java.util.Optional;

/**
 * The LoadBalancer class is responsible for distributing load among the available servers.
 * It uses a round-robin algorithm to select the next server.
 */
public class LoadBalancer {
    private final ServerRegistry registry;
    static int lastAssignedServer = -1;

    /**
     * Constructs a LoadBalancer with the specified server registry.
     *
     * @param registry the server registry to use
     */
    public LoadBalancer(ServerRegistry registry){
        this.registry = registry;
    }

    /**
     * Gets the next available server using a round-robin algorithm.
     *
     * @return an Optional containing the next available server, or an empty Optional if no servers are available
     * @throws IllegalStateException if no active servers are available in the registry
     */
    public Optional<Server> getNextServer() throws IllegalStateException {

        List<Server> servers = registry.getActiveServers();
        if(servers.isEmpty()) {
            throw new IllegalStateException("Servers are not available");
        }
        lastAssignedServer = (lastAssignedServer + 1) % servers.size();
        Server server = servers.get(lastAssignedServer);
        return Optional.of(server);
    }
}
