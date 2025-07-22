package main.repository;

import main.pojo.Produce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Long> {
    List<Produce> findByUser_Id(Long userId);
}
