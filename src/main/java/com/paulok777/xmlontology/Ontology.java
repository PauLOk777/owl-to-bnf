package com.paulok777.xmlontology;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "Ontology")
@XmlType(propOrder = {"prefix", "import", "declarations", "subClassOfs"})
public class Ontology {

    private Prefix prefix;
    private String aImport;
    private List<Declaration> declarations;
    private List<SubClassOf> subClassOfs;

    @XmlElement(name = "Prefix")
    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    @XmlElement(name = "Import")
    public String getImport() {
        return aImport;
    }

    public void setImport(String aImport) {
        this.aImport = aImport;
    }

    @XmlElement(name = "Declaration")
    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    @XmlElement(name = "SubClassOf")
    public List<SubClassOf> getSubClassOfs() {
        return subClassOfs;
    }

    public void setSubClassOfs(List<SubClassOf> subClassOfs) {
        this.subClassOfs = subClassOfs;
    }

    @Override
    public String toString() {
        return "Ontology {" +
                "prefix=" + prefix +
                ", import='" + aImport + '\'' +
                ", declaration=" + declarations +
                ", subClassOf=" + subClassOfs +
                '}';
    }
}
