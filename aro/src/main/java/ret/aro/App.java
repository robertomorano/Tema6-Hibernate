package ret.aro;

import java.time.LocalDateTime;
import java.util.Scanner;

import ret.aro.crud.LikeCRUD;
import ret.aro.crud.PostsCRUD;
import ret.aro.crud.UserCRUD;
import ret.aro.entities.Like;
import ret.aro.entities.Post;
import ret.aro.entities.User;



public class App {
	static Scanner sc = new Scanner(System.in);
	static UserCRUD userCrud = new UserCRUD();
	static PostsCRUD postCrud = new PostsCRUD();
	static LikeCRUD likeCrud = new LikeCRUD();

	public static void main(String[] args) {

		System.out.println("Conectar base de datos");
		

		int opcion;
		do {
			menu();
			opcion = sc.nextInt();
			sc.nextLine(); // Consumir el salto de línea

			switch (opcion) {
			case 1: // Crear
				
				break;
			case 2: // Insertar
				insertar();
				break;
			case 3: // Listar
				listar();
				break;
			case 4: // Modificar
				modificar();
				break;
			case 5: // Borrar Datos
				whereDeleteFrom();
				break;
			case 6: // Eliminar Tablas
				eliminarTablas();
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);

		sc.close();
	}

	public static void menu() {
		System.out.println("\n--- MENÚ PRINCIPAL ---");
		System.out.println("1. Crear Tablas");
		System.out.println("2. Insertar Datos");
		System.out.println("3. Listar/Mostrar Datos");
		System.out.println("4. Modificar Datos (con Transacción)");
		System.out.println("5. Borrar Datos (con Transacción)");
		System.out.println("6. Eliminar Tablas (DROP)");
		System.out.println("0. Salir");
		System.out.print("Seleccione una opción: ");
	}

	

	public static void insertar() {
		System.out.println("\n--- INSERTAR DATOS ---");
		System.out.println("1. Usuarios");
		System.out.println("2. Posts (Relacionada)");
		System.out.println("3. Likes (Relacionada)");
		System.out.print("Seleccione la tabla para insertar: ");
		int op = sc.nextInt();
		sc.nextLine();
		switch (op) {
		case 1:
			userCrud.saveUsuario(createUser());
			break;
		case 2:
			likeCrud.saveLike(createLike());
			break;
		case 3:
			postCrud.savePosts(createPost());
			;
			break;
		}
	}

	private static User createUser() {
		User user = null;
		String name = "";
		String surname = "";
		String username = "";
		String password = "";
		String email = "";

		System.out.println("dame nombre");
		name = sc.next();
		System.out.println("dame apellido");
		surname = sc.next();
		System.out.println("dame username");
		username = sc.next();
		System.out.println("dame password");
		password = sc.next();
		System.out.println("dame email");
		email = sc.next();
		user = new User(name, surname, username, password, email);
		return user;
	}

	private static Post createPost() {
		Post post = null;
		int idUser = -1;
		String usernameRelacion = "";

		System.out.println("dame el username del usuario que publica (para la relacion idUsuarios)");
		usernameRelacion = sc.next();

		userCrud.getUsuarioById(idUser);

		System.out.println("Que id de los disponibles seleccionas");
		idUser = sc.nextInt();

		post = new Post(-1, idUser, LocalDateTime.now(), LocalDateTime.now());

		return post;
	}

	private static Like createLike() {
		Like like = null;
		String usernameRelacion = "";
		String postIdRelacion = "";
		int idUser = -1;
		int idPost = -1;

		System.out.println("dame el username del usuario que da el like (para la relacion idUsuarios)");
		usernameRelacion = sc.next();

		userCrud.getUsuarioById(idUser);

		

		System.out.println("\ndame el ID del Post al que se da like (para la relacion idPosts)");
		postIdRelacion = sc.next();

		User user = userCrud.getUsuarioById(idUser);
		
		
		
		
		idPost = sc.nextInt();


		if (idUser != -1 && idPost != -1) {

			like = new Like(-1, user.getId(), idPost);

		}

		return like;
	}

	private static void listar() {
		int op = 0;
		int table = 0;
		System.out.println("1. Mostrar todas los valores de una tabla");
		System.out.println("2. Mostrar con una condicion concreta");
		op = sc.nextInt();

		sc.nextLine();
		System.out.println("Dame la tabla\n"
				+ "1. Users\n"
				+ "2. Likes\n"
				+ "3. Posts");
		table = sc.nextInt();
		
		if (op == 2) {
			//Falta implementar funciones en el crud
			if (table == 1) {
				
				
				
			} else if (table == 2) {
				
			} else {
				
			}
		} else {
			if (table == 1) {
				for(User user: userCrud.getAllUsuarios()) {
					System.out.println(user);
				}
			} else if (table == 2) {
				for(Like user: likeCrud.getAllLikes()) {
					System.out.println(user);
				}
			} else {
				for(Post user: postCrud.getAllPostss()) {
					System.out.println(user);
				}
			}

		}
	}

	public static void modificar() {

		System.out.println("\n--- MODIFICAR DATOS ---");

		System.out.println("Seleccione la tabla a modificar (Usuarios, Posts, Likes):");
		System.out.print("> ");
		String table = sc.nextLine().trim();

		System.out.println("Introduzca el ID del registro que desea modificar:");
		if (table.equals("Usuarios")) {
			for(User user: userCrud.getAllUsuarios()) {
				System.out.println(user);
			}
		} else if (table.equals("Posts")) {
			for(Post user: postCrud.getAllPostss()) {
				System.out.println(user);
			}
		} else {
			for(Like user: likeCrud.getAllLikes()) {
				System.out.println(user);
			}
		}
		int idRegistro = sc.nextInt();

		System.out.println("Introduzca el nombre del campo que desea cambiar (ej: Nombre, email, updated_at):");
		System.out.print("> ");
		String campo = sc.nextLine().trim();

		System.out.println("Introduzca el NUEVO VALOR para el campo '" + campo + "':");
		System.out.print("> ");

		String nuevoValor = sc.nextLine();

		userCrud.updateUsuario(null);
		int resultado =0 ;
		
		if(resultado<=0) {
			System.out.println("Exito al modificar");
		}else {
			System.out.println("Error al modificar");
		}

	}

	public static void whereDeleteFrom() {
		String value = "";
		String field = "";
		String table = "";
		int id = 0;
		System.out.println("Dame la tabla y el valor del campo a eliminar y el valor a eliminar");
		table = sc.next();
		field = sc.next();
		value = sc.next();
		userCrud.deleteUsuario(id);
	}

	

}
/**
 * package ret.aro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import ret.aro.crud.LikeCRUD;
import ret.aro.crud.PostsCRUD;
import ret.aro.crud.UserCRUD;
import ret.aro.entities.Like;
import ret.aro.entities.Post;
import ret.aro.entities.User;

public class App {

    static Scanner sc = new Scanner(System.in);
    static UserCRUD userCrud = new UserCRUD();
    static PostsCRUD postCrud = new PostsCRUD();
    static LikeCRUD likeCrud = new LikeCRUD();

    // =========================================================
    //  MAIN
    // =========================================================
    public static void main(String[] args) {

        System.out.println("=== Conectado a la base de datos via Hibernate ===");

        int opcion;
        do {
            menuPrincipal();
            opcion = leerInt();

            switch (opcion) {
                case 1 -> menuInsertar();
                case 2 -> menuBuscarPorId();
                case 3 -> menuListarTodos();
                case 4 -> menuModificar();
                case 5 -> menuEliminar();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // =========================================================
    //  MENUS DE NAVEGACION
    // =========================================================
    private static void menuPrincipal() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1. Insertar registro");
        System.out.println("2. Buscar registro por ID");
        System.out.println("3. Listar todos los registros");
        System.out.println("4. Modificar registro");
        System.out.println("5. Eliminar registro");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int menuTabla() {
        System.out.println("\n  Seleccione la tabla:");
        System.out.println("  1. Usuarios");
        System.out.println("  2. Posts");
        System.out.println("  3. Likes");
        System.out.print("  Opcion: ");
        return leerInt();
    }

    // =========================================================
    //  1. INSERTAR
    // =========================================================
    private static void menuInsertar() {
        System.out.println("\n--- INSERTAR REGISTRO ---");
        switch (menuTabla()) {
            case 1 -> {
                User u = pedirDatosUsuario();
                if (u != null) {
                    userCrud.saveUsuario(u);
                    System.out.println("Usuario insertado correctamente.");
                }
            }
            case 2 -> {
                Post p = pedirDatosPost();
                if (p != null) {
                    postCrud.savePosts(p);
                    System.out.println("Post insertado correctamente.");
                }
            }
            case 3 -> {
                Like l = pedirDatosLike();
                if (l != null) {
                    likeCrud.saveLike(l);
                    System.out.println("Like insertado correctamente.");
                }
            }
            default -> System.out.println("Tabla no valida.");
        }
    }

    // =========================================================
    //  2. BUSCAR POR ID
    // =========================================================
    private static void menuBuscarPorId() {
        System.out.println("\n--- BUSCAR POR ID ---");
        int tabla = menuTabla();
        System.out.print("  Introduce el ID: ");
        int id = leerInt();

        switch (tabla) {
            case 1 -> {
                User u = userCrud.getUsuarioById(id);
                if (u != null) System.out.println("Resultado: " + u);
                else           System.out.println("No se encontro ningun Usuario con id=" + id);
            }
            case 2 -> {
                Post p = postCrud.getPostsById(id);
                if (p != null) System.out.println("Resultado: " + p);
                else           System.out.println("No se encontro ningun Post con id=" + id);
            }
            case 3 -> {
                Like l = likeCrud.getLikeById(id);
                if (l != null) System.out.println("Resultado: " + l);
                else           System.out.println("No se encontro ningun Like con id=" + id);
            }
            default -> System.out.println("Tabla no valida.");
        }
    }

    // =========================================================
    //  3. LISTAR TODOS
    // =========================================================
    private static void menuListarTodos() {
        System.out.println("\n--- LISTAR TODOS LOS REGISTROS ---");
        switch (menuTabla()) {
            case 1 -> {
                List<User> usuarios = userCrud.getAllUsuarios();
                if (usuarios == null || usuarios.isEmpty()) {
                    System.out.println("No hay usuarios registrados.");
                } else {
                    System.out.println("--- Usuarios (" + usuarios.size() + ") ---");
                    for (User u : usuarios) System.out.println("  " + u);
                }
            }
            case 2 -> {
                List<Post> posts = postCrud.getAllPostss();
                if (posts == null || posts.isEmpty()) {
                    System.out.println("No hay posts registrados.");
                } else {
                    System.out.println("--- Posts (" + posts.size() + ") ---");
                    for (Post p : posts) System.out.println("  " + p);
                }
            }
            case 3 -> {
                List<Like> likes = likeCrud.getAllLikes();
                if (likes == null || likes.isEmpty()) {
                    System.out.println("No hay likes registrados.");
                } else {
                    System.out.println("--- Likes (" + likes.size() + ") ---");
                    for (Like l : likes) System.out.println("  " + l);
                }
            }
            default -> System.out.println("Tabla no valida.");
        }
    }

    // =========================================================
    //  4. MODIFICAR
    // =========================================================
    private static void menuModificar() {
        System.out.println("\n--- MODIFICAR REGISTRO ---");
        int tabla = menuTabla();

        switch (tabla) {
            case 1 -> modificarUsuario();
            case 2 -> modificarPost();
            case 3 -> modificarLike();
            default -> System.out.println("Tabla no valida.");
        }
    }

    private static void modificarUsuario() {
        // Mostrar todos para que el usuario elija
        List<User> lista = userCrud.getAllUsuarios();
        if (lista == null || lista.isEmpty()) { System.out.println("No hay usuarios."); return; }
        System.out.println("Usuarios disponibles:");
        for (User u : lista) System.out.println("  id=" + u.getId() + " | " + u);

        System.out.print("ID del usuario a modificar: ");
        int id = leerInt();
        User u = userCrud.getUsuarioById(id);
        if (u == null) { System.out.println("No existe el usuario con id=" + id); return; }

        System.out.println("  Campos: nombre | apellido | username | password | email");
        System.out.print("  Campo a modificar: ");
        String campo = sc.nextLine().trim();
        System.out.print("  Nuevo valor: ");
        String valor = sc.nextLine().trim();

        switch (campo.toLowerCase()) {
            case "nombre"   -> u.setNombre(valor);
            case "apellido" -> u.setApellido(valor);
            case "username" -> u.setUsername(valor);
            case "password" -> u.setPassword(valor);
            case "email"    -> u.setEmail(valor);
            default -> { System.out.println("Campo no reconocido."); return; }
        }

        userCrud.updateUsuario(u);
        System.out.println("Usuario actualizado: " + u);
    }

    private static void modificarPost() {
        List<Post> lista = postCrud.getAllPostss();
        if (lista == null || lista.isEmpty()) { System.out.println("No hay posts."); return; }
        System.out.println("Posts disponibles:");
        for (Post p : lista) System.out.println("  " + p);

        System.out.print("ID del post a modificar: ");
        int id = leerInt();
        Post p = postCrud.getPostsById(id);
        if (p == null) { System.out.println("No existe el post con id=" + id); return; }

        System.out.println("  Campos: idUser | updated_at (se actualiza automaticamente)");
        System.out.print("  Campo a modificar: ");
        String campo = sc.nextLine().trim();
        System.out.print("  Nuevo valor (para idUser introduce un numero): ");
        String valor = sc.nextLine().trim();

        switch (campo.toLowerCase()) {
            case "iduser" -> {
                try { p.setIdUser(Integer.parseInt(valor)); }
                catch (NumberFormatException e) { System.out.println("Valor invalido para idUser."); return; }
            }
            case "updated_at" -> p.setUpdatedAt(LocalDateTime.now());
            default -> { System.out.println("Campo no reconocido."); return; }
        }
        p.setUpdatedAt(LocalDateTime.now());

        postCrud.updatePosts(p);
        System.out.println("Post actualizado: " + p);
    }

    private static void modificarLike() {
        List<Like> lista = likeCrud.getAllLikes();
        if (lista == null || lista.isEmpty()) { System.out.println("No hay likes."); return; }
        System.out.println("Likes disponibles:");
        for (Like l : lista) System.out.println("  " + l);

        System.out.print("ID del like a modificar: ");
        int id = leerInt();
        Like l = likeCrud.getLikeById(id);
        if (l == null) { System.out.println("No existe el like con id=" + id); return; }

        System.out.println("  Campos: idUser | idPost");
        System.out.print("  Campo a modificar: ");
        String campo = sc.nextLine().trim();
        System.out.print("  Nuevo valor (numero): ");
        String valor = sc.nextLine().trim();

        try {
            switch (campo.toLowerCase()) {
                case "iduser" -> l.setIdUser(Integer.parseInt(valor));
                case "idpost" -> l.setIdPost(Integer.parseInt(valor));
                default -> { System.out.println("Campo no reconocido."); return; }
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido, debe ser un numero.");
            return;
        }

        likeCrud.updateLike(l);
        System.out.println("Like actualizado: " + l);
    }

    // =========================================================
    //  5. ELIMINAR
    // =========================================================
    private static void menuEliminar() {
        System.out.println("\n--- ELIMINAR REGISTRO ---");
        int tabla = menuTabla();

        // Mostrar registros actuales para facilitar la elección
        switch (tabla) {
            case 1 -> {
                List<User> lista = userCrud.getAllUsuarios();
                if (lista == null || lista.isEmpty()) { System.out.println("No hay usuarios."); return; }
                System.out.println("Usuarios disponibles:");
                for (User u : lista) System.out.println("  id=" + u.getId() + " | " + u);

                System.out.print("ID del usuario a eliminar: ");
                int id = leerInt();
                userCrud.deleteUsuario(id);
                System.out.println("Usuario con id=" + id + " eliminado (si existia).");
            }
            case 2 -> {
                List<Post> lista = postCrud.getAllPostss();
                if (lista == null || lista.isEmpty()) { System.out.println("No hay posts."); return; }
                System.out.println("Posts disponibles:");
                for (Post p : lista) System.out.println("  " + p);

                System.out.print("ID del post a eliminar: ");
                int id = leerInt();
                postCrud.deletePosts(id);
                System.out.println("Post con id=" + id + " eliminado (si existia).");
            }
            case 3 -> {
                List<Like> lista = likeCrud.getAllLikes();
                if (lista == null || lista.isEmpty()) { System.out.println("No hay likes."); return; }
                System.out.println("Likes disponibles:");
                for (Like l : lista) System.out.println("  " + l);

                System.out.print("ID del like a eliminar: ");
                int id = leerInt();
                likeCrud.deleteLike(id);
                System.out.println("Like con id=" + id + " eliminado (si existia).");
            }
            default -> System.out.println("Tabla no valida.");
        }
    }

    // =========================================================
    //  HELPERS PARA CREAR ENTIDADES
    // =========================================================
    private static User pedirDatosUsuario() {
        System.out.print("  Nombre: ");      String nombre   = sc.nextLine().trim();
        System.out.print("  Apellido: ");    String apellido = sc.nextLine().trim();
        System.out.print("  Username: ");    String username = sc.nextLine().trim();
        System.out.print("  Password: ");    String password = sc.nextLine().trim();
        System.out.print("  Email: ");       String email    = sc.nextLine().trim();
        return new User(nombre, apellido, username, email, password);
    }

    private static Post pedirDatosPost() {
        // Mostrar usuarios disponibles para elegir el idUser
        List<User> usuarios = userCrud.getAllUsuarios();
        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados. Crea un usuario primero.");
            return null;
        }
        System.out.println("Usuarios disponibles:");
        for (User u : usuarios) System.out.println("  id=" + u.getId() + " | " + u);

        System.out.print("  ID del usuario que publica el post: ");
        int idUser = leerInt();

        User u = userCrud.getUsuarioById(idUser);
        if (u == null) {
            System.out.println("No existe un usuario con id=" + idUser);
            return null;
        }
        return new Post(idUser);
    }

    private static Like pedirDatosLike() {
        // Mostrar usuarios disponibles
        List<User> usuarios = userCrud.getAllUsuarios();
        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados. Crea un usuario primero.");
            return null;
        }
        System.out.println("Usuarios disponibles:");
        for (User u : usuarios) System.out.println("  id=" + u.getId() + " | " + u);
        System.out.print("  ID del usuario que da el like: ");
        int idUser = leerInt();

        if (userCrud.getUsuarioById(idUser) == null) {
            System.out.println("No existe un usuario con id=" + idUser);
            return null;
        }

        // Mostrar posts disponibles
        List<Post> posts = postCrud.getAllPostss();
        if (posts == null || posts.isEmpty()) {
            System.out.println("No hay posts registrados. Crea un post primero.");
            return null;
        }
        System.out.println("Posts disponibles:");
        for (Post p : posts) System.out.println("  " + p);
        System.out.print("  ID del post al que se da like: ");
        int idPost = leerInt();

        if (postCrud.getPostsById(idPost) == null) {
            System.out.println("No existe un post con id=" + idPost);
            return null;
        }

        return new Like(idUser, idPost);
    }

    // =========================================================
    //  UTILIDAD: leer entero con manejo de errores
    // =========================================================
    private static int leerInt() {
        while (true) {
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("  Entrada invalida, introduce un numero: ");
            }
        }
    }
}
 */