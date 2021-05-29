package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlAttribute;

public class Prefix {

    private String name;
    private String iri;
    private String abbreviatedIri;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "Prefix{" +
                "name='" + name + '\'' +
                ", iri='" + iri + '\'' +
                ", abbreviatedIri='" + abbreviatedIri + '\'' +
                '}';
    }
}
