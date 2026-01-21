package ret.aro;

import java.time.LocalDateTime;
import java.util.Scanner;

import ret.aro.crud.UserCRUD;
import ret.aro.entities.Like;
import ret.aro.entities.Post;
import ret.aro.entities.User;



public class App {
	static Scanner sc = new Scanner(System.in);
	static UserCRUD userCrud = new UserCRUD();
	static LikesCRUD userCrud = new LikesCRUD();
	static PostsRUD userCrud = new PostsCRUD();

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
			userCrud.insertInto(createLike());
			break;
		case 3:
			userCrud.insertInto(createPost());
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

		//Al ser una relacion quiero mostrar todos los elementos de la tabla con la que se relaciona, en este caso usuarios,
		//y que el usuario elija el nombre a partir de ese input sacar el id   
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

	private static int elegirTabla(){
		int table = 0;
		
		System.out.println("Dame la tabla\n"
				+ "1. Users\n"
				+ "2. Likes\n"
				+ "3. Posts");
		table = sc.nextInt();
		return table;
	}


	private static void listar() {
		int op = 0;
		int table = 0;
		System.out.println("1. Mostrar todas los valores de una tabla");
		System.out.println("2. Mostrar con una condicion concreta");
		op = sc.nextInt();

		sc.nextLine();
		table = elegirTabla();
		
		if (op == 2) {
			if (table == 1) {
				System.out.println("Dame el campo a cambiar y su nuevo valor");
				
				
			} else if (table == 2) {
				System.out.println("Dame el campo a cambiar y su nuevo valor");
				System.out.println(); userCrud.getLike(sc.next(), sc.next());
			} else {
				System.out.println("Dame el campo a cambiar y su nuevo valor");
				System.out.println( userCrud.getPost(sc.next(), sc.next()));
			}
		} else {
			if (table == 1) {
				for(User user: userCrud.getAllUsuarios()) {
					System.out.println(user);
				}
			} else if (table == 2) {
				for(Like user: userCrud.listLikes()) {
					System.out.println(user);
				}
			} else {
				for(Post user: userCrud.listPosts()) {
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
			for(User user: userCrud.listUsers()) {
				System.out.println(user);
			}
		} else if (table.equals("Posts")) {
			for(Post user: userCrud.listPosts()) {
				System.out.println(user);
			}
		} else {
			for(Like user: userCrud.listLikes()) {
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

		boolean resultado = userCrud.updateFromTable(table, campo, idRegistro, nuevoValor);
		
		if(resultado) {
			System.out.println("Exito al modificar");
		}else {
			System.out.println("Error al modificar");
		}

	}

	public static void whereDeleteFrom() {
		String value = "";
		String field = "";
		String table = "";
		System.out.println("Dame la tabla y el valor del campo a eliminar y el valor a eliminar");
		table = sc.next();
		field = sc.next();
		value = sc.next();
		userCrud.deleteFromTable(table, field, value);
	}

	public static void eliminarTablas() {
		System.out.println("");
		System.out.println("1. Eliminar TODAS las tablas (Likes, Posts, Usuarios)");
		System.out.println("2. Eliminar una tabla concreta");
		System.out.print("Opción: ");
		int op = sc.nextInt();
		sc.nextLine();

		if (op == 1) {
			userCrud.deleteTables();
		} else if (op == 2) {
			System.out.print("Nombre de la tabla a eliminar (Usuarios, Posts, Likes): ");
			String table = sc.nextLine();
			userCrud.deleteTable(table);
		}
	}

}
