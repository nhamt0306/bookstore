package edu.hcmute.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "topics")
public class TopicEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topic_name;
    private String topic_status = "Active";

    public TopicEntity(Long id, String topic_name, String topic_status) {
        this.id = id;
        this.topic_name = topic_name;
        this.topic_status = topic_status;
    }

    public TopicEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }
}
