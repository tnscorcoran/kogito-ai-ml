package org.acme.kogito;

import java.util.Map;
import java.util.HashMap;

@org.kie.internal.kogito.codegen.Generated(value = "kogito-codegen", reference = "persons", name = "Persons", hidden = true)
public class PersonsModelOutput implements org.kie.kogito.Model {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap();
        params.put("person", this.person);
        return params;
    }

    @Override
    public void fromMap(Map<String, Object> params) {
        fromMap(null, params);
    }

    public void fromMap(String id, Map<String, Object> params) {
        this.id = id;
        this.person = (org.acme.kogito.model.Person) params.get("person");
    }

    @org.kie.internal.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "person")
    private org.acme.kogito.model.Person person;

    public org.acme.kogito.model.Person getPerson() {
        return person;
    }

    public void setPerson(org.acme.kogito.model.Person person) {
        this.person = person;
    }
}
