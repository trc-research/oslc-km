package com.reusecompany.srl.model;

import java.net.URI;
import java.util.ArrayList;
import com.google.gson.annotations.*;

public class Artifact extends GeneralElement {
	
	// CONSTANTS
	
	/*		NO SE USA DE MOMENTO
	private final String ArtifactNamePropertyName = "Name";
	private final String ArtifactIdentifierPropertyName = "Identifier";
	private final String KeyPropertyName = "Key";
	private final String IdPropertyName = "Identifier";
	private final String ArtifactTypeNamePropertyName = "Type.Name";
	private final String RelationshipTypeNamePropertyName = "Type.Name";
	private final String RelationshipIdentifierPropertyName = "Identifier";
	
	private final String TermSyntacticTypeNamePropertyName = "SyntacticType.Name";
	private final String TermIdentifierPropertyName = "Identifier";
	private final String TermNamePropertyName = "Name";
	*/
	
	
	// Properties for Artifact
	
	public static ArtifactType DEFAULT_ARTIFACT_TYPE;
	@Expose
	@SerializedName("name")
	protected String name = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("type")
	protected ArtifactType type;
	@Expose
	@SerializedName("description")
    protected String description = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("content")
    protected String content = null;
	@Expose
	@SerializedName("snapShot")
    protected String snapShot = null;
	@Expose
	@SerializedName("physicalPath")
	protected String physicalPath = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("physicalName")
	protected String physicalName = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("sparqlEndpoint")
	protected URI sparqlEndpoint = null;		// JUAN ???
	@Expose
	@SerializedName("rdfContent")
	protected String rdfContent = null;
	@Expose
	@SerializedName("term")
	protected Term term;
	
	// Se ignora la Serialización por provocar bucles
	protected Artifact parentArtifact;
    
	@Expose
	@SerializedName("relationships")
	private ArrayList<Relationship> relationships = new ArrayList<Relationship>();
	@Expose
	@SerializedName("ownedArtifacts")
	private ArrayList<Artifact> ownedArtifacts = new ArrayList<Artifact>();
	@Expose
	@SerializedName("ownedTerms")
	private ArrayList<Term> ownedTerms = new ArrayList<Term>();
	@Expose
	@SerializedName("properties")
	private ArrayList<Property> properties = new ArrayList<Property>();
	@Expose
	@SerializedName("metaDatas")
	private ArrayList<MetaData> metaDatas = new ArrayList<MetaData>();
	
    
    // Constructors
    
	public Artifact() {
//		Init (false);
	}
	
	public Artifact(String artifactName) {
//		Init (false);
		this.name = artifactName;
	}
	
	public Artifact(String artifactName, ArtifactType artifactType) {
//		Init (false);
		this.name = artifactName;
		this.type = artifactType;
	}
	
	
	// Getters & Setters
	
	public ArtifactType getType() {
		return type;
	}
	public void setType(ArtifactType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSnapShot() {
		return snapShot;
	}
	public void setSnapShot(String snapShot) {
		this.snapShot = snapShot;
	}

	public String getPhysicalPath() {
		return physicalPath;
	}
	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}

