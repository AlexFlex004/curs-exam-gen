package org.skypro.exam.model;

import jakarta.persistence.*;

@Entity
public class Avatar {

    @Id

    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] data;


}
