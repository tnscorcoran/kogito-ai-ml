package org.acme.kogito;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.kie.api.runtime.process.WorkItemNotFoundException;
import org.kie.kogito.Application;
import org.kie.kogito.auth.SecurityPolicy;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessInstanceExecutionException;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.process.workitem.Policy;
import org.kie.kogito.process.impl.Sig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.acme.kogito.PersonsModel;
import org.acme.kogito.PersonsModelOutput;

@Path("/persons")
@javax.enterprise.context.ApplicationScoped()
public class PersonsResource {

    @javax.inject.Inject()
    @javax.inject.Named("persons")
    Process<PersonsModel> process;

    @javax.inject.Inject()
    Application application;

    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonsModelOutput createResource_persons(@Context HttpHeaders httpHeaders, @QueryParam("businessKey") String businessKey, PersonsModelInput resource) {
        if (resource == null) {
            resource = new PersonsModelInput();
        }
        final PersonsModelInput value = resource;
        return org.kie.kogito.services.uow.UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> {
            ProcessInstance<PersonsModel> pi = process.createInstance(businessKey, mapInput(value, new PersonsModel()));
            String startFromNode = httpHeaders.getHeaderString("X-KOGITO-StartFromNode");
            if (startFromNode != null) {
                pi.startFrom(startFromNode);
            } else {
                pi.start();
            }
            return getModel(pi);
        });
    }

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonsModelOutput> getResources_persons() {
        return process.instances().values().stream().map(pi -> mapOutput(new PersonsModelOutput(), pi.variables())).collect(Collectors.toList());
    }

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonsModelOutput getResource_persons(@PathParam("id") String id) {
        return process.instances().findById(id).map(pi -> mapOutput(new PersonsModelOutput(), pi.variables())).orElse(null);
    }

    @DELETE()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonsModelOutput deleteResource_persons(@PathParam("id") final String id) {
        return org.kie.kogito.services.uow.UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> {
            ProcessInstance<PersonsModel> pi = process.instances().findById(id).orElse(null);
            if (pi == null) {
                return null;
            } else {
                pi.abort();
                return getModel(pi);
            }
        });
    }

    @POST()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonsModelOutput updateModel_persons(@PathParam("id") String id, PersonsModel resource) {
        return org.kie.kogito.services.uow.UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> {
            ProcessInstance<PersonsModel> pi = process.instances().findById(id).orElse(null);
            if (pi == null) {
                return null;
            } else {
                pi.updateVariables(resource);
                return mapOutput(new PersonsModelOutput(), pi.variables());
            }
        });
    }

    @GET()
    @Path("/{id}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getTasks_persons(@PathParam("id") String id, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return process.instances().findById(id).map(pi -> pi.workItems(policies(user, groups))).map(l -> l.stream().collect(Collectors.toMap(WorkItem::getId, WorkItem::getName))).orElse(null);
    }

    protected PersonsModelOutput getModel(ProcessInstance<PersonsModel> pi) {
        if (pi.status() == ProcessInstance.STATE_ERROR && pi.error().isPresent()) {
            throw new ProcessInstanceExecutionException(pi.id(), pi.error().get().failedNodeId(), pi.error().get().errorMessage());
        }
        return mapOutput(new PersonsModelOutput(), pi.variables());
    }

    protected Policy[] policies(String user, List<String> groups) {
        if (user == null) {
            return new Policy[0];
        }
        org.kie.kogito.auth.IdentityProvider identity = null;
        if (user != null) {
            identity = new org.kie.kogito.services.identity.StaticIdentityProvider(user, groups);
        }
        return new Policy[] { SecurityPolicy.of(identity) };
    }

    protected PersonsModel mapInput(PersonsModelInput input, PersonsModel resource) {
        resource.fromMap(input.toMap());
        return resource;
    }

    protected PersonsModelOutput mapOutput(PersonsModelOutput output, PersonsModel resource) {
        output.fromMap(resource.getId(), resource.toMap());
        return output;
    }

    @POST()
    @Path("/{id}/ChildrenHandling/{workItemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonsModelOutput completeTask_4(@PathParam("id") final String id, @PathParam("workItemId") final String workItemId, @QueryParam("phase") @DefaultValue("complete") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, final Persons_4_TaskOutput model) {
        try {
            return org.kie.kogito.services.uow.UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> {
                ProcessInstance<PersonsModel> pi = process.instances().findById(id).orElse(null);
                if (pi == null) {
                    return null;
                } else {
                    org.kie.kogito.auth.IdentityProvider identity = null;
                    if (user != null) {
                        identity = new org.kie.kogito.services.identity.StaticIdentityProvider(user, groups);
                    }
                    org.jbpm.process.instance.impl.humantask.HumanTaskTransition transition = new org.jbpm.process.instance.impl.humantask.HumanTaskTransition(phase, model.toMap(), identity);
                    pi.transitionWorkItem(workItemId, transition);
                    return getModel(pi);
                }
            });
        } catch (WorkItemNotFoundException e) {
            return null;
        }
    }

    @GET()
    @Path("/{id}/ChildrenHandling/{workItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Persons_4_TaskInput getTask_4(@PathParam("id") String id, @PathParam("workItemId") String workItemId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        try {
            ProcessInstance<PersonsModel> pi = process.instances().findById(id).orElse(null);
            if (pi == null) {
                return null;
            } else {
                WorkItem workItem = pi.workItem(workItemId, policies(user, groups));
                if (workItem == null) {
                    return null;
                }
                return Persons_4_TaskInput.fromMap(workItem.getId(), workItem.getName(), workItem.getParameters());
            }
        } catch (WorkItemNotFoundException e) {
            return null;
        }
    }

    @DELETE()
    @Path("/{id}/ChildrenHandling/{workItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonsModelOutput abortTask_4(@PathParam("id") final String id, @PathParam("workItemId") final String workItemId, @QueryParam("phase") @DefaultValue("abort") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        try {
            return org.kie.kogito.services.uow.UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> {
                ProcessInstance<PersonsModel> pi = process.instances().findById(id).orElse(null);
                if (pi == null) {
                    return null;
                } else {
                    org.kie.kogito.auth.IdentityProvider identity = null;
                    if (user != null) {
                        identity = new org.kie.kogito.services.identity.StaticIdentityProvider(user, groups);
                    }
                    org.jbpm.process.instance.impl.humantask.HumanTaskTransition transition = new org.jbpm.process.instance.impl.humantask.HumanTaskTransition(phase, null, identity);
                    pi.transitionWorkItem(workItemId, transition);
                    return getModel(pi);
                }
            });
        } catch (WorkItemNotFoundException e) {
            return null;
        }
    }
}
