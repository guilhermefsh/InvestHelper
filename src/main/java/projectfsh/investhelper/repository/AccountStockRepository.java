package projectfsh.investhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectfsh.investhelper.entity.AccountStock;
import projectfsh.investhelper.entity.AccountStockId;
import projectfsh.investhelper.entity.Stock;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
