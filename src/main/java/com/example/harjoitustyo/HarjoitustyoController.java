package com.example.harjoitustyo;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
public class HarjoitustyoController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("teksti", "Hei mualima!");
        return "index";
    }

    private String message;
    // 'content' pitää vastaa HTML:n name='' osiota
    @PostMapping("/")
    public String posting(@RequestParam String content) { 
        this.message = content;
        System.out.println(message);
        return "redirect:/";
    }

   @GetMapping("/json")
    public String getJsonData(Model model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("db.json");
        JsonNode rootNode = objectMapper.readTree(resource.getInputStream());
        // Assuming "items" is the property name in your JSON
        JsonNode itemsNode = rootNode.get("items");
        // Deserialize the list of items
        List<Item> itemList = objectMapper.readValue(itemsNode.toString(), new TypeReference<List<Item>>() {});
        model.addAttribute("jsonData", itemList);

        return "json";
    }
}

