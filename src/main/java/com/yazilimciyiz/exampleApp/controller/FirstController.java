package com.yazilimciyiz.exampleApp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/first")
public class FirstController {

    List<String> aList = new ArrayList<>();

    public FirstController() {
        aList.add("a");
        aList.add("b");
    }

    @GetMapping
    public List<String> getHello() {
        return aList;
    }

    @GetMapping(value = "/check")
    public boolean check(@RequestParam(value = "newText", required = false) String checkText) {
        return aList.stream().anyMatch(str -> str.toLowerCase().equals(checkText));
    }

    @GetMapping(value = "/{hede}")
    public String getHede(@PathVariable("hede") String hede) {
        return "Hello " + hede;
    }

    @PostMapping
    public List<String> addString(@RequestBody String textToAdd) {
        if (Objects.nonNull(textToAdd))
            aList.add(textToAdd);
        return aList;
    }

    @DeleteMapping
    public List<String> deleteString(@RequestBody String textToRemove) {
        if (Objects.nonNull(textToRemove) && aList.stream().anyMatch(str -> str.toLowerCase().equals(textToRemove)))
            aList.remove(textToRemove);
        return aList;
    }

}
