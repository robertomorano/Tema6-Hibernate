package ret.aro.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ret.aro.entities.Post;
import ret.aro.util.HibernateUtil;

public class PostsCRUD {
	public void savePosts(Post Posts) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(Posts);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public Post getPostsById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Post.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Post> getAllPostss() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Post", Post.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public void updatePosts(Post Posts) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(Posts);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	
	public void deletePosts(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Post Posts = session.get(Post.class, id);
			if (Posts != null) {
				session.remove(Posts);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}
}
