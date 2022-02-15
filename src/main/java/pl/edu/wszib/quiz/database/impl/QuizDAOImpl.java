package pl.edu.wszib.quiz.database.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.quiz.database.IQuizDAO;
import pl.edu.wszib.quiz.model.Quiz;
import pl.edu.wszib.quiz.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class QuizDAOImpl implements IQuizDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Quiz> getQuizzes() {
        Session session = this.sessionFactory.openSession();
        Query<Quiz> query = session.createQuery("FROM pl.edu.wszib.quiz.model.Quiz");
        List<Quiz> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Quiz getQuizById(int quizId) {
        Session session = this.sessionFactory.openSession();
        Query<Quiz> query = session.createQuery("FROM pl.edu.wszib.quiz.model.Quiz WHERE id = :id");
        query.setParameter("id", quizId);
        try {
            Quiz quiz = query.getSingleResult();
            session.close();
            return quiz;
        } catch (NoResultException e) {
            session.close();
            return null;
        }
    }

    @Override
    public void addQuiz(Quiz quiz) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(quiz);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Quiz getQuizByName(String quizname){
        Session session = this.sessionFactory.openSession();
        Query<Quiz> query = session.createQuery("FROM pl.edu.wszib.quiz.model.Quiz WHERE name = :name");
        query.setParameter("name", quizname);
        try {
            Quiz quiz = query.getSingleResult();
            session.close();
            return quiz;
        } catch (NoResultException e) {
            session.close();
            return null;
        }
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(quiz);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
