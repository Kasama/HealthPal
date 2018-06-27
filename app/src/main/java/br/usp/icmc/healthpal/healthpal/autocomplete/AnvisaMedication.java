package br.usp.icmc.healthpal.healthpal.autocomplete;

public class AnvisaMedication {
    private String name;
    private String company;
    private String leaflet;
    private String code;

    public AnvisaMedication(String name, String company, String leaflet, String code) {
        this.name = name;
        this.company = company;
        this.leaflet = leaflet;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.company;
    }

    public AnvisaMedication() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLeaflet() {
        return leaflet;
    }

    public void setLeaflet(String leaflet) {
        this.leaflet = leaflet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
