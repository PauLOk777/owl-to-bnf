package com.paulok777;

import com.paulok777.xmlontology.Ontology;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Ontology ontology = new OntologyParser().xmlParseOntology(new File("examples/owl/ontology.xml"));
        System.out.println(ontology);
    }
}
