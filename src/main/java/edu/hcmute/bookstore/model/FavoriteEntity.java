package edu.hcmute.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "favorities")
public class FavoriteEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
