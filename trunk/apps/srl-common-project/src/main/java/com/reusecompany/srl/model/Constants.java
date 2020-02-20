package com.reusecompany.srl.model;

public class Constants {
	
	// Constants
	
	public final String SERVICE_PROVIDER_PATH = "trc";
    public final String XML = "http://www.w3.org/XML/1998/namespace/";
    public final String INFO_ACTION = "info";
    public final String PATH_VOCABULARY = "concepts";
    public final String PATH_VOCABULARY_SHAPE = "getShape=true";
    
    
    // Patterns service constants
    
    public final String PATTERN_NAMESPACE = "http://purl.org/trc/oslc/patterns/";
    public final String PATTERN_NAMESPACE_PREFIX = "patterns";
    public final String PATTERN_CONCEPT = "Pattern";
    public final String PATTERN_GROUP = "PatternGroup";
    public final String PATTERNS_DOMAIN = PATTERN_NAMESPACE;

    public final String PATTERN_GROUPS_ACTION = "groups";
    public final String PATTERN_GROUP_ACTION = "group";
    public final String PATTERNS_ACTION = "patterns";
    public final String PATTERN_ACTION = "pattern";
    public static String PATTERN_GROUP_SHAPE = "getShape=true";
    public final String PATTERN_GROUP_PATH = "patterns";
    
    
    // Vocabulary service constants
    
    public final String VOCABULARY_DOMAIN = "http://purl.org/trc/oslc/vm/";
    public final String VOCABULARY_NAMESPACE = "http://purl.org/trc/oslc/vm/";
    public final String VOCABULARY_NAMESPACE_PREFIX = "oslc-vm";
    public final String VOCABULARY_CONCEPT = "Concept";
    public final String CONCEPT_ACTION = "concept";
    
    
    //The URI of the type for concepts
    
    public final String TYPE_VOCABULARY_CONCEPT = VOCABULARY_NAMESPACE + VOCABULARY_CONCEPT;
    
    
    // Function utils
    /* TODO de donde viene esta clase en C#?
    public static PrefixDefinition[] PREFIX_DEFINITIONS = {
            //new PrefixDefinition(OslcConstants.DCTERMS_NAMESPACE_PREFIX, new Uri(OslcConstants.DCTERMS_NAMESPACE)),
            //new PrefixDefinition(OslcConstants.OSLC_CORE_NAMESPACE_PREFIX, new Uri(OslcConstants.OSLC_CORE_NAMESPACE)),
            //new PrefixDefinition(OslcConstants.RDF_NAMESPACE_PREFIX, new Uri(OslcConstants.RDF_NAMESPACE)),
            //new PrefixDefinition(OslcConstants.RDFS_NAMESPACE_PREFIX, new Uri(OslcConstants.RDFS_NAMESPACE)),
            //new PrefixDefinition(RDF_DATA_CUBE.DATA_CUBE_PREFIX_NAMESPACE, new Uri(RDF_DATA_CUBE.DATA_CUBE_NAMESPACE)),
            //new PrefixDefinition(SKOS.PREFIX_CORE_NAMESPACE, new Uri(SKOS.CORE_NAMESPACE)),
            //new PrefixDefinition(KPI_CONSTANTS.KPI_NAMESPACE_PREFIX, new Uri(KPI_CONSTANTS.KPI_NAMESPACE)),
            new PrefixDefinition(REQUIREMENT_DOMAIN_PREFIX, new Uri(REQUIREMENT_DOMAIN)),
            new PrefixDefinition(PATTERN_NAMESPACE_PREFIX, new Uri(PATTERN_NAMESPACE)),
            new PrefixDefinition("foaf", new Uri(FOAF_NAMESPACE)),
            //new PrefixDefinition("rshp", new Uri(RSHPShapeConstants.RSHP_NAMESPACE)),
            new PrefixDefinition("xml", new Uri(XML)),
            };      
	*/
    
    // Requirements service constants
    public final String REQUIREMENT_DOMAIN_PREFIX = "rm";
    public final String REQUIREMENT_DOMAIN = "http://localhost/ns/rm/";
    public final String REQUIREMENT_NAMESPACE = "http://localhost/ns/rm/";
    public final String REQUIREMENT = "Requirement";
    public final String TYPE_REQUIREMENT = REQUIREMENT_NAMESPACE + REQUIREMENT;
    public final String PATH_REQUIREMENT = "requirements";
    public final String REQUIREMENT_ACTION = "requirement";
    
    // Dublin Core properties
    /* TODO de donde viene esta clase en C#?
    public final String DCTERMS = OslcConstants.DCTERMS_NAMESPACE;
    public final String DCTERMS_TITLE = DCTERMS + "title";
    public final String DCTERMS_DESCRIPTION = DCTERMS + "description";
    public final String DCTERMS_IDENTIFIER = DCTERMS + "identifier";
    public final String DCTERMS_SUBJECT = DCTERMS + "subject";
    public final String DCTERMS_CREATOR = DCTERMS + "creator";
    public final String DCTERMS_CONTRIBUTOR = DCTERMS + "contributor";
    public final String DCTERMS_CREATED = DCTERMS + "created";
    public final String DCTERMS_MODIFIED = DCTERMS + "modified";
    */
    
    // FOAF Properties
    public final String FOAF_NAMESPACE = "http://xmlns.com/foaf/0.1/";
    public final String FOAF_PERSON = FOAF_NAMESPACE + "Person";
    public final String FOAF_NAME_PROPERTY = FOAF_NAMESPACE + "name";
    public final String FOAF_MBOX_PROPERTY = FOAF_NAMESPACE + "mbox";
    
    // OSLC Core properties
    /* TODO de donde viene esta clase en C#?
    public final String OSLC_CORE = OslcConstants.OSLC_CORE_NAMESPACE;
    public final String OSLC_CORE_SHORT_TITLE = OSLC_CORE + "shortTitle";
    */
    
    // OSLC KM Properties
    public final String BASEURI = "http://wwww.knowledgeCentricSolutions.com/api/km/oslc/sas/";
    public final String ARTIFACT = BASEURI + "artifact/";
    public final String ARTIFACTTYPE = BASEURI + "artifactType/";
    public final String METAPROPERTY = BASEURI + "metaproperty/";
    public final String RSHP = BASEURI + "rshp/";
    public final String SEMANTICCLUSTER = BASEURI + "semanticCluster/";
    public final String TERMTAG = BASEURI + "termTag/";
    public final String ARTIFACT_ACTION = "artifact";
    public static String ARTIFACT_SHAPE = "getShape=true";
    public final String ARTIFACT_NAMESPACE = BASEURI + "artifact/";
}
