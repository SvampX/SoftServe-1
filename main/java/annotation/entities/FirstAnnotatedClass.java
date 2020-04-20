package annotation.entities;

import annotation.Service;

@Service(name = "FairySimpleService")
public class FirstAnnotatedClass {
    private String name;
    private String age;
    private String weight;

    public void initService() throws Exception {
        System.out.println("I am OK");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
