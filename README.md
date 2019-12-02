###Initial Readme

The current state of this project is that it is mostly done, and possibly breaking. I plan to fix this readme when the project is fully working. I intended to have a polished readme and fully commented code by I have spent over 30hours on this project, it’s due in an hour. And each big I fix causes another.
All the code currently looks like it should work, and on there own they do. All the stretch goals are implemented and working more or less. I added a bunch more functionality as well.
The code needing comments is an understatement. But basically I implemented normal jets plus I wanted to have different states for flying or killed and store those states.
Implement cargo planes as a way to make money, fighters to defend them and collecting the money to build custom jets
I also have the ability to save and load all states of all jets including there pilots.
There is also some code that is unimplemented.
The biggest feature I tried to implement was a menu. The idea was simple have menus and menu items and make them both able to be stored in menus. This allows for extremely easy submenus.
I came up with this idea because of the massive numbers of switch statements and if statements I was writing and the bigger issue, any time I wanted to change them was a massive hassle.
The issue was I tried to implement the interface runnable. This interface apparently runs separate threads, I was unaware of this.
The code currently may get random thread collisions and I did not have time to implement mutexes. I am not sure of the state of the project.
I plan to fix what is broken tonight.
