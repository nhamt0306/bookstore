package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> getAllByProductEntityId(Long id);
}
