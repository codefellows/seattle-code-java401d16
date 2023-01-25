package com.cyberZork.helloWorld;

import com.cyberZork.helloWorld.models.Album;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class albumController {

    // getg splash page
    @GetMapping("/")
    public String getHome(){
        return "welcome.html";
    }

    // get /albums
    @GetMapping("/albums")
    public String getAllAlbums(Model m){
        // first, create albums
        Album album1 = new Album("Title", "Artist", 27, 27.59, "imageURL");
        Album album2 = new Album("Title", "Artist", 27, 27.59, "imageURL");
        Album album3 = new Album("Title", "Artist", 27, 27.59, "imageURL");

        // second, create an ArrayList to hold them
        List<Album> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);
        albums.add(album3);

        // then send the albums to the view
        // Using the Model, include key:value pairs
        m.addAttribute("albums", albums);
        // return to the template of album
        return "albums.html";

    }

    // hardcoded albums
//    Album album2 = new Album();
//    Album album3 = new Album();
}

