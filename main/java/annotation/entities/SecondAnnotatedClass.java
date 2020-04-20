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

    public void initService(){
        System.out.println("I am very lazy");
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public String getContributions() {
        return contributions;
    }

    public void setContributions(String contributions) {
        this.contributions = contributions;
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
