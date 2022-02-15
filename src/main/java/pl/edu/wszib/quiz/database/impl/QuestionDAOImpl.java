package pl.edu.wszib.quiz.database.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.quiz.database.IQuestionDAO;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class QuestionDAOImpl implements IQuestionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Question> getQuestions() {
        Session session = this.sessionFactory.openSession();
        Query<Question> query = session.createQuery("FROM pl.edu.wszib.quiz.model.Question");
        List<Question> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Question getQuestionById(int questionId) {
        Session session = this.sessionFactory.openSession();
        Query<Question> query = session.createQuery("FROM pl.edu.wszib.quiz.model.Question WHERE id = :id");
        query.setParameter("id", questionId);
        try {
            Question question = query.getSingleResult();
            session.close();
            return question;
        } catch (NoResultException e) {
            session.close();
            return null;
        }
    }

    public void addQuestion(Question question) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(question);
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
