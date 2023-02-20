#ifndef Sokoban_h
#define Sokoban_h

#include <SFML/Graphics.hpp>
#include <iostream>
#include <fstream>
#include <string>
#include <vector>

class Sokoban{

public:
  Sokoban(); // constructor
  void loadLevel(std::string filename);
  void movePlayer(int dx, int dy);
  void moveBox(int boxIndex, int dx, int dy);
  bool checkWin();
  void draw(sf::RenderWindow& window);

  std::vector<char> fileContents;
  int col;
  int row; 
  
private:
  int playerIndex;
  std::vector<int> boxIndices;
  
};

#endif
