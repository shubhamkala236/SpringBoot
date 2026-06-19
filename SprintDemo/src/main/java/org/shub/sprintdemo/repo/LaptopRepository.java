package org.shub.sprintdemo.repo;

import org.shub.sprintdemo.model.Laptop;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopRepository {
    public void save(Laptop laptop){
        System.out.println("Saved in Database... " + laptop.toString());
    }
}
