package ret.aro.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ret.aro.entities.User;
import ret.aro.util.HibernateUtil;

public class UserCRUD {

	public void saveUsuario(User usuario) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(usuario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public User getUsuarioById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> getAllUsuarios() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Usuario", User.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateUsuario(User usuario) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(usuario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	
	public void deleteUsuario(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User usuario = session.get(User.class, id);
			if (usuario != null) {
				session.remove(usuario);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}
}
