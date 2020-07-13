package org.acme.kogito.PDE;

import static org.acme.kogito.RulesE405F32A817FD57B535435BB6FB960B3.*;
import org.acme.kogito.*;
import org.acme.kogito.model.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaExtractorDE5CA080B4B47F2586D3CAC99BF6315C implements org.drools.model.functions.Function1<org.acme.kogito.model.Person, java.lang.Integer> {

    INSTANCE;

    public static final String EXPRESSION_HASH = "A62C37F0EAA372A17CFC052E87B6014B";

    @Override()
    public java.lang.Integer apply(org.acme.kogito.model.Person _this) {
        return _this.getAge();
    }
}
