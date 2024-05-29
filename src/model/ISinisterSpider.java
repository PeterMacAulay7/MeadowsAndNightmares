/**
 * Represents the Sinister Spider Nightmare
 * has access to an IWebToken
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface ISinisterSpider {

    /**
     * creates an instance of IWebToken
     */
    public void createWebToken();

    /**
     * returns the instance of IWebToken
     * @return web token
     */
    public IWebToken getWebToken();

    
}
