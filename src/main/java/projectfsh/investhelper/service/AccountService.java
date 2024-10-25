package projectfsh.investhelper.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projectfsh.investhelper.dtos.AccountStockResponseDTO;
import projectfsh.investhelper.dtos.AssociateAccountStockDTO;
import projectfsh.investhelper.entity.AccountStock;
import projectfsh.investhelper.entity.AccountStockId;
import projectfsh.investhelper.repository.AccountRepository;
import projectfsh.investhelper.repository.AccountStockRepository;
import projectfsh.investhelper.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private final StockRepository stockRepository;
    private final AccountRepository accountRepository;
    private final AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
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
                .map(ac -> new AccountStockResponseDTO(ac.getStock().getStockId(), ac.getQuantity(), 0.0))
                .toList();

    }
}
