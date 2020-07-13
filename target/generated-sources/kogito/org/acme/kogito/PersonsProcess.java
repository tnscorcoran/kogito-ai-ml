package org.acme.kogito;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.drools.core.util.KieFunctions;

@javax.enterprise.context.ApplicationScoped()
@javax.inject.Named("persons")
public class PersonsProcess extends org.kie.kogito.process.impl.AbstractProcess<org.acme.kogito.PersonsModel> {

    @javax.inject.Inject()
    javax.enterprise.inject.Instance<org.kie.api.runtime.process.WorkItemHandler> handlers;

    org.kie.kogito.app.Application app;

    public PersonsProcess() {
    }

    @javax.inject.Inject()
    public PersonsProcess(org.kie.kogito.app.Application app) {
        super(app.config().process());
        this.app = app;
    }

    public org.acme.kogito.PersonsProcessInstance createInstance(org.acme.kogito.PersonsModel value) {
        return new org.acme.kogito.PersonsProcessInstance(this, value, this.createLegacyProcessRuntime());
    }

    public org.acme.kogito.PersonsProcessInstance createInstance(java.lang.String businessKey, org.acme.kogito.PersonsModel value) {
        return new org.acme.kogito.PersonsProcessInstance(this, value, businessKey, this.createLegacyProcessRuntime());
    }

    public org.acme.kogito.PersonsModel createModel() {
        return new org.acme.kogito.PersonsModel();
    }

    public org.acme.kogito.PersonsProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.acme.kogito.PersonsModel) value);
    }

    public org.acme.kogito.PersonsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.acme.kogito.PersonsModel) value);
    }

    public PersonsProcess configure() {
        super.configure();
        return this;
    }

    protected void registerListeners() {
    }

    public org.kie.api.definition.process.Process legacyProcess() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("persons");
        factory.variable("person", new ObjectDataType("org.acme.kogito.model.Person"), "customTags", null);
        factory.name("Person Process");
        factory.packageName("org.acme.kogito");
        factory.dynamic(false);
        factory.version("1.0");
        factory.visibility("Public");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        factory.imports("org.acme.kogito.model.Person");
        org.jbpm.ruleflow.core.factory.StartNodeFactory startNode1 = factory.startNode(1);
        startNode1.name("StartProcess");
        startNode1.interrupting(true);
        startNode1.metaData("UniqueId", "StartEvent_1");
        startNode1.metaData("elementname", "StartProcess");
        startNode1.metaData("x", 97);
        startNode1.metaData("width", 36);
        startNode1.metaData("y", 110);
        startNode1.metaData("height", 36);
        startNode1.done();
        org.jbpm.ruleflow.core.factory.RuleSetNodeFactory ruleSetNode2 = factory.ruleSetNode(2);
        ruleSetNode2.name("Evaluate person");
        ruleSetNode2.inMapping("person", "person");
        ruleSetNode2.outMapping("person", "person");
        ruleSetNode2.ruleFlowGroup("person", () -> {
            return app.ruleUnits().ruleRuntimeBuilder().newKieSession("defaultStatelessKieSession", app.config().rule());
        });
        ruleSetNode2.metaData("UniqueId", "BusinessRuleTask_1");
        ruleSetNode2.metaData("elementname", "Evaluate person");
        ruleSetNode2.metaData("x", 180);
        ruleSetNode2.metaData("width", 110);
        ruleSetNode2.metaData("y", 103);
        ruleSetNode2.metaData("height", 50);
        ruleSetNode2.done();
        org.jbpm.ruleflow.core.factory.SplitFactory splitNode3 = factory.splitNode(3);
        splitNode3.name("Exclusive Gateway 1");
        splitNode3.type(2);
        splitNode3.metaData("UniqueId", "ExclusiveGateway_1");
        splitNode3.metaData("x", 365);
        splitNode3.metaData("width", 50);
        splitNode3.metaData("y", 103);
        splitNode3.metaData("height", 50);
        splitNode3.constraint(4, "SequenceFlow_3", "DROOLS_DEFAULT", "java", kcontext -> {
            org.acme.kogito.model.Person person = (org.acme.kogito.model.Person) kcontext.getVariable("person");
            {
                return person.isAdult() == false;
            }
        }, 1);
        splitNode3.constraint(6, "SequenceFlow_5", "DROOLS_DEFAULT", "java", kcontext -> {
            org.acme.kogito.model.Person person = (org.acme.kogito.model.Person) kcontext.getVariable("person");
            {
                return person.isAdult() == true;
            }
        }, 1);
        splitNode3.done();
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory humanTaskNode4 = factory.humanTaskNode(4);
        humanTaskNode4.name("Special handling for children");
        humanTaskNode4.workParameter("TaskName", "ChildrenHandling");
        humanTaskNode4.workParameter("Priority", "1");
        humanTaskNode4.workParameter("Skippable", "true");
        humanTaskNode4.workParameter("Locale", "en-UK");
        humanTaskNode4.workParameter("NodeName", "Special handling for children");
        humanTaskNode4.inMapping("person", "person");
        humanTaskNode4.done();
        humanTaskNode4.metaData("UniqueId", "UserTask_1");
        humanTaskNode4.metaData("elementname", "Special handling for children");
        humanTaskNode4.metaData("x", 465);
        humanTaskNode4.metaData("width", 110);
        humanTaskNode4.metaData("y", 105);
        humanTaskNode4.metaData("height", 50);
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode5 = factory.endNode(5);
        endNode5.name("End Event 1");
        endNode5.terminate(false);
        endNode5.metaData("UniqueId", "EndEvent_1");
        endNode5.metaData("elementname", "End Event 1");
        endNode5.metaData("x", 622);
        endNode5.metaData("width", 36);
        endNode5.metaData("y", 112);
        endNode5.metaData("height", 36);
        endNode5.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode6 = factory.endNode(6);
        endNode6.name("End Event 2");
        endNode6.terminate(false);
        endNode6.metaData("UniqueId", "EndEvent_2");
        endNode6.metaData("elementname", "End Event 2");
        endNode6.metaData("x", 622);
        endNode6.metaData("width", 36);
        endNode6.metaData("y", 212);
        endNode6.metaData("height", 36);
        endNode6.done();
        factory.connection(1, 2, "SequenceFlow_1");
        factory.connection(2, 3, "SequenceFlow_2");
        factory.connection(3, 4, "SequenceFlow_3");
        factory.connection(4, 5, "SequenceFlow_4");
        factory.connection(3, 6, "SequenceFlow_5");
        factory.validate();
        return factory.getProcess();
    }

    public void init(@javax.enterprise.event.Observes() io.quarkus.runtime.StartupEvent event) {
        this.activate();
    }
}
