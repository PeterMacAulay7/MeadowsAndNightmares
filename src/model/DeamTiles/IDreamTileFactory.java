package model.dreamtiles;

/**
 * This is an interface used to interface DreamTileFactory
 */
public interface IDreamTileFactory {

    /**
     * Takes in a String for the type of DreamTile to be created.
     * Creates and returns the DreamTile that the String corresponds to
     *
     * @param type of DreamTile to be created
     * @return new DreamTile
     */
    public DreamTile createDreamTile(String type);

    public String[] getDreamTileNames();

}
