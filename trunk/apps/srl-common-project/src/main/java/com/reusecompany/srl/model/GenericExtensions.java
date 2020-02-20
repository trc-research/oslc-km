package com.reusecompany.srl.model;

import java.util.HashMap;

public final class GenericExtensions {

	// CONSTANTS
	
	//por defecto el concept order 1 es elemento de origen
    final static int defaultFromConceptOrder = 1;
    //por defecto el concept order 2 es elemento de destino (basado en KM)
    final static int defaultToConceptOrder = 2;
    //por defecto el concept order 0 es nombre de la relación (basado en KM)
    final static int MandatoryNameConceptOrder = 0;
	
    
	// Constructors
	
    
    // Participant Management
    
    public static Artifact GetFromParticipant(Relationship relationship) {
    	Artifact result = null;
    	if (relationship != null) {
    		HashMap<Integer, ArtifactOccurrence> participants = relationship.getParticipants();
    		
    		if (participants != null && participants.size() > 0) {
    			ArtifactOccurrence ocurrence = null;
    			ocurrence = participants.getOrDefault(defaultFromConceptOrder, null);
    			
    			if(ocurrence == null) {    			
    				Integer lowerKey = -1;
    				for (Integer key : participants.keySet()) {
						if ( key > 0 && (lowerKey <= 0 || key < lowerKey)) {
							lowerKey = key;
						} 
					}
    				if (lowerKey > 0) {
    					ocurrence = participants.getOrDefault(lowerKey, null);
    				}
    			}
    			
    			if(ocurrence != null) {
    				result = ocurrence.getParticipant();
    			}
    		}
    	}
    	return result;
    }
    
    public static void SetFromParticipant(Relationship relationship, Artifact fromArtifact) {
    	if (relationship != null) {
    		HashMap<Integer, ArtifactOccurrence> participants = null;

    		participants = relationship.getParticipants();
    		
    		if (participants == null) {
    			participants = new HashMap<Integer, ArtifactOccurrence>();
    			relationship.setParticipants(participants);
    		}
    		
    		ArtifactOccurrence newOccurrenceToLoad;
            if (participants.containsKey(defaultFromConceptOrder)) {
                newOccurrenceToLoad = participants.get(defaultFromConceptOrder);
                if (newOccurrenceToLoad != null) {
                    newOccurrenceToLoad = DefaultArtifactOccurrenceConfiguration(fromArtifact, defaultFromConceptOrder);
                }
                participants.replace(defaultFromConceptOrder, newOccurrenceToLoad);
            } else {
                newOccurrenceToLoad = DefaultArtifactOccurrenceConfiguration(fromArtifact, defaultFromConceptOrder);
                participants.put(defaultFromConceptOrder,newOccurrenceToLoad);
            }
    		
    	}
    }
    
    public static Artifact GetToParticipant(Relationship relationship) {
    	Artifact result = null;
    	if (relationship != null) {
    		HashMap<Integer, ArtifactOccurrence> participants = relationship.getParticipants();
    		
    		if (participants != null && participants.size() > 0) {
    			ArtifactOccurrence ocurrence = null;
    			ocurrence = participants.getOrDefault(defaultFromConceptOrder, null);
    			
    			if(ocurrence == null) {    			
    				Integer lowerKey = -1;
    				for (Integer key : participants.keySet()) {
						if ( key > 1 && (lowerKey <= 0 || key < lowerKey)) {
							lowerKey = key;
						} 
					}
    				if (lowerKey > 1) {
    					ocurrence = participants.getOrDefault(lowerKey, null);
    				}
    			}
    			
    			if(ocurrence != null) {
    				result = ocurrence.getParticipant();
    			}
    		}
    	}
    	return result;
    }
    
    public static void SetToParticipant(Relationship relationship, Artifact toArtifact) {
    	if (relationship != null) {
    		HashMap<Integer, ArtifactOccurrence> participants = null;
    		participants = relationship.getParticipants();
    		
    		if (participants == null) {
    			participants = new HashMap<Integer, ArtifactOccurrence>();
    			relationship.setParticipants(participants);
    		}
    		
    		ArtifactOccurrence newOccurrenceToLoad;
            if (participants.containsKey(defaultFromConceptOrder)) {
                newOccurrenceToLoad = participants.get(defaultFromConceptOrder);
                if (newOccurrenceToLoad != null) {
                    newOccurrenceToLoad = DefaultArtifactOccurrenceConfiguration(toArtifact, defaultFromConceptOrder);
                }
                participants.replace(defaultFromConceptOrder, newOccurrenceToLoad);
            } else {
                newOccurrenceToLoad = DefaultArtifactOccurrenceConfiguration(toArtifact, defaultFromConceptOrder);
                participants.put(defaultFromConceptOrder,newOccurrenceToLoad);
            }
    		
    	}
    }
    
    public static Artifact GetRelationshipNameFromParticipants(Relationship relationship) {
        Artifact result = null;
        HashMap<Integer, ArtifactOccurrence> participants = null;
		participants = relationship.getParticipants();
        if (relationship != null) {
            if (participants != null && participants.size() > 0) {
                if (participants.containsKey(MandatoryNameConceptOrder)) {
                    result = participants.get(MandatoryNameConceptOrder).getParticipant();
                }
            }
        }
        return result;
    }
    
    public static void SetRelationshipNameFromParticipants(Relationship relationship, Artifact name) {
    	HashMap<Integer, ArtifactOccurrence> participants = null;
    	
		participants = relationship.getParticipants();
		
        if (relationship != null ) {
            if (participants == null) {
                participants = new HashMap<Integer, ArtifactOccurrence>();
            }
            ArtifactOccurrence newOccurrenceToLoad;
            if (participants.containsKey(MandatoryNameConceptOrder)) {
                newOccurrenceToLoad = participants.get(MandatoryNameConceptOrder);
                if (newOccurrenceToLoad != null) {
                    newOccurrenceToLoad = DefaultArtifactOccurrenceConfiguration(name, MandatoryNameConceptOrder);
                }
                participants.replace(MandatoryNameConceptOrder, newOccurrenceToLoad);
            } else {
                newOccurrenceToLoad = DefaultArtifactOccurrenceConfiguration(name, MandatoryNameConceptOrder);
                participants.put(MandatoryNameConceptOrder, newOccurrenceToLoad);
            }
        }
    }

	private static ArtifactOccurrence DefaultArtifactOccurrenceConfiguration(Artifact fromArtifact, int conceptOrder) {
		 ArtifactOccurrence newOccurrenceToLoad = null;
         if (fromArtifact != null) {
             newOccurrenceToLoad = new ArtifactOccurrence();
             newOccurrenceToLoad.setParticipant(fromArtifact);
             newOccurrenceToLoad.setPosition(conceptOrder);
             newOccurrenceToLoad.setX(-1);
             newOccurrenceToLoad.setY(-1);
             newOccurrenceToLoad.setInterfaceOccurrence(SRLModelUtils.EMPTY_STRING);
             newOccurrenceToLoad.setAsReference(null);
         }
         return newOccurrenceToLoad;
	}
}
