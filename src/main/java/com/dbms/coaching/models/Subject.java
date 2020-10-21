package com.dbms.coaching.models;

public class Subject {
    private String subjectId;
    private String name;
    private String description;

    public Subject() {
    }

    public Subject(String subjectId, String name, String description) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
    }

    /**
     * @return String return the subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
            " subjectId='" + getSubjectId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}
