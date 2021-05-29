package com.paulok777;

import com.paulok777.xmlontology.Ontology;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class OntologyParser {

    private JAXBContext jaxbContext;

    public OntologyParser() {
        try {
            jaxbContext = JAXBContext.newInstance(Ontology.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Ontology xmlParseOntology(File file) {
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Ontology) unmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

}
