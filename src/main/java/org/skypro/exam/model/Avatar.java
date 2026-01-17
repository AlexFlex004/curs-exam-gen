package org.skypro.exam.model;

import jakarta.persistence.*;

@Entity
public class Avatar {

    @Id

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "avatar_seq"
    )
    @SequenceGenerator(
            name = "avatar_seq",
            sequenceName = "avatar_sequence",
            allocationSize = 1
    )


    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] data;


}
