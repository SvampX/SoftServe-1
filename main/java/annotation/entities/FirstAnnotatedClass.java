package annotation.entities;

import annotation.Service;

@Service(name = "FairySimpleService")
public class FirstAnnotatedClass {
    private String name;
    private String age;
    private String weight;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FirstAnnotatedClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
