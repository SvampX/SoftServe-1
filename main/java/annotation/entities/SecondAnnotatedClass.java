package annotation.entities;

import annotation.Service;

import java.util.Date;

@Service(name = "LazyAssService")
public class SecondAnnotatedClass {

    private Date birth;

    private Date death;

    private String contributions;

    public SecondAnnotatedClass() {
    }

    public Date getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return "SecondAnnotatedClass{" +
                "birth=" + birth +
                ", death=" + death +
                ", contributions=" + contributions +
                '}';
    }
}
