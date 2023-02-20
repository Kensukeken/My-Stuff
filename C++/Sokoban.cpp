#include "sokoban.h"
#include <SFML/Graphics.hpp>
#include <fstream>
#include <iostream>

int main(int argc, char* argv[]) {

    std::string name = argv[1];
    std::string name2 = "level1.lvl";

    int row, col;

    std::ifstream sokFile;

    sokFile.open(name2, std::ifstream::in);

    if (!sokFile) {
        std::cerr << "Failed to open file " << name2 << std::endl;
        return 1;
    }

    sokFile >> row >> col;

    std::vector<char> map;

    char ch;
    while (sokFile >> ch) {
        map.push_back(ch);
    }

    std::cout << "test \n";

    for (auto i : map) {
        std::cout << i;
    }

    sf::RenderWindow window(sf::VideoMode(635, 635), "Sokoban!");
    sf::Texture player;
    sf::Texture space;
    sf::Texture wall;
    sf::Texture box;
    sf::Texture boxStorage;

    player.loadFromFile("player_05.png");
    space.loadFromFile("ground_01.png");
    wall.loadFromFile("block_06.png");
    box.loadFromFile("crate_03.png");
    boxStorage.loadFromFile("ground_04.png");

    while (window.isOpen()) {

        sf::Sprite sprite;

        int yCord = 0;
        int xCord = 0;
        int counter = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (map.at(counter) == '@') {
                    sprite.setTexture(player);
                    sprite.setPosition(xCord, yCord);
                    window.draw(sprite);
                }
                else if (map.at(counter) == '.') {
                    sprite.setTexture(space);
                    sprite.setPosition(xCord, yCord);
                    window.draw(sprite);
                }
                else if (map.at(counter) == '#') {
                    sprite.setTexture(wall);
                    sprite.setPosition(xCord, yCord);
                    window.draw(sprite);
                }
                else if (map.at(counter) == 'A') {
                    sprite.setTexture(box);
                    sprite.setPosition(xCord, yCord);
                    window.draw(sprite);
                }
                else if (map.at(counter) == 'a') {
                    sprite.setTexture(boxStorage);
                    sprite.setPosition(xCord, yCord);
                    window.draw(sprite);
                }
                counter = counter + 1;
                xCord = xCord + 64;
            }

            yCord = yCord + 64;
            xCord = 0;
        }

        window.display();

        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed)
                window.close();
        }
    }
    return 0;
}
