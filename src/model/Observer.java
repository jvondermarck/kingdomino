package model;

public interface Observer {
    void updateDominoesOnTable(Game game);
    void updateDominoPreview(Game game);
    void updatePlayerGraph(Game game);
}
