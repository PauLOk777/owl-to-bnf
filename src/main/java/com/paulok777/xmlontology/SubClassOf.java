package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class SubClassOf {

    private OwlClass owlClass;
    private ObjectAllValuesFrom objectAllValuesFrom;

    public SubClassOf() {

    }

    public SubClassOf(OwlClass owlClass, ObjectAllValuesFrom objectAllValuesFrom) {
        this.owlClass = owlClass;
        this.objectAllValuesFrom = objectAllValuesFrom;
    }

    @XmlElement(name = "Class")
    public OwlClass getOwlClass() {
        return owlClass;
    }

    public void setOwlClass(OwlClass owlClass) {
        this.owlClass = owlClass;
    }

    @XmlElement(name = "ObjectAllValuesFrom")
    public ObjectAllValuesFrom getObjectAllValuesFrom() {
        return objectAllValuesFrom;
    }

    public void setObjectAllValuesFrom(ObjectAllValuesFrom objectAllValuesFrom) {
        this.objectAllValuesFrom = objectAllValuesFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubClassOf that = (SubClassOf) o;
        return Objects.equals(owlClass, that.owlClass) && Objects.equals(objectAllValuesFrom, that.objectAllValuesFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owlClass, objectAllValuesFrom);
    }

    @Override
    public String toString() {
        return "SubClassOf{" +
                "owlClass=" + owlClass +
                ", objectAllValuesFrom=" + objectAllValuesFrom +
                '}';
    }
}