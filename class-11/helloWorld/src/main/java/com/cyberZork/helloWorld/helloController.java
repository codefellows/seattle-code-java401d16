package com.cyberZork.helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class helloController {

    // routes and logic

    // INCLUDE ResponseBody IF you want JSON returned
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(){
        return "hello world";
    }

    // THIS IS HOW WE DO ROUTES
    @GetMapping("/hello")
    public String getHello(Model m){
        // Model(m) is how we send data to our thymeleaf templates
        m.addAttribute("name", "Kevin");
        return "hello.html";
    }

    // URL params is 1 one way we GET data from our View whatevce%20I%20want
    @ResponseBody
    @GetMapping("/speak/{word}")
    public String speak(@PathVariable String word){
        return word;
    }

    // 2nd way to GET data from our View is by Query Params. This needs a POST.

//    @PostMapping("hello")
//    @PutMapping()
//    @DeleteMapping

    // hardcoded albums
//    Album album1 = new Album();
//    Album album2 = new Album();
//    Album album3 = new Album();
}

