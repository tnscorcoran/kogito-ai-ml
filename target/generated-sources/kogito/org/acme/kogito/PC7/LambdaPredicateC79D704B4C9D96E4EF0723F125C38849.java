package org.acme.kogito.PC7;

import static org.acme.kogito.RulesE405F32A817FD57B535435BB6FB960B3.*;
import org.acme.kogito.*;
import org.acme.kogito.model.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaPredicateC79D704B4C9D96E4EF0723F125C38849 implements org.drools.model.functions.Predicate1<org.acme.kogito.model.Person> {

    INSTANCE;

    public static final String EXPRESSION_HASH = "5FB5A734E172F0879D9D198117DB351C";

    @Override()
    public boolean test(org.acme.kogito.model.Person _this) throws java.lang.Exception {
        return org.drools.modelcompiler.util.EvaluationUtil.greaterThanNumbers(_this.getAge(), 18);
    }
}
