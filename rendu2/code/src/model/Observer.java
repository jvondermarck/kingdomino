package model;

public interface Observer {
    void updateDominoesOnTable(Game game);
    void updateDominoPreview(Game game);
    void updatePlayerKingdom(Game game);
}
