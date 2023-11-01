package com.atguigu.ioc_01;

public class ClientService {
    private static ClientService clientService = new ClientService();
    public static ClientService createInstance() {
        return clientService;
    }
}
