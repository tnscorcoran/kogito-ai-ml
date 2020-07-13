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
import org.drools.model.Model;

public class RulesE405F32A817FD57B535435BB6FB960B3 implements Model {

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateUtils.getDateFormatMask(),
                                                                                            Locale.ENGLISH);

    @Override
    public String getName() {
        return "org.acme.kogito";
    }

    public static Date string_2_date(String s) {
        return GregorianCalendar.from(LocalDate.parse(s,
                                                      DATE_TIME_FORMATTER).atStartOfDay(ZoneId.systemDefault()))
                                .getTime();
    }

    @Override
    public List<org.drools.model.EntryPoint> getEntryPoints() {
        return Collections.emptyList();
    }

    @Override
    public List<org.drools.model.Global> getGlobals() {
        return globals;
    }

    @Override
    public List<org.drools.model.TypeMetaData> getTypeMetaDatas() {
        return typeMetaDatas;
    }

    List<org.drools.model.Global> globals = Collections.emptyList();

    List<org.drools.model.TypeMetaData> typeMetaDatas = Collections.emptyList();

    /**
     * With the following expression ID:
     * org.drools.modelcompiler.builder.generator.DRLIdGenerator@75954eaf
     */
    @Override
    public List<org.drools.model.Rule> getRules() {
        return rules;
    }

    public List<Rule> getRulesList() {
        return Arrays.asList(RulesE405F32A817FD57B535435BB6FB960B3RuleMethods0.rule_Is_32adult());
    }

    List<org.drools.model.Rule> rules = getRulesList();

    @Override
    public List<org.drools.model.Query> getQueries() {
        return java.util.Collections.emptyList();
    }
}
