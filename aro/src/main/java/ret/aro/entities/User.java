package ret.aro.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="Usuaruos")
public class User {
	
	@Id
    @Column(name = "idUsuarios")
    private int id;
	@Column(name = "Nombre")
    private String nombre;
	@Column(name = "Apellidos")
    private String apellido;
	@Column(name = "Username")
    private String username;
	@Column(name = "Password")
    private String password;
	@Column(name = "email")
    private String email;

	public User(){}
    public User(String nombre, String apellido, String username, String email, String password) {
        this.id = 0;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password; 
        this.email = email;
    }
    

    public User(int id, String nombre, String apellido, String username, String password, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // --- Métodos Getters ---

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    

    @Override
    public String toString() {
        return "" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\''
     ;
    }
    
 
    
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
}
