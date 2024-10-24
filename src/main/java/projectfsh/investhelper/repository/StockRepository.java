package projectfsh.investhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectfsh.investhelper.entity.Account;
import projectfsh.investhelper.entity.Stock;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
