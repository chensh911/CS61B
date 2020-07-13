package byog.lab5;
import javafx.geometry.Pos;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */

public class HexWorld {
    private static final int WIDTH = 28, HEIGHT = 30;
    private static final long SEED = 2873126;
    private static final Random RANDOM = new Random(SEED);
    private static Position[] p;
    // a simple class of the position with two parameter.
    public static class Position {
        int x;
        int y;
        public Position(int xx, int yy) {
            this.x = xx;
            this.y = yy;
        }
        public Position() { }
    }
    /**
     *  adds a hexagon of side length s to a given position in the world
     * @param world The real word we need to add TETile
     * @param p  a class with two variables p.x and p.y and no methods.
     * @param s the size of the hexagon
     * @param t the TETile we need to add
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        int length = s;
        int row = 0;
        //lower part
        for (; row < s; row += 1) {
            for (int i = 0; i < length; i += 1) {
                world[p.x + i - row][p.y + row] = t;
            }
            length += 2;
        }
        //upper part
        length -= 2;
        for (row = 0; row < s; row += 1) {
            for (int i = 0; i < length; i += 1) {
                world[p.x - s + 1 + i + row][p.y + s + row] = t;
            }
            length -= 2;
        }
    }
    /** Picks a RANDOM tile with a chance of
     * being something in the Tileset
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.GRASS;
            case 4: return Tileset.TREE;
            case 5: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    public static Position[] retPos() {
        int index = 0, s = 3;
        int firstX = s - 1, secondX = 3 * s - 2, thirdX = 4 * s, indexY = 0;
        p = new Position[19];
        //lowest
        for (int i = 0; i < 19; i++) {
            p[i] = new Position();
        }
        p[index].x = thirdX;
        p[index++].y = indexY;
        indexY += s;
        for (int i = 0; i < 3; i += 1) {
            //second
            for (int j = 0; j < 2; j++) {
                p[index].x = secondX + j * (3  * s + 1);
                p[index++].y = indexY;
            }
            indexY += s;
            //first
            for (int j = 0; j < 3; j++) {
                p[index].x = firstX + j * (3  * s + 1);
                p[index++].y = indexY;
            }
            indexY += s;
        }
        for (int j = 0; j < 2; j++) {
            p[index].x = secondX + j * (3  * s + 1);
            p[index++].y = indexY;
        }
        indexY += s;
        p[index].x = thirdX;
        p[index].y = indexY;
        return p;
    }
    public static void main(String[] args) {
        Position[] p = retPos();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];
        // initialize tiles
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hexWorld[x][y] = Tileset.NOTHING;
            }
        }
        for (int i = 0; i < 19; i += 1) {
            addHexagon(hexWorld, p[i], 3, randomTile());
        }

        ter.renderFrame(hexWorld);
    }
}
