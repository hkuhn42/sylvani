
package org.sylvani.oxford;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Result {

    private String scenario;
    private String name;
    private String lexical;
    private String confidence;
    private Properties properties;

    /**
     *
     * @return
     *         The scenario
     */
    public String getScenario() {
        return scenario;
    }

    /**
     *
     * @param scenario
     *            The scenario
     */
    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    /**
     *
     * @return
     *         The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *         The lexical
     */
    public String getLexical() {
        return lexical;
    }

    /**
     *
     * @param lexical
     *            The lexical
     */
    public void setLexical(String lexical) {
        this.lexical = lexical;
    }

    /**
     *
     * @return
     *         The confidence
     */
    public String getConfidence() {
        return confidence;
    }

    /**
     *
     * @param confidence
     *            The confidence
     */
    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    /**
     *
     * @return
     *         The properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     *
     * @param properties
     *            The properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
