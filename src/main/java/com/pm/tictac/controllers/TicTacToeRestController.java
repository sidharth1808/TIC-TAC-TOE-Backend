package com.pm.tictac.controllers;

import com.pm.tictac.dto.GameStateResponse;
import com.pm.tictac.dto.MakeMoveRequest;
import com.pm.tictac.dto.StartGameRequest;
import com.pm.tictac.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class TicTacToeRestController {
    
    @Autowired
    private GameService gameService;
    
    @PostMapping("/start")
    public ResponseEntity<GameStateResponse> startGame(@RequestBody StartGameRequest request) {
        try {
            GameStateResponse response = gameService.startGame(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{gameId}/move")
    public ResponseEntity<GameStateResponse> makeMove(
            @PathVariable String gameId,
            @RequestBody MakeMoveRequest request) {
        try {
            GameStateResponse response = gameService.makeMove(gameId, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{gameId}")
    public ResponseEntity<GameStateResponse> getGameState(@PathVariable String gameId) {
        try {
            GameStateResponse response = gameService.getGameState(gameId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
