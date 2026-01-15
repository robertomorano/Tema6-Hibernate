package ret.aro.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Like")
public class Like {
    @Id
    @Column(name = "idLikes")
    private int id;
    
    @Column(name = "idUsuarios")
    @ManyToOne(cascade=CascadeType.ALL)
    private int idUser; 
    
    @Column(name = "idPosts")
    private int idPost; 

    public Like() {}
    public Like(int id, int idUser, int idPost) {
        this.id = id;
        this.idUser = idUser;
        this.idPost = idPost;
    }

   
    public Like(int idUser, int idPost) {
        this.id = 0; 
        this.idUser = idUser;
        this.idPost = idPost;
    }

    // --- Métodos Getters ---

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdPost() {
        return idPost;
    }
    
    // --- Métodos Setters ---
    
    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return id == like.id;
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
                ", idPost= " + idPost;
    }
}
