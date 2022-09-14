# dataox-test-task
Test task for Junior Java Developer position in the Dataox co.

![](https://reedfree.sirv.com/video_2022-09-14_16-24-50.gif)

# Elevator working model

## Simulation
![](https://reedfree.sirv.com/dataox_test_task.png)

## Overview
The following repo contains examples for OpenFin's Java adapter.

## Guidelines
Run the example of connecting to OpenFin and creating applications

1. Clone this repository

2. Go to release directory and start run.bat

3. Once the java app starts, click on Start button, which should start OpenFin Runtime.  The java app will wait and try to connect to OpenFin Runtime.

## Source Code Review

Source code for the example is located in /src/main/java/com/openfin/desktop/demo/OpenFinDesktopDemo.java.  The followings overview of how it communicates with OpenFin Runtime with API calls supported by the Java adapter:

1. Code example:

```java
	this.desktopConnection = new DesktopConnection("OpenFinDesktopDemo");
```
