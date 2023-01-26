package com.cyberZork.salmonCookiesD16.controllers;

import com.cyberZork.salmonCookiesD16.models.SalmonCookieStore;
import com.cyberZork.salmonCookiesD16.repositories.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class StoreController {
    // Wire up the Repository so we can use it to talk to our DB!
        // this contains all the methods we need for full CRUD. Also has our custom queries we made in the SalmonCookieStoreRepository
    @Autowired
    SalmonCookieStoreRepository salmonCookieStoreRepository;

    // get route to home
    @GetMapping("/")
    public String getHome(){
        return "index.html";
    }

    // GET ONE store
    @GetMapping("/stores/{id}")
    public String getOneStore(@PathVariable Long id, Model m){
        // find that store
        SalmonCookieStore returnedStore = salmonCookieStoreRepository.findById(id).orElseThrow();
        // send it to the view!
        m.addAttribute("stores", returnedStore);
        return "stores.html";
    }

    // get route to get ALL stores
    @GetMapping("/stores")
    public String getAllStores(Model m){
        // make a call to the db to get ALL stores
            // findAll returns a List of SalmonCookieStores
        List<SalmonCookieStore> salmonCookieStores = salmonCookieStoreRepository.findAll();
        // Send List to our View(our html template)
        m.addAttribute("stores", salmonCookieStores);
        return "stores.html";
    }

    // post route to stores
    @PostMapping("/stores")
    public RedirectView createAStore(String name, Integer averageCookiesPerDay){
        // create new store
        SalmonCookieStore salmonCookieStore = new SalmonCookieStore(name, averageCookiesPerDay);
        // add it to the DB
        salmonCookieStoreRepository.save(salmonCookieStore);
        return new RedirectView("/stores");
    }

}
