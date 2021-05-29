package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlElement;

public class ObjectAllValuesFrom {

    private OwlClass owlClass;
    private ObjectProperty objectProperty;

    public ObjectAllValuesFrom() {

    }

    public ObjectAllValuesFrom(OwlClass owlClass, ObjectProperty objectProperty) {
        this.owlClass = owlClass;
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
        return "ObjectAllValuesFrom{" +
                "owlClass=" + owlClass +
                ", objectProperty=" + objectProperty +
                '}';
    }
}
