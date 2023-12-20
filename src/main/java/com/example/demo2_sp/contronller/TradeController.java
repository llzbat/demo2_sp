package com.example.demo2_sp.contronller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trade")
public class TradeController {
    //进站
    @PostMapping("/pitted")
    public ResponseEntity<?> pitted(){
        return null;
    }
}
   