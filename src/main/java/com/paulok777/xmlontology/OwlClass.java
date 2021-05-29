package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlAttribute;

public class OwlClass {

    private String iri;
    private String abbreviatedIri;

    @XmlAttribute(name = "IRI")
    public String getIri() {
        return iri;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    @XmlAttribute(name = "abbreviatedIRI")
    public String getAbbreviatedIri() {
        return abbreviatedIri;
    }

    public void setAbbreviatedIri(String abbreviatedIri) {
        this.abbreviatedIri = abbreviatedIri;
    }

    @Override
    public String toString() {
        return "OwlClass{" +
                "iri='" + iri + '\'' +
                ", abbreviatedIri='" + abbreviatedIri + '\'' +
                '}';
    }
}
