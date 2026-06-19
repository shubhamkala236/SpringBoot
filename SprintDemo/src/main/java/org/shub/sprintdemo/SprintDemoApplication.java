package org.shub.sprintdemo;

import org.shub.sprintdemo.model.Alien;
import org.shub.sprintdemo.model.Laptop;
import org.shub.sprintdemo.service.LaptopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SprintDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SprintDemoApplication.class, args);

        LaptopService service = ctx.getBean(LaptopService.class);

        Laptop laptop = ctx.getBean(Laptop.class);

        service.addLaptop(laptop);

//        Alien obj = ctx.getBean(Alien.class);
//        obj.code();
//        System.out.println(obj.getAge());
    }

}
