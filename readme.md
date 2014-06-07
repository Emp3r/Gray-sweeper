#### About Gray Sweeper
Gray Sweeper is a Java implementation of the well known puzzle game Minesweeper. It has customizable both game difficulty and board size with highscore rankings in each category.

Big surprise - it’s called Gray because most of the components are painted in shades of gray.

Written in Eclipse 4.3.2 on Mac OS X 10.9.

Optimized for Mac.

<hr>
### Packages
##### logic
Logic package implements basic logic core classes. Field can be clicked, flagged and should know how many mines are around it. Board sets up bunch of fields, randomly chooses mines and calculates number of mines around each field. 

##### form
Form package implements the component JGame, which basically control the whole game, and main frame Form, which initialize menu and also has the main function.

##### dialogs
Dialogs package implements various dialog windows, such as dialog for highscore tables, classic Yes/No “Are you sure?” dialog, etc.

##### data
Data package implements tools for highscores management. ScoreManager control highscore tables and can save to / load from XML files. Record is basic entity for tables.

<hr>
![](http://emper.cz/img/graysweeper.png)
