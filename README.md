# OOPfied Tic-Tac-Toe

## Description
There are two game modes in this project, classical tictactoe and numerical tictactoe. It is played on a 3 by 3 grid. There are two players. The first player to get 3 of their symbols/numbers adding up to 15 on the board in a row is the winner. A tie happens when the board is full and no one got 3 in a row/numbers adding up to 15.
<br/><br/>A user can choose to run the console version or a GUI version of the game. Implemented many different object oriented practices like encapsulation polymorphism, inheritence, abstraction to reduce code duplication so the same classes can be used for different games.
## Usage
[Java Development Kit (JDK)](https://www.oracle.com/ca-en/java/technologies/downloads/) must be installed before use.<br/>
The program should be compiled using Gradle, click [here](https://gradle.org/install/) to download.<br/>Also make sure to be cd'd in the OOPfied-TicTacToe directory.

1. Type first command then press enter.
```
gradle build
```
2. Second command
```
gradle run
```
3. Third command to run console version
```
java -cp build/classes/java/main tictactoe.TextUI
```
4. To run GUI version

```
java -jar build/libs/A3.jar
```
Or alternatively you can use the jar file in this folder to run the GUI application: [OOPfied TTT.zip](https://github.com/realzf/OOPfied-TicTacToe/files/10350025/OOPfied.TTT.zip)

## Expected Output
#### GUI version:
<img width="543" alt="image" src="https://user-images.githubusercontent.com/86804008/210684718-99e1cae9-c94a-45a3-a019-ab7c854af053.png">

#### Console Version:
```
Turn: Player 1 (X)
down? (0 to quit)
```

