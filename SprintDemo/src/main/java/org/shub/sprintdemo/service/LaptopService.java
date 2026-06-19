package org.shub.sprintdemo.service;

import org.shub.sprintdemo.model.Laptop;
import org.shub.sprintdemo.repo.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository repo;

    public void addLaptop(Laptop laptop){
        System.out.println("Service Called...");
        repo.save(laptop);
    }

    public boolean isGoodForProg(Laptop laptop){
        return true;
    }
}
