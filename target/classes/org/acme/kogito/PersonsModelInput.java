package org.acme.kogito;

import java.util.Map;
import java.util.HashMap;

@org.kie.internal.kogito.codegen.Generated(value = "kogito-codegen", reference = "persons", name = "Persons", hidden = true)
public class PersonsModelInput implements org.kie.kogito.Model {

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
