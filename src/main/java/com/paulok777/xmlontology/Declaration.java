package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlElement;

public class Declaration {

    private OwlClass owlClass;
    private ObjectProperty objectProperty;

    public Declaration() {

    }

    public Declaration(OwlClass owlClass) {
        this.owlClass = owlClass;
    }

    public Declaration(ObjectProperty objectProperty) {
        this.objectProperty = objectProperty;
    }

    @XmlElement(name = "Class")
    public OwlClass getOwlClass() {
        return owlClass;
    }

    public void setOwlClass(OwlClass owlClass) {
        this.owlClass = owlClass;
    }

    @XmlElement(name = "ObjectProperty")
    public ObjectProperty getObjectProperty() {
        return objectProperty;
    }

    public void setObjectProperty(ObjectProperty objectProperty) {
        this.objectProperty = objectProperty;
    }

    @Override
    public String toString() {
        return "Declaration{" +
                "owlClass=" + owlClass +
                ", objectProperty=" + objectProperty +
                '}';
    }
}
