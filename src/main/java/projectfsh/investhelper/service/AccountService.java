package projectfsh.investhelper.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projectfsh.investhelper.client.BrapiClient;
import projectfsh.investhelper.dtos.AccountStockResponseDTO;
import projectfsh.investhelper.dtos.AssociateAccountStockDTO;
import projectfsh.investhelper.entity.AccountStock;
import projectfsh.investhelper.entity.AccountStockId;
import projectfsh.investhelper.repository.AccountRepository;
import projectfsh.investhelper.repository.AccountStockRepository;
import projectfsh.investhelper.repository.StockRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;
    private final StockRepository stockRepository;
    private final AccountRepository accountRepository;
    private final AccountStockRepository accountStockRepository;
    private final BrapiClient braipiClient;


    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, BrapiClient braipiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.braipiClient = braipiClient;
    }

    public void associateStock(String accountId, AssociateAccountStockDTO associateDTO) {
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        var stock = stockRepository.findById(associateDTO.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var accountStockEntity = new AccountStock(
                id,
                account,
                stock,
                associateDTO.quantity()
        );

        accountStockRepository.save(accountStockEntity);
    }

    public List<AccountStockResponseDTO> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account nao existe"));

        return account.getAccountStocks()
                .stream()
                .map(ac -> new AccountStockResponseDTO(
                        ac.getStock().getStockId(),
                        ac.getQuantity(),
                        getTotal(ac.getQuantity(), ac.getStock().getStockId())
                ))
                .toList();

    }

    private double getTotal(Integer quantity, String stockId){
        var response = braipiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();
        BigDecimal total = BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(price));
        total = total.setScale(2, RoundingMode.HALF_UP);

        return total.doubleValue();
    }
}
