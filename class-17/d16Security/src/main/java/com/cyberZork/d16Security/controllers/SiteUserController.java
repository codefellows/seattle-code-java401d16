package com.cyberZork.d16Security.controllers;

import com.cyberZork.d16Security.models.SiteUser;
import com.cyberZork.d16Security.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

// TODO Step 2: Create controller for SiteUSer
@Controller
public class SiteUserController {
    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // THIS is how you do auto login
    @Autowired
    HttpServletRequest request;

    @GetMapping("login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup.html";
    }


    // GET route /
    // Principal == Http session == current User
    @GetMapping("/")
    public String getHome(Model m, Principal p){
        if (p != null){
            String username = p.getName();
            SiteUser dbUser = siteUserRepository.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("FirstName", dbUser.getFirstName());
        }
        try {

        } catch (RuntimeException runtimeException) {
            throw new RuntimeException("Oh dear, something went wrong");
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT, "There is a conflict");
//        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with the server");
//        return "index.html";
    }


    // GET route to /secret -> secret sauce!!
    @GetMapping("/secret")
    public String getSecretSauce(){
        return "secretSauce.html";
    }

    // GEt route to /users/id to get ONE user -> send this to user-info.html
    @GetMapping("/user/{id}")
    public String getOneSiteUser(@PathVariable Long id, Model m, Principal p){
        SiteUser authenticatedUser = siteUserRepository.findByUsername(p.getName());
        m.addAttribute("authUser", authenticatedUser);
        // find user by ID from DB
        SiteUser viewUser = siteUserRepository.findById(id).orElseThrow();
        // attach the user to the model
        m.addAttribute("viewUser", viewUser);
        return "user-info.html";
    }

    // POST route to create new SiteUser
    @PostMapping("/signup")
    public RedirectView createSiteUser(String username, String password, String firstName){
        // hash the PW
        String hashedPW = passwordEncoder.encode(password);
        // create User
        SiteUser newUser = new SiteUser(username, hashedPW, firstName, new Date());
        // Save the user
        siteUserRepository.save(newUser);
        // Auto login
        autoAuthWithHttpServletRequest(username, password);
        // Return redirectView
        return new RedirectView("/");
    }

    public void autoAuthWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        } catch (ServletException se) {
            se.printStackTrace();
        }
    }

    @PutMapping("/user/{id}")
    public RedirectView editSiteUserInfo(@PathVariable Long id, String username, String firstName, Principal p, RedirectAttributes redir) throws ServletException {
        // find the user we want to edit
        SiteUser userToBeEdited = siteUserRepository.findById(id).orElseThrow();
        if(p.getName().equals(userToBeEdited.getUsername())){
            // update user
            userToBeEdited.setUsername(username);
            userToBeEdited.setFirstName(firstName);
            // save the edited user back to the DB
            siteUserRepository.save(userToBeEdited);
            request.logout(); // TODO: fix this so the session gets updated!
            autoAuthWithHttpServletRequest(username, userToBeEdited.getPassword());
        } else {
            redir.addFlashAttribute("errorMessage", "Cannot edit another user's info");
        }
        return new RedirectView("/user/" + id);
    }

}
