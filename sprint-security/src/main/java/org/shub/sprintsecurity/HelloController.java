package org.shub.sprintsecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String greet(HttpServletRequest req)
    {
        return "Hello" + req.getSession().getId();
    }

    @GetMapping("/about")
    public String about(HttpServletRequest req)
    {
        return "About Page" + req.getSession().getId();
    }
}
