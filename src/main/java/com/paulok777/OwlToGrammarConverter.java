package com.paulok777;

import com.paulok777.xmlontology.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OwlToGrammarConverter {

    private static final String THING_KEY = "thing";
    private static final String THING_VALUE = "Thing";
    private static final String ID = "ID";

    private static final String COLON = ":";
    private static final String SEMICOLON = ";";
    private static final String SPACE = " ";
    private static final String LEFT_BRACE = "{";
    private static final String RIGHT_BRACE = "}";
    private static final String LEFT_SQUARE_BRACKET = "[";
    private static final String RIGHT_SQUARE_BRACKET = "]";
    private static final String LEFT_ROUND_BRACKET = "(";
    private static final String RIGHT_ROUND_BRACKET = ")";
    private static final String SINGLE_QUOTE = "'";
    private static final String PIPE = "|";
    private static final String PLUS_SIGN = "+";
    private static final String UNDERSCORE = "_";
    private static final String COMMA = ",";
    private static final String ASTERISK = "*";
    private static final String QUESTION_MARK = "?";
    private static final String HASH = "#";
    private static final String END_DESCRIPTION = SINGLE_QUOTE + RIGHT_BRACE + SINGLE_QUOTE + SEMICOLON;

    public List<String> convert(Ontology ontology) {
        List<OwlClass> classes = new ArrayList<>();
        List<ObjectProperty> objectProperties = new ArrayList<>();
        List<SubClassOf> relations = new ArrayList<>();

        populateClassesAndProperties(ontology, classes, objectProperties);
        populateRelations(ontology, classes, objectProperties, relations);

        return getGrammar(classes, relations);
    }

    private void populateClassesAndProperties(Ontology ontology, List<OwlClass> classes,
                                              List<ObjectProperty> objectProperties) {
        ontology.getDeclarations().forEach(declaration -> {
            if (declaration.getOwlClass() == null && !objectProperties.contains(declaration.getObjectProperty())) {
                ObjectProperty property = declaration.getObjectProperty();
                property.setAttribute(getClearClassOrProperty(property.getAttribute(),
                        property.containsIriAttribute()).toLowerCase());
                objectProperties.add(property);
            } else if (!classes.contains(declaration.getOwlClass())) {
                OwlClass owlClass = declaration.getOwlClass();
                owlClass.setAttribute(getClearClassOrProperty(owlClass.getAttribute(),
                        owlClass.containsIriAttribute()).toLowerCase());
                classes.add(owlClass);
            } else {
                if (objectProperties.contains(declaration.getObjectProperty())) {
                    throw new RuntimeException("Duplicated property: " + declaration.getObjectProperty().getAttribute());
                } else {
                    throw new RuntimeException("Duplicated class: " + declaration.getOwlClass().getAttribute());
                }
            }
        });
    }

    private void populateRelations(Ontology ontology, List<OwlClass> classes,
                                   List<ObjectProperty> objectProperties, List<SubClassOf> relations) {
        ontology.getSubClassOfs().forEach(subClassOf -> {
            OwlClass parentClass = subClassOf.getOwlClass();
            parentClass.setAttribute(getClearClassOrProperty(parentClass.getAttribute(),
                    parentClass.containsIriAttribute()).toLowerCase());
            OwlClass childClass = subClassOf.getObjectAllValuesFrom().getOwlClass();
            childClass.setAttribute(getClearClassOrProperty(childClass.getAttribute(),
                    childClass.containsIriAttribute()).toLowerCase());
            ObjectProperty property = subClassOf.getObjectAllValuesFrom().getObjectProperty();
            property.setAttribute(getClearClassOrProperty(property.getAttribute(),
                    property.containsIriAttribute()).toLowerCase());

            if (!relationContainsDeclarations(classes, objectProperties, subClassOf)) {

                throw new RuntimeException("Not declared class or property in relation: " +
                        subClassOf.getOwlClass().getAttribute() + "_" +
                        subClassOf.getObjectAllValuesFrom().getObjectProperty().getAttribute() + "_" +
                        subClassOf.getObjectAllValuesFrom().getOwlClass().containsIriAttribute());
            }

            relations.add(subClassOf);
        });
    }

    private String getClearClassOrProperty(String attribute, boolean isIriAttribute) {
        if (isIriAttribute) {
            return attribute.split(HASH)[1];
        } else {
            return attribute.split(COLON)[1];
        }
    }

    private boolean relationContainsDeclarations(List<OwlClass> classes, List<ObjectProperty> objectProperties,
                                                 SubClassOf subClassOf) {
        ObjectAllValuesFrom objectAllValuesFrom = subClassOf.getObjectAllValuesFrom();
        return classes.contains(subClassOf.getOwlClass()) && classes.contains(objectAllValuesFrom.getOwlClass())
                && objectProperties.contains(objectAllValuesFrom.getObjectProperty());
    }

    private List<String> getGrammar(List<OwlClass> classes, List<SubClassOf> relations) {
        List<String> rows = new ArrayList<>();

        fillGrammarHeader(classes, rows);
        fillGrammarMainBody(classes, relations, rows);

        return rows;
    }

    private void fillGrammarHeader(List<OwlClass> classes, List<String> rows) {
        String things = classes.stream()
                .map(OwlClass::getAttribute)
                .map(String::toLowerCase)
                .collect(Collectors.joining(PIPE));

        String header = THING_KEY + COLON + SPACE + SINGLE_QUOTE + THING_VALUE + LEFT_SQUARE_BRACKET + SINGLE_QUOTE +
                SPACE + LEFT_ROUND_BRACKET + things + RIGHT_ROUND_BRACKET + PLUS_SIGN + SPACE + SINGLE_QUOTE +
                RIGHT_SQUARE_BRACKET + SINGLE_QUOTE + SEMICOLON;
        rows.add(header);
    }

    private void fillGrammarMainBody(List<OwlClass> classes, List<SubClassOf> relations, List<String> rows) {
        classes.forEach(thing -> {
            List<ObjectAllValuesFrom> relationsForThing = getRelationsForThing(relations, thing);
            List<String> relationsInStringForThing = convertRelationsToString(relationsForThing, thing);
            String thingName = thing.getAttribute();
            String startDescription = thingName.toLowerCase() + COLON + SPACE + SINGLE_QUOTE +
                    thingName.substring(0, 1).toUpperCase() + thingName.substring(1) + LEFT_BRACE + SINGLE_QUOTE +
                    SPACE + thingName.toLowerCase() + ID + SPACE;

            String thingDescription = getThingDescription(relationsInStringForThing, startDescription);

            rows.add(thingDescription);

            fillRelationsForThing(thing, relationsForThing, rows);
        });
    }

    private String getThingDescription(List<String> relationsInStringForThing, String startDescription) {
        if (relationsInStringForThing.size() == 0) {
            return startDescription + END_DESCRIPTION;
        } else if (relationsInStringForThing.size() == 1) {
            return startDescription + LEFT_ROUND_BRACKET + SINGLE_QUOTE + COMMA + SINGLE_QUOTE +
                    SPACE + SINGLE_QUOTE + LEFT_SQUARE_BRACKET + SINGLE_QUOTE + SPACE + LEFT_ROUND_BRACKET +
                    relationsInStringForThing.get(0) + RIGHT_ROUND_BRACKET + ASTERISK + SPACE + SINGLE_QUOTE +
                    RIGHT_SQUARE_BRACKET + SINGLE_QUOTE + SPACE + RIGHT_ROUND_BRACKET + QUESTION_MARK + SPACE +
                    END_DESCRIPTION;
        } else {
            String relationsInString = relationsInStringForThing.stream()
                    .map(relation -> LEFT_ROUND_BRACKET + relation + RIGHT_ROUND_BRACKET + QUESTION_MARK)
                    .collect(Collectors.joining());

            return startDescription + LEFT_ROUND_BRACKET + SINGLE_QUOTE +COMMA + SINGLE_QUOTE +
                    SPACE + SINGLE_QUOTE + LEFT_SQUARE_BRACKET + SINGLE_QUOTE + SPACE + relationsInString +
                    SPACE + SINGLE_QUOTE + RIGHT_SQUARE_BRACKET + SINGLE_QUOTE + SPACE + RIGHT_ROUND_BRACKET +
                    QUESTION_MARK + SPACE + END_DESCRIPTION;
        }
    }

    private void fillRelationsForThing(OwlClass thing, List<ObjectAllValuesFrom> relationsForThing, List<String> rows) {
        relationsForThing.forEach(relationForThing -> {
            String childClass = relationForThing.getOwlClass().getAttribute().toLowerCase();
            String property = relationForThing.getObjectProperty().getAttribute().toLowerCase();
            rows.add(
                    thing.getAttribute().toLowerCase() + UNDERSCORE + property + UNDERSCORE + childClass +
                    COLON + SPACE + SINGLE_QUOTE + LEFT_BRACE + SINGLE_QUOTE + SPACE + SINGLE_QUOTE +
                    property + SINGLE_QUOTE + SPACE + SINGLE_QUOTE + childClass + SINGLE_QUOTE + SPACE +
                    childClass + ID + SPACE + END_DESCRIPTION
            );
        });
    }

    private List<ObjectAllValuesFrom> getRelationsForThing(List<SubClassOf> relations, OwlClass thing) {
        return relations.stream()
                .filter(subClassOf -> subClassOf.getOwlClass().getAttribute().equals(thing.getAttribute()))
                .map(SubClassOf::getObjectAllValuesFrom)
                .collect(Collectors.toList());
    }

    private List<String> convertRelationsToString(List<ObjectAllValuesFrom> objectAllValuesFroms, OwlClass thing) {
        return objectAllValuesFroms.stream()
                .map(relation -> thing.getAttribute().toLowerCase() + UNDERSCORE +
                        relation.getObjectProperty().getAttribute() + UNDERSCORE +
                        relation.getOwlClass().getAttribute())
                .collect(Collectors.toList());
    }
}
