/*
package ru.ivan.instazoo.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "length")
    private Long length;
    @Lob
    @Column(name = "bytes", columnDefinition = "LONGBLOB")
    private byte[] bytes;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "image")
    private User user;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "image")
    private Post post;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @PrePersist
    private void onCreate(){
        this.creationTime = LocalDateTime.now();
    }
}
*/
