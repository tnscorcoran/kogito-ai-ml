package org.acme.kogito;
public class DomainClassesMetadataE405F32A817FD57B535435BB6FB960B3 {

    public static final org.drools.model.DomainClassMetadata org_acme_kogito_model_Person_Metadata_INSTANCE = new org_acme_kogito_model_Person_Metadata();
    private static class org_acme_kogito_model_Person_Metadata implements org.drools.model.DomainClassMetadata {

        @Override
        public Class<?> getDomainClass() {
            return org.acme.kogito.model.Person.class;
        }

        @Override
        public int getPropertiesSize() {
            return 4;
        }

        @Override
        public int getPropertyIndex( String name ) {
            switch(name) {
                case "adult": return 0;
                case "age": return 1;
                case "employed": return 2;
                case "name": return 3;
             }
             throw new RuntimeException("Unknown property '" + name + "' for class class class org.acme.kogito.model.Person");
        }
    }

}