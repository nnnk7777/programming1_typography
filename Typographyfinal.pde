import twitter4j.*;
import twitter4j.api.*;
import twitter4j.auth.*;
import twitter4j.conf.*;
import twitter4j.json.*;
import twitter4j.management.*;
import twitter4j.util.*;
import twitter4j.util.function.*;

import java.util.List;

int ArraySize = 4;
int loadPage = 3;

Images imgs = new Images();
Cells[] cells = new Cells[ArraySize];
Arrays[] arrays = new Arrays[ArraySize];
Motion[] motion = new Motion[ArraySize];
Str[] strs = new Str[ArraySize];
GetTimeLine tweet = new GetTimeLine();
Initialize ini = new Initialize();
UI ui = new UI();
FileIO fio;

Test test = new Test();

int count = 0;
float frame = 100;
boolean net = false;
boolean initFlag = false;
boolean loopFlag = false;
boolean mouseFlag = false;
boolean inputFlag = false;
boolean intextFlag = false;
boolean outputFlag = false;
int nArray=0;
float[] brVal = new float[ArraySize];
float[] textSize = new float[ArraySize];
int Tweetlength=0;

String str = null;
String openfilepath;
color textc = color(21, 109, 157);
PFont font ;
PFont fontA;

void setup() {
  frameRate(50);
  size(1200, 600);
  background(255);

  font = createFont("MS-Gothic-48.vlw", 48);//文字の作成
  fontA = createFont("AdobeArabic-Regular.otf", 35);//文字の作成
  textFont(font); // 選択したフォントを指定する

  ui.frame();
  fill(80);
  rect(width-200, 0, 200, height);
  ui.button();
  ui.colorpalette();
}

void draw() {
  if (initFlag)ini.initialize();
  if (loopFlag) {
    motion[0].mainloop();
  }

  fill(50);
  rect(width-200, 0, 30, height);


  colorMode(HSB);
  fill(textc);
  rect(width-100, 520, 85, 50);
  textSize(20);
  textFont(fontA);
  text("A", width-150, 560);
  textFont(font);
  colorMode(RGB);


  //saveFrame("worldmap-####.gif");
}

void mousePressed() {
  if (mouseFlag) {
    if (0<mouseX && mouseX<width-200 && 0<mouseY && mouseY<height) {
      count = 0;
      if (mouseButton == LEFT) {
        for (nArray=0; nArray<ArraySize; nArray++) {
          motion[nArray].update();
        }
      }
      if (mouseButton == RIGHT) {
        for (nArray=0; nArray<ArraySize; nArray++) {
          motion[nArray].randomize();
        }
      }
    }
  }
}

void mouseDragged() {
  if (width-165<mouseX && mouseX<width-15 && 280<mouseY && mouseY<480) {
    textc = get(mouseX, mouseY);
  }
}

void mouseReleased() {
  if (width-165<mouseX && mouseX<width-15 && 10<mouseY && mouseY<60) {
    fileinput();
  }
  if (width-165<mouseX && mouseX<width-15 && 110<mouseY && mouseY<160) {
    intextFlag = true;
    ini.initialize();
  }
  if (width-165<mouseX && mouseX<width-15 && 210<mouseY && mouseY<260) {
    if (outputFlag) {
      fio.output();
      println("output 01 file");
    }
  }
}

void fileinput() {
  selectInput("Select a file to process:", "fileSelected");
}

void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    initFlag = true;
    inputFlag = true;
    openfilepath = selection.getAbsolutePath();
    println("User selected " + openfilepath);
  }
}
