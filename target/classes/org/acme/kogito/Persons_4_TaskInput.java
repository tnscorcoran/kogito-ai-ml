package org.acme.kogito;

import java.util.Map;

public class Persons_4_TaskInput {

    private String _id;

    private String _name;

    public void setId(String id) {
        this._id = id;
    }

    public String getId() {
        return this._id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public static Persons_4_TaskInput fromMap(String id, String name, Map<String, Object> params) {
        Persons_4_TaskInput item = new Persons_4_TaskInput();
        item._id = id;
        item._name = name;
        item.person = (org.acme.kogito.model.Person) params.get("person");
        return item;
    }

    private org.acme.kogito.model.Person person;

    public org.acme.kogito.model.Person getPerson() {
        return person;
    }

    public void setPerson(org.acme.kogito.model.Person person) {
        this.person = person;
    }
}
// Task input model for user task 'Special handling for children' in process 'persons'
