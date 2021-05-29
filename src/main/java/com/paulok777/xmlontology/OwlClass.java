package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

import static java.util.Optional.ofNullable;

public class OwlClass {

    private String iri;
    private String abbreviatedIri;

    public String getAttribute() {
        return ofNullable(iri).orElse(abbreviatedIri);
    }

    public void setAttribute(String attribute) {
        if (containsIriAttribute()) {
            iri = attribute;
        } else {
            abbreviatedIri = attribute;
        }
    }

    public boolean containsIriAttribute() {
        return iri != null;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwlClass owlClass = (OwlClass) o;
        return Objects.equals(iri, owlClass.iri) && Objects.equals(abbreviatedIri, owlClass.abbreviatedIri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iri, abbreviatedIri);
    }

    @Override
    public String toString() {
        return "OwlClass{" +
                "iri='" + iri + '\'' +
                ", abbreviatedIri='" + abbreviatedIri + '\'' +
                '}';
    }
}
