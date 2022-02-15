package pl.edu.wszib.quiz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tquiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

    public Quiz(int id, String name, Set<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    public Quiz() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Question> getQuestions() { return questions; }

    public void setQuestions(Set<Question> questions) { this.questions = questions; }

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    public ArrayList<Integer> getCorrectAnswersIds (){
        ArrayList<Integer> correct = new ArrayList<>();
        for (Question question: this.getQuestions()){
            for(Answer answer: question.getAnswers()){
                if( answer.isCorrect()){
                    correct.add(answer.getId());
                }
            }
        }
        return  correct;
    }
}
