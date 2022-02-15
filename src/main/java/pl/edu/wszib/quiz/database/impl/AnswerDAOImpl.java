package pl.edu.wszib.quiz.database.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.quiz.database.IAnswerDAO;
import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class AnswerDAOImpl implements IAnswerDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Answer> getAnswers() {
        Session session = this.sessionFactory.openSession();
        Query<Answer> query = session.createQuery("FROM pl.edu.wszib.quiz.model.Answer");
        List<Answer> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Answer getAnswerById(int answerId) {
        Session session = this.sessionFactory.openSession();
        Query<Answer> query = session.createQuery("FROM pl.edu.wszib.quiz.model.Answer WHERE id = :id");
        query.setParameter("id", answerId);
        try {
            Answer answer = query.getSingleResult();
            session.close();
            return answer;
        } catch (NoResultException e) {
            session.close();
            return null;
        }
    }

    @Override
    public void addAnswer(Answer answer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(answer);
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
