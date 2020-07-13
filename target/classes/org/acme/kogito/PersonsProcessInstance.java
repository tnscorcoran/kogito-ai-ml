package org.acme.kogito;

public class PersonsProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<PersonsModel> {

    public PersonsProcessInstance(org.acme.kogito.PersonsProcess process, PersonsModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public PersonsProcessInstance(org.acme.kogito.PersonsProcess process, PersonsModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    protected java.util.Map<String, Object> bind(PersonsModel variables) {
        return variables.toMap();
    }

    protected void unbind(PersonsModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