	public String getPhysicalName() {
		return physicalName;
	}
	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}

	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	
	public String getRdfContent() {
		return rdfContent;
	}
	public void setRdfContent(String rdfContent) {
		this.rdfContent = rdfContent;
	}
	
	public Artifact getParentArtifact() {
		return parentArtifact;
	}
	public void setParentArtifact(Artifact parentArtifact) {
		this.parentArtifact = parentArtifact;
	}
	
	public URI getSparqlEndpoint() {
		return sparqlEndpoint;
	}
	public void setSparqlEndpoint(URI sparqlEndpoint) {
		this.sparqlEndpoint = sparqlEndpoint;
	}
	
	public ArrayList<Relationship> getRelationships() {
		return relationships;
	}

	public ArrayList<Artifact> getOwnedArtifacts() {
		return ownedArtifacts;
	}

	public ArrayList<Term> getOwnedTerms() {
		return ownedTerms;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public ArrayList<MetaData> getMetaDatas() {
		return metaDatas;
	}
	
	
	// Functions

	@Override
	public String toString() {
        String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s})\r\nId:{%s}\r\nName:{%s}\r\nType:{%s}";
        final String EmptyArtifactFormat = "({%s}):{%s}";
        String nullableIdentifier;
        nullableIdentifier = EmptyData;
        if (this.identifier != null 
        		&& !this.identifier.isEmpty()) {
            nullableIdentifier = this.identifier;
        }
        String nullableName;
        nullableName = EmptyData;
        if (this.name != null 
        		&& !this.name.isEmpty()) {
            nullableName = this.name;
        }
        String nullableTypeName;
        nullableTypeName = EmptyData;
        if (this.type != null 
        		&& this.type.name != null 
        		&& !this.type.name.isEmpty()) {
            nullableTypeName = this.type.name;
        }
        if ((nullableIdentifier == null || nullableIdentifier.isEmpty()) 
        		&& (nullableName == null  ||  nullableName.isEmpty()) 
        		&& (nullableTypeName == null || nullableTypeName.isEmpty())) {
            result = String.format(EmptyArtifactFormat, result, hashCode());
        } else {
            result = String.format(ToStringFormat, result, nullableIdentifier, nullableName, nullableTypeName);
        }
        return result;
    }
	
	
	// TODO Hay que comprobar si el elemento esta en la lista, es nullo o no es la misma instancia
	
	// Functions - ownedArtifacts
	
	public boolean AddOwnedArtifact(Artifact artifactToAdd) {
        boolean result = false;
        if (artifactToAdd != null 
        		&& this.ownedArtifacts != null) {
        	for (int i = 0; i < this.ownedArtifacts.size(); i++) {			//debemos comprobar que el Artefcato no este ya en la lista
        		if (this.ownedArtifacts.get(i).equals(artifactToAdd)) {
        			return result;
        		}
			}
            result = this.ownedArtifacts.add(artifactToAdd);
        }
        return result;
    }
	
	public boolean AddOwnedArtifacts(ArrayList<Artifact> artifactsToAdd) {
		ArrayList<Artifact> artifactsToAddFinal = new ArrayList<Artifact>();
        boolean result = false;
        if (artifactsToAdd != null 
        		&& artifactsToAdd.size() > 0 
        		&& this.ownedArtifacts != null) {
        	for (int i = 0; i < artifactsToAdd.size(); i++) {
        		if (artifactsToAdd.get(i) != null) {
        			boolean repeat = false;
        			for (int j = 0; j < this.ownedArtifacts.size(); j++) {						// TODO: USAR CONTAINS DE ARRAYLIST!!!
    					if (artifactsToAdd.get(i).equals(this.ownedArtifacts.get(j))) {
    						repeat = true;
    						break;
    					}
    				}
        			if (!repeat) {
						artifactsToAddFinal.add(artifactsToAdd.get(i));
					}
        		}
			}
        	result = this.ownedArtifacts.addAll(artifactsToAddFinal);
        }
        return result;
    }
	
	public boolean RemoveOwnedArtifact(Artifact elementToRemove) {
        boolean result = false;
        if (elementToRemove != null 
        		&& this.ownedArtifacts != null) {
            result = this.ownedArtifacts.remove(elementToRemove);
        }
        return result;
    }
	
	public boolean RemoveOwnedArtifactAtPosition(int index) {
        boolean result = false;
        if (index >= 0 
        		&& this.ownedArtifacts != null) {
            int elements = this.ownedArtifacts.size();
            this.ownedArtifacts.remove(index);
            result = elements > this.ownedArtifacts.size();
        }
        return result;
    }
	
	public Artifact GetFirstOwnedArtifactByName(String nameToSearch) {
        Artifact artifactRetrieved = null;
        if (nameToSearch != null 
        		&& !nameToSearch.isEmpty() 
        		&& this.ownedArtifacts != null 
        		&& this.ownedArtifacts.size() != 0) {
        	for (int i = 0; i < this.ownedArtifacts.size(); i++) {
        		if (this.ownedArtifacts.get(i) != null 
        				&& this.ownedArtifacts.get(i).name.equals(nameToSearch)) {
					artifactRetrieved = this.ownedArtifacts.get(i);
					break;
				}
			}
        }
        return artifactRetrieved;                    
    }
	
	public ArrayList<Artifact> GetOwnedArtifactsByName(String nameToSearch) {
		ArrayList<Artifact> result = new ArrayList<Artifact>();
		if (nameToSearch != null 
				&& !nameToSearch.isEmpty() 
				&& this.ownedArtifacts != null 
				&& this.ownedArtifacts.size() != 0) {
        	for (int i = 0; i < this.ownedArtifacts.size(); i++) {
        		if (this.ownedArtifacts.get(i) != null 
        				&& this.ownedArtifacts.get(i).name.equals(nameToSearch)) {
					result.add(this.ownedArtifacts.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
        
    }
	
	public Artifact GetOwnedArtifactById(String idToSearch) {
        Artifact artifactRetrieved = null;
        if (idToSearch != null 
        		&& !idToSearch.isEmpty() 
        		&& this.ownedArtifacts != null 
        		&& this.ownedArtifacts.size() != 0) {
        	for (int i = 0; i < this.ownedArtifacts.size(); i++) {
        		if (this.ownedArtifacts.get(i) != null 
        				&& this.ownedArtifacts.get(i).identifier.equals(idToSearch)) {
					artifactRetrieved = this.ownedArtifacts.get(i);
					break;
				}
			}
        }
        return artifactRetrieved;
    }
	
	public ArrayList<Artifact> GetOwnedArtifactByArtifactType(String typeToSearch) {
		ArrayList<Artifact> result = new ArrayList<Artifact>();
		if (typeToSearch != null 
				&& !typeToSearch.isEmpty() 
				&& this.ownedArtifacts != null 
				&& this.ownedArtifacts.size() != 0) {
        	for (int i = 0; i < this.ownedArtifacts.size(); i++) {
        		if (this.ownedArtifacts.get(i) != null 
        				&& this.ownedArtifacts.get(i).type.name.equals(typeToSearch)) {
					result.add(this.ownedArtifacts.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
    }
	
	public ArrayList<Artifact> GetOwnedArtifactByArtifactTypeAndName(String artifactType, String artifactName) {
		ArrayList<Artifact> result = new ArrayList<Artifact>();
		if (artifactType != null 
				&& !artifactType.isEmpty() 
				&& artifactName != null 
				&& !artifactName.isEmpty() 
				&& this.ownedArtifacts != null 
				&& this.ownedArtifacts.size() != 0) {
        	for (int i = 0; i < this.ownedArtifacts.size(); i++) {
        		if (this.ownedArtifacts.get(i) != null 
        				&& this.ownedArtifacts.get(i).name.equals(artifactType) 
        				&& this.ownedArtifacts.get(i).name.equals(artifactName)) {
					result.add(this.ownedArtifacts.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
    }
	
	public void ClearOwnedArtifacts() {
        if (this.ownedArtifacts != null) {
            this.ownedArtifacts.clear();
        }
    }
	
	
	// Functions - properties
	
	public boolean AddProperty(Property propertyToAdd) {
        boolean result = false;
        if (propertyToAdd != null 
        		&& this.properties != null) {
            result = this.properties.add(propertyToAdd);
        }
        return result;
    }
	
	public boolean AddProperties(ArrayList<Property> propertiesToAdd) {
        boolean result = false;
        if (propertiesToAdd != null 
        		&& propertiesToAdd.size() > 0 
        		&& this.properties != null) {
            int numProperties = this.properties.size();
            this.properties.addAll(propertiesToAdd);
            result = numProperties < this.properties.size();
        }
        return result;
    }
	
    public boolean RemoveProperty(Property propertyToDelete) {
        boolean result = false;
        if (propertyToDelete != null 
        		&& this.properties != null) {
            result = this.properties.remove(propertyToDelete);
        }
        return result;
    }
    
    public boolean RemovePropertyAt(int index) {
        boolean result = false;
        if (index > -1 
        		&& this.properties != null) {
            int numProperties = this.properties.size();
            this.properties.remove(index);
            result = this.properties.size() < numProperties;
        }
        return result;
    }
    
    public Property GetPropertyByKey(String keyToSearch) {
        Property propertyRetrieved = null;
        if (keyToSearch != null 
        		&& !keyToSearch.isEmpty() 
        		&& this.properties != null 
        		&& this.properties.size() != 0) {
        	for (int i = 0; i < this.properties.size(); i++) {
        		if (this.properties.get(i) != null 
        				&& this.properties.get(i).key.equals(keyToSearch)) {
        			propertyRetrieved = this.properties.get(i);
					break;
				}
			}
        }
        return propertyRetrieved;
    }
    
    public void ClearProperties() {
        if (this.properties != null) {
            this.properties.clear();
        }
    }
    
    
    // Functions - metaDatas
	
    public boolean AddMetaData(MetaData metadataToAdd) {
        boolean result = false;
        if (metadataToAdd != null 
        		&& metaDatas != null) {
            result = metaDatas.add(metadataToAdd);
        }
        return result;
    }
    
    public boolean AddMetadatas(ArrayList<MetaData> metadatasToAdd) {
        boolean result = false;
        if (metadatasToAdd != null 
        		&& metadatasToAdd.size() > 0 
        		&& this.metaDatas != null) {
            int numMetadatas = metaDatas.size();
            metaDatas.addAll(metadatasToAdd);
            result = numMetadatas < metaDatas.size();
        }
        return result;
    }
    
    public boolean RemoveMetadata(MetaData metadataToDelete) {
        boolean result = false;
        if (metadataToDelete != null 
        		&& metaDatas != null) {
            result = metaDatas.remove(metadataToDelete);
        }
        return result;
    }
    
    public boolean RemoveMetadataAt(int index) {
        boolean result = false;
        if (index > -1 
        		&& metaDatas != null) {
            int numMetadatas = metaDatas.size();
            metaDatas.remove(index);
            result = metaDatas.size() < numMetadatas;
        }
        return result;
    }
    
    public MetaData GetMetadataByKey(String keyToSearch) {
        MetaData metadataRetrieved = null;
        if (keyToSearch != null 
        		&& !keyToSearch.isEmpty() 
        		&& this.metaDatas != null 
        		&& this.metaDatas.size() != 0) {
        	for (int i = 0; i < this.metaDatas.size(); i++) {
        		if (this.metaDatas.get(i) != null 
        				&& this.metaDatas.get(i).key.equals(keyToSearch)) {
        			metadataRetrieved = this.metaDatas.get(i);
					break;
				}
			}
        }
        return metadataRetrieved;
    }
    
    public void ClearMetadatas() {
        if (this.metaDatas != null) {
            this.metaDatas.clear();
        }
    }
    
    
    // Functions - relationships
    
    public boolean AddRelationship(Relationship relationshipToAdd) {
        boolean result = false;
        if (relationshipToAdd != null 
        		&& this.relationships != null) {
            result = this.relationships.add(relationshipToAdd);
        }
        return result;
    }
    
    public boolean AddRelationships(ArrayList<Relationship> relationshipsToAdd) {
        boolean result = false;
        if (relationshipsToAdd != null 
        		&& relationshipsToAdd.size() > 0 
        		&& this.relationships != null) {
            int numrelationships = relationships.size();
            this.relationships.addAll(relationshipsToAdd);
            result = numrelationships < relationships.size();
        }
        return result;
    }
    
    public boolean RemoveRelationship(Relationship relationshipToDelete) {
        boolean result = false;
        if (relationshipToDelete != null 
        		&& relationships != null) {
            result = relationships.remove(relationshipToDelete);
        }
        return result;
    }
    
    public boolean RemoveRelationshipAt(int index) {
        boolean result = false;
        if (index > -1 
        		&& relationships != null) {
            int numRelationships = relationships.size();
            relationships.remove(index);
            result = relationships.size() < numRelationships;
        }
        return result;
    }
    
    public ArrayList<Relationship> GetRelationshipByTypeName(String relationshipTypeToSearch) {
        ArrayList<Relationship> result = new ArrayList<Relationship>();
        if (relationshipTypeToSearch != null 
        		&& !relationshipTypeToSearch.isEmpty() 
        		&& this.relationships != null) {
        	for (int i = 0; i < this.relationships.size(); i++) {
        		if (this.relationships.get(i) != null 
        				&& this.relationships.get(i).getType().name.equals(relationshipTypeToSearch)) {
					result.add(this.relationships.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
    }
    
    public ArrayList<Relationship> GetRelationshipById(String relationshipIdToSearch) {
    	ArrayList<Relationship> result = new ArrayList<Relationship>();
    	if (relationshipIdToSearch != null 
    			&& !relationshipIdToSearch.isEmpty() 
    			&& this.relationships != null) {
        	for (int i = 0; i < this.relationships.size(); i++) {
        		if (this.relationships.get(i) != null 
        				&& this.relationships.get(i).getType().identifier.equals(relationshipIdToSearch)) {
					result.add(this.relationships.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
    }
    
    public void ClearRelationships() {
        if (this.relationships != null) {
            this.relationships.clear();
        }
    }
    
    
    // Functions - ownedTerms
    
    public boolean AddTerm(Term termToAdd) {
        boolean result = false;
        if (termToAdd != null 
        		&& ownedTerms != null) {
            result = ownedTerms.add(termToAdd);
        }
        return result;
    }
    
    public boolean AddTerms(ArrayList<Term> termsToAdd) {
        boolean result = false;
        if (termsToAdd != null 
        		&& termsToAdd.size() > 0 
        		&& this.ownedTerms != null) {
            int numTerms = this.ownedTerms.size();
            this.ownedTerms.addAll(termsToAdd);
            result = numTerms < ownedTerms.size();
        }
        return result;
    }
    
    public boolean RemoveTerm(Term termToDelete) {
        boolean result = false;
        if (termToDelete != null 
        		&& ownedTerms != null) {
            result = ownedTerms.remove(termToDelete);
        }
        return result;
    }
    
    public boolean RemoveTermAt(int index) {
        boolean result = false;
        if (index > -1 
        		&& ownedTerms != null) {
            int numTerms = ownedTerms.size();
            ownedTerms.remove(index);
            result = ownedTerms.size() < numTerms;
        }
        return result;
    }
    
    public ArrayList<Term> GetTermByName(String termNameToSearch) {
    	ArrayList<Term> result = new ArrayList<Term>();
    	if (termNameToSearch != null 
    			&& !termNameToSearch.isEmpty() 
    			&& this.ownedTerms != null) {
        	for (int i = 0; i < this.ownedTerms.size(); i++) {
        		if (this.ownedTerms.get(i) != null 
        				&& this.ownedTerms.get(i).name.equals(termNameToSearch)) {
					result.add(this.ownedTerms.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
    }
    
    public ArrayList<Term> GetTermByNameAndTermTag(String termSyntaticType, String termName) {
        ArrayList<Term> result = new ArrayList<Term>();
        if (termSyntaticType != null 
        		&& !termSyntaticType.isEmpty() 
        		&& termName != null 
        		&& !termName.isEmpty() 
        		&& this.ownedTerms != null 
        		&& this.ownedTerms.size() != 0) {
        	for (int i = 0; i < this.ownedTerms.size(); i++) {
        		if (this.ownedTerms.get(i) != null 
        				&& this.ownedTerms.get(i).name.equals(termSyntaticType) 
        				&& this.ownedTerms.get(i).name.equals(termName)) {
					result.add(this.ownedTerms.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
    }
    
    public ArrayList<Term> GetTermById(String termIdToSearch) {
    	ArrayList<Term> result = new ArrayList<Term>();
    	if (termIdToSearch != null 
    			&& !termIdToSearch.isEmpty() 
    			&& this.ownedTerms != null) {
        	for (int i = 0; i < this.ownedTerms.size(); i++) {
        		if (this.ownedTerms.get(i) != null 
        				&& this.ownedTerms.get(i).identifier.equals(termIdToSearch)) {
					result.add(this.ownedTerms.get(i));
				}
			}
        }
		if (result.size() == 0) {
			return null;
		} else {
			return result;
		}
    }
    
    public void ClearOwnedTerms() {
        if (ownedTerms != null) {
            ownedTerms.clear();
        }
    }
	
    
}
