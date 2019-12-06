
# Jets
## Week 4 Homework for SkillDistillery
### Overview
This Project is all about jets.

This application is a simulation of an airfield. The goal is to be able to replicate an airfield owning jets and using them for various tasks.

You can check your which jets are at your airfield, the fastest and furthest fliers, and manage your jets.

In my Jets Project, the name of the game is simple, get the best High Score.

Score is achieved by dogfighting jets. A dogfight will take all of the fighters that you have and use them to fight. The probability that a jet will win is based off of how fast the jet can fly and how far the jet can fly. The higher both of these things are, the better your chances of winning.

However the faster and higher fuel capacity jets all end up costing more money.

You can earn money by flying your cargo jets. Cargo jets earn money based off of how fast they can deliver their cargo and how far they can deliver cargo to.

There is a slight delay in every fighter and cargo ships action to make the game more realistic.

There is also the blue print jet creation system, all jets are to be constructed from blue prints. Blue prints simply hold the fields a jet might need in order to fly.
In the app is an ability to build jet blue prints.

The blue prints are then stored in a repository to allow the user to access them and send them to the factory.

The factory is used to build jets based on blue prints. The application allows you to send the factory a set of blue prints and a quantity and it will give you a fresh set of jets(assuming you can afford them)

You build your arsenal of jets and use them to fight enemies in the skies.

if you have some jets that just are not very good, you are able to remove them.

Jet Blue Prints can also be read from a file and loaded into the game.

This can include pilots if you are really attached to the names, or if your jet doesn't have a pilot your airfield can hire one for you.

Happy hunting.

### Concepts
- Object Oriented Code
- Static Serialization
- Abstraction
- PolyMorphism
- InterFaces
- Inheritance
- File I/O
- Encapsulation

### Technologies Used
- Eclipse
- Java
- Git
- OO

### Lessoned Learned
Many lessons were learned from this project, I think the biggest lesson was the need for Agile ideals while developing. I started the project trying to build extra utilities to help make it easier to develop. I made these in large expanse, however due to some of the poor implementation i was unable to create a fully functional product. I reached for goals past my stretch goals and was not even able to have a minimum viable working product.

I then was given the opportunity to retry the project and I changed many things about the implementation. I stripped out much of the utilities I built and instead build a fully functional MVP. Due to the fact that I had extra time on the project, I then went in and implemented some of the stretch goals, adding in pilots and some more playability to the game.

One of the other things I found very useful was the idea of implementing things in there real world way. for example:
  Building a jet: a factory builds a jet. A factory needs to know how to build a jet: Use blue prints. Where do built jets go from the factory: the airfield.

  by implementing the logic in this way, It became very easy when I wanted to add features like "read jets from a file" and "order multiple jets" because the framework was already built in a realistic way. Now when I want to add the new features it becomes simple and not very convoluted. I can just put blue prints in a file or call the build method multiple times.
