package org.codekafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ServerRegistry class manages the registration and de-registration of servers.
 */
public class ServerRegistry {
    private final List<Server> servers = new ArrayList<>();

    /**
     * Registers a server in the serverRegistry and sets its status to 'true'.
     *
     * @param server -> the server to register
     */
    public void registerServer(Server server){
        servers.add(server);
    }

    /**
     * De-registers a server from the serverRegistry and sets its status to 'false'.
     *
     * @param serverName -> the name of the server to de-register
     */
    public void deRegisterServer(Server server){
       // serverRegistry.removeIf( item ->  item.getName().equals(serverName));

        servers.stream()
                .filter(s -> s.getName().equals(server.getName()))
                .findFirst()
                .ifPresent(s -> {
                    s.setStatus(false); // Set server status to false when de-registered
                    servers.remove(s);
                });

    }
    /**
     * Gets all the active servers from the serverRegistry.
     *
     * @return a list of active servers
     */
    public List<Server> getActiveServers() {
        return servers.stream()
                .filter(Server::getStatus).toList();
    }

    /**
     * Gets a server by its name from the serverRegistry.
     *
     * @param server the server to retrieve
     * @return an Optional containing the server if found, or an empty Optional if not found
     */
    public Optional<Server> getServer(String serverName) {
        // get the server from the serverRegistry
        return servers.stream()
                .filter(s -> s.getName().equals(serverName))
                .findFirst();
    }
}
