package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.CommentEntity;
import edu.hcmute.bookstore.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> getAllByProductEntityId(Long id);

    @Query(value = "select count(*) from comments where user_id = :UserId",nativeQuery = true)
    Integer getTotalComment(@Param("UserId") Long UserId);

    @Query(value = "select * from comments where com_rating =  :Rating and product_id = :ProductId",nativeQuery = true)
    List<CommentEntity> filterCommentByRating(@Param("Rating") Long Rating, @Param("ProductId") Long ProductId);
}
