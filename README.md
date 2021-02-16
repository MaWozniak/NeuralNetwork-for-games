Neural Network for games
========

#### Goal: ####

Create [Neural Network](https://en.wikipedia.org/wiki/Artificial_neural_network) from scratch in Java to use it with [Genetic Algorithms](https://en.wikipedia.org/wiki/Genetic_algorithm) and to create
self-teaching AI-agents for games.

For this purpose I created simple game enviroment which imitate natural enviroment - biotop.

### Test game ###

In the game Predators agents compete with Preys agents.

![Alt text](resources/img1.jpg?raw=true "game")

Predators (red one's) hunt and kill Preys (grey one's) to gain energy to live. Preys hide and run away from Predators -
they feed only in blue areas and can hide in the darker sides of the board where Predators can not reach. This simple
setup is a good enviroment to engage Machine Learning AI-agents in order to teach them to become competetive and fight
for their live. First I created old-fashioned AI-agents to start the game, in the next step I will introduce Machine
Learning AI - like a new preys agents (blue agents).

##### Goal of this simulation (sign that it's working) #####
We want to see AI prey agents hiding from Predators and trying to reach feeding place as fast as they can and again run away to safe places.


### Application structure ###

##### game package #####
- test game classes

##### gui package #####
- gui classes

##### Neural Network package #####
- for development neural network.

### Simulation ###

game (Simulation) start in TestMain.java and runs Biom, ModelView and Gui classes.

```
Simulation(){

        ModelView model = new ModelView(modelFrame);
        Biom game = new Biom(preyNum, predatorsNum, AIpreyNum, biomFramerate, fullspeed, model, organismFramerate);
        gui gui = new gui(guiFramerate, game);
    }
```
Biom.java create Lists of Prey, Predators and PreyAI, and has its own Thread.
```
Biom() {
        this.model = model;
        this.organismUpdateFramerate = organismUpdateFramerate;
        this.addNewPreys(numPrey);
        this.addNewAIPreys(numAIPrey);
        this.addNewPredators(numPred);
        Thread thread = new Thread(lifecycleThread = new LifecycleThread(framerate, fullspeed, this));
        thread.start();
    }
```
Method `lifecycle()` includes the main logic of the game:
```
    void lifecycle() {
        organismsMoves();
        modelViewSet();
        randomAddPrey();
        //randomKillPrey();
        validate();
    }
```
##### ModelView.class #####
Concept of this class is to model what Organisms can see on the board. It's very important because inputs for neural network
 will be readed from this model.

##### Simulation FPS (framerate) #####
Simulation has two different threads with different framerates:
- one for Lifecycle of the Biom class
```
Thread thread = new Thread(lifecycleThread = new LifecycleThread(framerate, fullspeed, game));
```
- second for Gui thread
```
gui gui = new gui(guiFramerate, game);
```

This concept is to separete graphics from model calculation.
(Aim is to do calculation on server-side and to place Gui on web-side(javascript) and comunicate them via Web Sockets.

### Hierarchy of the organisms (inheritance) ###

![Alt text](resources/img2.jpg?raw=true "game")

### Update ###

game is still raw and in the early stage but fair good to start developing neural network and test first mocaps with
AI-agents.
