package com.waiterraterclient.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "visits")
public class Visit implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visit_id")
    private Long visitId;

    @ManyToOne
    @JoinColumn(name = "visit_server", referencedColumnName = "server_id")
    private Server server;

    @Column(name = "visit_comment")
    private String comment;

    public Long getVisitId() {
        return visitId;
    }

    public Server getServer() {
        return server;
    }

    public String getComment() {
        return comment;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
