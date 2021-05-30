package com.paulok777;

import com.paulok777.xmlontology.Ontology;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect number of arguments");
            return;
        }

        try {
            Ontology ontology = new OntologyParser().xmlParseOntology(new File(args[0]));
            byte[] buffer = String.join("\n", new OwlToGrammarConverter().convert(ontology)).getBytes();
            FileWriter.writeToOutputFile(buffer, new File(args[1]));
        } catch (JAXBException e) {
            System.err.println("Failed to parse XML file. Please recheck path, extension and content.");
        } catch (IOException e) {
            System.err.println("Failed to write content into file.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Successfully converted owl to grammar.");
    }
}
