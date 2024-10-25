package projectfsh.investhelper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectfsh.investhelper.dtos.CreateStockDTO;
import projectfsh.investhelper.dtos.CreateUserDTO;
import projectfsh.investhelper.entity.User;
import projectfsh.investhelper.service.StockServices;

import java.net.URI;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private StockServices stockService;

    public StockController(StockServices stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createStockDTO) {
        stockService.createStock(createStockDTO);
        return ResponseEntity.ok().build();
    }

}
