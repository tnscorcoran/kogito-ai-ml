package org.acme.kogito;

import java.util.*;
import org.drools.model.*;
import org.drools.modelcompiler.dsl.pattern.D;
import org.drools.model.Index.ConstraintType;
import java.time.*;
import java.time.format.*;
import java.text.*;
import org.drools.core.util.*;
import org.acme.kogito.model.Person;
import static org.acme.kogito.RulesE405F32A817FD57B535435BB6FB960B3.*;

public class RulesE405F32A817FD57B535435BB6FB960B3RuleMethods0 {

    /**
     * Rule name: Is adult
     */
    public static org.drools.model.Rule rule_Is_32adult() {
        final org.drools.model.Variable<org.acme.kogito.model.Person> var_$person = D.declarationOf(org.acme.kogito.model.Person.class,
                                                                                                    DomainClassesMetadataE405F32A817FD57B535435BB6FB960B3.org_acme_kogito_model_Person_Metadata_INSTANCE,
                                                                                                    "$person");
        final org.drools.model.BitMask mask_$person = org.drools.model.BitMask.getPatternMask(DomainClassesMetadataE405F32A817FD57B535435BB6FB960B3.org_acme_kogito_model_Person_Metadata_INSTANCE,
                                                                                              "adult");
        org.drools.model.Rule rule = D.rule("org.acme.kogito",
                                            "Is adult").attribute(Rule.Attribute.RULEFLOW_GROUP,
                                                                  "person")
                                                       .build(D.pattern(var_$person).expr("A1FDC7F1FAFC28000D8AA997953F4C44",
                                                                                          org.acme.kogito.PC7.LambdaPredicateC79D704B4C9D96E4EF0723F125C38849.INSTANCE,
                                                                                          D.alphaIndexedBy(java.lang.Integer.class,
                                                                                                           org.drools.model.Index.ConstraintType.GREATER_THAN,
                                                                                                           DomainClassesMetadataE405F32A817FD57B535435BB6FB960B3.org_acme_kogito_model_Person_Metadata_INSTANCE.getPropertyIndex("age"),
                                                                                                           org.acme.kogito.PDE.LambdaExtractorDE5CA080B4B47F2586D3CAC99BF6315C.INSTANCE,
                                                                                                           18),
                                                                                          D.reactOn("age")),
                                                              D.on(var_$person).execute(org.acme.kogito.P12.LambdaConsequence12E14A003432FE818E1802F0168A8586.INSTANCE));
        return rule;
    }
}
