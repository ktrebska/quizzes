package pl.edu.wszib.quiz.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tquestion")
public class Question {
    private String question;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Answer> answers = new HashSet<>();

    public Question(int id,String question,  Set<Answer> answers) {
        this.question = question;
        this.id = id;
        this.answers = answers;
    }



    public Question() {

    }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public int getId() { return id; }

    public void setId(int questionId) { this.id = id; }

    public Set<Answer> getAnswers() { return answers; }

    public void setAnswers(Set<Answer> answers) { this.answers = answers; }
}
