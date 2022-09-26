package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.map.Brick;
import uet.oop.bomberman.map.Grass;
import uet.oop.bomberman.map.Portal;
import uet.oop.bomberman.map.Wall;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import static uet.oop.bomberman.BombermanGame.*;

public class CreateMap {
    public CreateMap(String level) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File("res/levels/Level1.txt");
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            StringTokenizer tokens = new StringTokenizer(line);
            // the first line in each levelN.txt are level's Name, the map's height, the map's width
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
               idObjects = new int[_width][_height];

               listKill = new int[_width][_height];
                for(int i = 0; i< _width; i++){
                    for (int j = 0; j< _height; j++){
                        idObjects[i][j] = 0;
                        listKill[i][j] = 0;
                    }
                }
                for (int i = 0; i < _height; ++i) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        Entity entity;
                        switch (s) {
                            case 1:
                                entity = new Portal(j, i, Sprite.grass.getFxImage());
                                s = 0;
                                break;
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3:
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
//                            case 6:
//                                entity = new SpeedItem(j, i, Sprite.brick.getFxImage());
//                                break;
//                            case 7:
//                                entity = new FlameItem(j, i, Sprite.brick.getFxImage());
                                //break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                        }
                        idObjects[j][i] = s; // Store map
//                        block.add(entity);
                        stillObjects.add(entity);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
