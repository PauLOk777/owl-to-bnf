package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlElement;

public class SubClassOf {

    private OwlClass owlClass;
    private ObjectAllValuesFrom objectAllValuesFrom;

    public SubClassOf() {

    }

    public SubClassOf(OwlClass owlClass, ObjectAllValuesFrom objectProperty) {
        this.owlClass = owlClass;
        this.objectAllValuesFrom = objectProperty;
    }

    @XmlElement(name = "Class")
    public OwlClass getOwlClass() {
        return owlClass;
    }

    public void setOwlClass(OwlClass owlClass) {
        this.owlClass = owlClass;
    }

    @XmlElement(name = "ObjectProperty")
    public ObjectAllValuesFrom getObjectAllValuesFrom() {
        return objectAllValuesFrom;
    }

    public void setObjectAllValuesFrom(ObjectAllValuesFrom objectAllValuesFrom) {
        this.objectAllValuesFrom = objectAllValuesFrom;
    }

    @Override
    public String toString() {
        return "SubClassOf{" +
                "owlClass=" + owlClass +
                ", objectAllValuesFrom=" + objectAllValuesFrom +
                '}';
    }
}