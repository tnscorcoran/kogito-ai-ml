package org.acme.kogito.P12;

import static org.acme.kogito.RulesE405F32A817FD57B535435BB6FB960B3.*;
import org.acme.kogito.*;
import org.acme.kogito.model.Person;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaConsequence12E14A003432FE818E1802F0168A8586 implements org.drools.model.functions.Block2<org.drools.model.Drools, org.acme.kogito.model.Person> {

    INSTANCE;

    public static final String EXPRESSION_HASH = "D50889A745637599FC6E9B449E5B67ED";

    private final org.drools.model.BitMask mask_$person = org.drools.model.BitMask.getPatternMask(DomainClassesMetadataE405F32A817FD57B535435BB6FB960B3.org_acme_kogito_model_Person_Metadata_INSTANCE, "adult");

    @Override()
    public void execute(org.drools.model.Drools drools, org.acme.kogito.model.Person $person) throws java.lang.Exception {
        {
            {
                {
                    ($person).setAdult(true);
                    drools.update($person, mask_$person);
                }
            }
        }
    }
}
