package com.pocketguide.controller;

import com.pocketguide.exception.OutgoNotFoundException;
import com.pocketguide.model.ErrorResponse;
import com.pocketguide.model.Outgo;
import com.pocketguide.service.OutgoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/spent")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class OutgoController {

    private OutgoService outgoService;

    @Autowired
    public OutgoController(OutgoService outgoService) {
        this.outgoService = outgoService;
    }

    @DeleteMapping("/{spentId}")
    public ResponseEntity<Mono> DeleteSpentById(
            @PathVariable Long spentId
    ) {
        try {
            outgoService.deleteById(spentId);
        } catch (OutgoNotFoundException e) {
            log.error("Spent with id {} not found", spentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(new ErrorResponse(e.getMessage())));
        }
        return ResponseEntity.ok(Mono.empty());
    }

    @GetMapping
    public ResponseEntity<Flux<List<Outgo>>> listSpending(){
        return ResponseEntity.ok(Flux.just(outgoService.listAll()));
    }

    @GetMapping("/{spentId}")
    public ResponseEntity<Mono> ListSpentById(
            @PathVariable Long spentId
    ) {
        Outgo outgo;

        try {
            outgo = outgoService.findById(spentId);
        } catch (OutgoNotFoundException e) {
            log.error("Spent with id {} not found", spentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(new ErrorResponse(e.getMessage())));
        }

        return ResponseEntity.ok(Mono.just(outgo));
    }

    @PostMapping
    public ResponseEntity<Mono<Outgo>> createSpent(@RequestBody Outgo outgo){
         return ResponseEntity.ok(Mono.just(outgoService.save(outgo)));
    }
}
