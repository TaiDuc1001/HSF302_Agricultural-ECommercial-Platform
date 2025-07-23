package main.repository;

import main.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    int countByUser_Id(Long userId);

    List<Review> findByUser_Id(Long userId);

    List<Review> findAllByUser_Id(Long userId);
}
