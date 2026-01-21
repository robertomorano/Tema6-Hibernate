package ret.aro.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ret.aro.entities.Like;
import ret.aro.util.HibernateUtil;

public class LikeCRUD {
	public void saveLike(Like like) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(like);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public Like getLikeById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Like.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Like> getAllLikes() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from like", Like.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateLike(Like like) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(like);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	
	public void deleteLike(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Like like = session.get(Like.class, id);
			if (like != null) {
				session.remove(like);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}
}
