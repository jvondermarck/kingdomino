package model;

public interface Observer {
    void update(Game game);
    void rotationDomino(Game game);
    void dominoGraph(Game game);
}
