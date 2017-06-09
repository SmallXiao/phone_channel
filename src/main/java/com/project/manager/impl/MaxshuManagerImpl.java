package com.project.manager.impl;

import com.project.manager.MaxshuManager;

public class MaxshuManagerImpl implements MaxshuManager{

    public String sayHello(String hello) {
        System.out.println("receive " + hello);
        return hello;
    }
}
