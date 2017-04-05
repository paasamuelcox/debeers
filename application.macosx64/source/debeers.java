import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class debeers extends PApplet {


Movie myMovie;


PImage bg;

PImage text;
Capture cam;

public void setup() {
  //size(690, 372);

  
  background(0);
  myMovie = new Movie(this, "1.mov");
  myMovie.loop();
  String[] cameras = Capture.list();


  if (cameras.length == 0) {
    println("There are no cameras available for capture.");
    exit();
  } else {
    println("Available cameras:");
    for (int i = 0; i < cameras.length; i++) {
      println(cameras[i]);
    }

    // The camera can be initialized directly using an 
    // element from the array returned by list():
    cam = new Capture(this, cameras[0]);
    cam.start();
  }
  bg = loadImage("bg.png");
  text = loadImage("text.png");
}

public void draw() {

  if (cam.available() == true) {
    cam.read();
  }
  tint(255, 250);
  pushMatrix();
  scale(-1, 1);
  image(cam.get(), -860, -20, 720, 388);
  popMatrix();

  tint(255, 127);
  image(cam, 120, -20, 720, 388);

  image(myMovie, 150, 0, 690, 372);

  tint(255, 255);
  image(bg, 0, 0, 1000, 555);
  image(text, 150, 10, 680, 340);
  if (mousePressed == true) {
    saveFrame("line-######.png");
  }
}

public void movieEvent(Movie m) {
  m.read();
}

  public void settings() {  size(1000, 555); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "debeers" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
