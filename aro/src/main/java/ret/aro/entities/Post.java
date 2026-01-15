package ret.aro.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="Post")
public class Post {
    @Id
    @Column(name = "idPosts")
    private int id;
    
    @OneToMany(cascade=CascadeType.ALL)
    @Column(name ="idUsuarios")
    private int idUser; 
    @Column(name ="created_at")
    private LocalDateTime createdAt;
    @Column(name ="updated_at")
    private LocalDateTime updatedAt;
    
    

    public Post(int id, int idUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.idUser = idUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Post(int idUser) {
        this.id = 0; 
        this.idUser = idUser;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // --- Métodos Getters ---

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    // --- Métodos Setters ---
    
    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return 
                "id= " + id +
                ", idUser= " + idUser +
                ", createdAt= " + createdAt +
                ", updatedAt= " + updatedAt ;
    }
}
