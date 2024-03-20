package com.example.demo;


import com.example.demo.models.DemoEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final DemoRepository demoRepository;

    public DemoController(DemoRepository demoRepository){
        this.demoRepository=demoRepository;
    }

    @GetMapping("")
    public List<DemoEntity> get(){
        return demoRepository.findAll();
    }

    @PostMapping("")
    public DemoEntity post(@RequestBody DemoEntity payload){
        return demoRepository.save(payload);
    }

    @DeleteMapping
    public void deleteAll(){
        demoRepository.deleteAll();
    }
}
