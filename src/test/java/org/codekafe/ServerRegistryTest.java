package org.codekafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ServerRegistryTest {
    private ServerRegistry registry;
    private Server server1;
    private Server server2;

    @BeforeEach
    public void setUp(){
        registry = new ServerRegistry();

        server1 = new Server("S101");//by default the server is set to active
        server2 = new Server("S102", false); // setting only the one server active

    }
    @Test
    void testRegisterServer() {

        registry.registerServer(server1);
        assertThat(registry.getServer(server1.getName())).isPresent().containsSame(server1);

    }

    @Test
    void testDeRegisterServer() {
        registry.registerServer(server1);
        registry.deRegisterServer(server1);
        assertThat(registry.getServer(server1.getName())).isNotPresent();
    }

    @Test
    void testGetActiveServers() {
        registry.registerServer(server1);
        registry.registerServer(server2); // Assuming this server is down and it is not active

        List<Server> activeServers = registry.getActiveServers();
        assertThat(activeServers).containsExactly(server1);
    }

    @Test
    void testGetServer() {

        registry.registerServer(server1);

        Optional<Server> foundServer = registry.getServer(server1.getName());
        assertThat(foundServer).isPresent().contains(server1);

        Optional<Server> notFoundServer = registry.getServer(server2.getName());
        assertThat(notFoundServer).isNotPresent();
    }

}