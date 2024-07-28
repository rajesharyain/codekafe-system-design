package org.codekafe;

public class Server {
    private String name;
    private Boolean status;

    public Server(String name) {
        this.name = name;
        this.status = true;
    }
    public Server(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Boolean getStatus(){
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
