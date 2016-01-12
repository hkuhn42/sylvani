
package org.sylvani.oxford;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Recognition {

    private String version;
    private Header header;
    private List<Result> results = new ArrayList<Result>();

    /**
     *
     * @return
     *         The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     *            The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     *         The header
     */
    public Header getHeader() {
        return header;
    }

    /**
     *
     * @param header
     *            The header
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     *
     * @return
     *         The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     *            The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

}
