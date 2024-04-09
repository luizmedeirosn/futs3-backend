package com.luizmedeirosn.futs3.entities;

import com.luizmedeirosn.futs3.shared.exceptions.DatabaseException;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_player_picture")
public class PlayerPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    private byte[] content;

    @OneToOne
    @MapsId
    private Player player;

    public PlayerPicture() {
    }

    public PlayerPicture(Long id, String filename, String contentType, byte[] content, Player player) {
        this.id = id;
        this.filename = filename;
        this.contentType = contentType;
        this.content = content;
        this.player = player;
    }

    public PlayerPicture(String filename, String contentType, byte[] content, Player player) {
        this.filename = filename;
        this.contentType = contentType;
        this.content = content;
        this.player = player;
    }

    public PlayerPicture(Player player, MultipartFile playerPicture) {
        try {
            if (playerPicture != null) {
                filename = playerPicture.getOriginalFilename();
                contentType = playerPicture.getContentType();
                content = playerPicture.getBytes();
            }
            this.player = player;

        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateData(MultipartFile playerPicture) {
        try {
            filename = playerPicture.getOriginalFilename();
            contentType = playerPicture.getContentType();
            content = playerPicture.getBytes();
        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPicture that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
