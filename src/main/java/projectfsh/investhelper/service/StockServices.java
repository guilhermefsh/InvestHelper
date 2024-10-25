package projectfsh.investhelper.service;

import org.springframework.stereotype.Service;
import projectfsh.investhelper.dtos.CreateStockDTO;
import projectfsh.investhelper.entity.Stock;
import projectfsh.investhelper.repository.StockRepository;

@Service
public class StockServices {

    private StockRepository stocksRepository;

    public StockServices(StockRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }


    public void createStock(CreateStockDTO createStockDTO) {

        var stock = new Stock(
                createStockDTO.stockId(),
                createStockDTO.description()
        );

        stocksRepository.save(stock);
    }
}
