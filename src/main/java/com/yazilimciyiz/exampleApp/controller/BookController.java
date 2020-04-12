package com.yazilimciyiz.exampleApp.controller;

import com.yazilimciyiz.exampleApp.domain.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getBooks() {
        return jdbcTemplate.queryForList("select * from book;");
    }

    @GetMapping(value = "/name")
    public List<String> getBookNames() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select name from book;");

        List<String> names = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            String name = (String) map.get("NAME");
            names.add(name);
        }

        return names;
    }

//    @GetMapping(value = "/insert")
//    public List<Map<String, Object>> insertBook(@RequestParam("name") String name,
//                                                @RequestParam("page") int page) {
//        jdbcTemplate.update("insert into book (name, page) values ( ?, ? )", name, page);
//        return jdbcTemplate.queryForList("select * from book;");
//    }

    @PostMapping(value = "/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Map<String, Object>> insertBook(@RequestBody BookDTO bookDTO) {
        jdbcTemplate.update("insert into book (name, page) values ( ?, ? )", bookDTO.getName(), bookDTO.getPage());
        return jdbcTemplate.queryForList("select * from book;");
    }

    @DeleteMapping(value = "remove/{id}")
    public List<Map<String, Object>> removeBook(@PathVariable("id") int id) {
        jdbcTemplate.update("delete from book where id = ?", id);
        return jdbcTemplate.queryForList("select * from book;");
    }

}
