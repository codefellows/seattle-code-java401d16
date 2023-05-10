package com.cyberZork.d16Security.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model m){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = (String) request.getAttribute("javax.servlet.error.message");
        if (status != null){
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                m.addAttribute("errorStatusCode", statusCode);
                m.addAttribute("errorMessage", "It's a 404");
                return "/error/404.html";
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                m.addAttribute("errorStatusCode", statusCode);
                m.addAttribute("errorMessage", "500: It's not you it's us");
                return "/error/500.html";
            }
        }
        m.addAttribute("errorStatusCode", status);
        m.addAttribute("errorMessage", message);
        return "/error/error.html";
    }
}
