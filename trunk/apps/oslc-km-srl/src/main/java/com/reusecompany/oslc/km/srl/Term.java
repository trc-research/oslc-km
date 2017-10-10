package com.reusecompany.oslc.km.srl;

import java.net.URI;

import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRepresentation;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.Representation;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import com.reusecompany.oslc.km.vocabs.SKOS;
import com.reusecompany.oslc.km.vocabs.SRL;
import com.reusecompany.oslc.km.vocabs.XML;

@OslcNamespace(SRL.NAMESPACE)
@OslcName(SRL.TERM)
@OslcResourceShape(title = "SRL Term Resource Shape",describes=SKOS.CONCEPT)
public class Term extends AbstractResource {


	String prefLabel;
	String lang;
	String identifier;
	TermTag tag;
	
	public Term() {
		// TODO Auto-generated constructor stub
	}

	public Term(String label, String language, TermTag tag) {
		this.prefLabel = label;
		this.lang = language;
		this.tag = tag;
	}

	public Term(String label) {
		this.prefLabel = label;
	}

	public Term(String label, String language) {
		this.prefLabel = label;
		this.lang = language;
	}


	@OslcDescription("A unique identifier for a resource. Assigned by the service provider when a resource is created. Not intended for end-user display.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(OslcConstants.DCTERMS_NAMESPACE + "identifier")
	@OslcReadOnly
	@OslcTitle("Identifier")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	@OslcDescription("A preferred label for each element of the vocabulary. The tuple (literal, lang) should be unique.")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition(SKOS.PREF_LABEL)
	@OslcReadOnly
	@OslcTitle("A preferred label")
	@OslcName("prefLabel")
	public String getPrefLabel() {
		return this.prefLabel;
	}
	
	public void setPrefLabel(String label) {
		this.prefLabel = label;
	}


	@OslcDescription("The language of the term.")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition(XML.NAMESPACE+"lang")
	@OslcReadOnly
	@OslcTitle("A language")
	@OslcName("lang")
	public String getLang() {
		return this.lang;
	}
	public void setLang(String language) {
		this.lang = language;
	}

	@OslcDescription("Describes the syntactical category of this term.")
	@OslcPropertyDefinition(SRL.NAMESPACE+"tag")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("TermTag")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public TermTag getTag() {
		return tag;
	}
	public void setTag(TermTag tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Term [prefLabel=" + prefLabel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prefLabel == null) ? 0 : prefLabel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (prefLabel == null) {
			if (other.prefLabel != null)
				return false;
		} else if (!prefLabel.equals(other.prefLabel))
			return false;
		return true;
	}





}
