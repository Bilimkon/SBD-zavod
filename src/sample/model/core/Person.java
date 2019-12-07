package sample.model.core;

import sample.components.models.AdminOperModel;

public class Person extends AdminOperModel {

    String id;
    String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
