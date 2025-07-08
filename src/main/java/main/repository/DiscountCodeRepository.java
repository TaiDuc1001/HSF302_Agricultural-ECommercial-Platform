package main.repository;

import main.pojo.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    DiscountCode findDiscountCodeByCode(String code);
}
