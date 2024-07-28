# Load Balancer

This project is a basic implementation of a Load Balancer using the Round Robin method. The goal is to manage server registration and provide a mechanism to distribute requests evenly across available servers.

## Overview

A load balancer helps distribute incoming network or application traffic across multiple servers to ensure no single server becomes overwhelmed. This implementation uses a round-robin approach to cycle through active servers, ensuring an even distribution of requests.

## Classes

### Server.java

`Server.java` represents a server in the load balancer system.

- **Attributes**:
    - `name`: A unique identifier for the server.
    - `status`: A boolean indicating if the server is active.

- **Methods**:
    - `getName()`: Returns the server's Name.
    - `getStatus()`: Checks if the server is active.
    - `setStatus(boolean status)`: Sets the server's status.

### ServerRegistry.java

`ServerRegistry.java` is responsible for registering and managing servers in the load balancer.

- **Attributes**:
    - `servers`: A list of `Server` objects.

- **Methods**:
    - `registerServer(Server server)`: Adds a new server to the registry.
    - `deRegisterServer(Server server)`: Removes a server from the registry.
    - `getServer(String serverName)`: Retrieves a server by its name.
    - `getActiveServers()`: Returns a list of currently active servers.

### LoadBalancer.java

`LoadBalancer.java` is responsible for distributing requests to the next available server.

- **Attributes**:
    - `registry`: A reference to a `ServerRegistry` instance.
    - `lastAssignedServer`: An integer tracking the last used server index in the round-robin algorithm.

- **Methods**:
    - `getNextServer()`: Returns the next active server in the round-robin sequence.

## Usage

To use the load balancer:

1. Register servers with the `ServerRegistry`.
2. Update server status as needed (e.g., active or inactive).
3. Use the `LoadBalancer` to get the next available server for handling requests.

## Example

```java
public class Main {
    public static void main(String[] args) {
    
        ServerRegistry registry = new ServerRegistry();
        Server server1 = new Server("S101");
        Server server2 = new Server("S102");
        Server server3 = new Server("S103");

        LoadBalancer loadBalancer = new LoadBalancer(registry);
        server2.setStatus(false);

        for (int i = 0; i < 5; i++) {
            loadBalancer.getNextServer();
        }

        registry.deregisterServer(server1);

        loadBalancer.getNextServer();
    }
}
