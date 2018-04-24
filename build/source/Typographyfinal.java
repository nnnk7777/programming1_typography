import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import twitter4j.*; 
import twitter4j.api.*; 
import twitter4j.auth.*; 
import twitter4j.conf.*; 
import twitter4j.json.*; 
import twitter4j.management.*; 
import twitter4j.util.*; 
import twitter4j.util.function.*; 
import java.util.List; 

import gifAnimation.*; 
import twitter4j.examples.account.*; 
import twitter4j.examples.async.*; 
import twitter4j.examples.block.*; 
import twitter4j.examples.directmessage.*; 
import twitter4j.examples.*; 
import twitter4j.examples.favorite.*; 
import twitter4j.examples.friendsandfollowers.*; 
import twitter4j.examples.friendship.*; 
import twitter4j.examples.geo.*; 
import twitter4j.examples.help.*; 
import twitter4j.examples.json.*; 
import twitter4j.examples.lambda.*; 
import twitter4j.examples.list.*; 
import twitter4j.examples.media.*; 
import twitter4j.examples.oauth.*; 
import twitter4j.examples.savedsearches.*; 
import twitter4j.examples.search.*; 
import twitter4j.examples.spamreporting.*; 
import twitter4j.examples.stream.*; 
import twitter4j.examples.suggestedusers.*; 
import twitter4j.examples.timeline.*; 
import twitter4j.examples.trends.*; 
import twitter4j.examples.tweets.*; 
import twitter4j.examples.user.*; 
import twitter4j.*; 
import resources.classes.com.javafx.main.*; 
import com.oracle.tools.packager.*; 
import com.oracle.tools.packager.jnlp.*; 
import com.oracle.tools.packager.linux.*; 
import com.oracle.tools.packager.mac.*; 
import com.oracle.tools.packager.windows.*; 
import com.sun.javafx.tools.ant.*; 
import com.sun.javafx.tools.packager.*; 
import com.sun.javafx.tools.packager.bundlers.*; 
import com.sun.javafx.tools.resource.*; 
import jdk.packager.services.*; 
import jdk.packager.services.userjvmoptions.*; 
import twitter4j.media.*; 
import com.oracle.javafx.jmx.*; 
import com.oracle.javafx.jmx.json.*; 
import com.oracle.javafx.jmx.json.impl.*; 
import twitter4j.api.*; 
import twitter4j.auth.*; 
import twitter4j.conf.*; 
import twitter4j.json.*; 
import twitter4j.management.*; 
import twitter4j.util.*; 
import twitter4j.util.function.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Typographyfinal extends PApplet {












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
int textc = color(21, 109, 157);
PFont font ;
PFont fontA;

public void setup() {
  frameRate(50);
  
  background(255);

  font = createFont("MS-Gothic-48.vlw", 48);//\u6587\u5b57\u306e\u4f5c\u6210
  fontA = createFont("AdobeArabic-Regular.otf", 35);//\u6587\u5b57\u306e\u4f5c\u6210
  textFont(font); // \u9078\u629e\u3057\u305f\u30d5\u30a9\u30f3\u30c8\u3092\u6307\u5b9a\u3059\u308b

  ui.frame();
  fill(80);
  rect(width-200, 0, 200, height);
  ui.button();
  ui.colorpalette();
}

public void draw() {
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

public void mousePressed() {
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

public void mouseDragged() {
  if (width-165<mouseX && mouseX<width-15 && 280<mouseY && mouseY<480) {
    textc = get(mouseX, mouseY);
  }
}

public void mouseReleased() {
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

public void fileinput() {
  selectInput("Select a file to process:", "fileSelected");
}

public void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    initFlag = true;
    inputFlag = true;
    openfilepath = selection.getAbsolutePath();
    println("User selected " + openfilepath);
  }
}
//\u914d\u5217\u3092\u7ba1\u7406\u3059\u308b\u30af\u30e9\u30b9

class Arrays {
  PVector[] pos;
  PVector[] pos_;
  PVector[] finalPos;
  PVector[] vel;


  public void initArray() {
    //\u5404\u914d\u5217\u3092\u4f5c\u6210
    //strArray = new String[count_one];
    pos = new PVector [cells[nArray].count_one];
    pos_ = new PVector [cells[nArray].count_one];
    finalPos = new PVector [cells[nArray].count_one];
    vel = new PVector [cells[nArray].count_one];

    //\u30e9\u30f3\u30c0\u30e0\u8868\u793a\u30fb\u5909\u6570\u521d\u671f\u5316
    for (int i=0; i<cells[nArray].count_one; i++) {
      pos[i] = new PVector ();
      pos_[i] = new PVector();
      finalPos[i] = new PVector();
      vel[i] = new PVector();
    }
  }

  public void newArray() {
    int n=0;
    for (int j=0; j<height/cells[nArray].cellSize; j++) {
      for (int i=0; i<(width-200)/cells[nArray].cellSize; i++) {
        if (cells[nArray].cell[i][j] == 1) {
          finalPos[n].x = i*cells[nArray].cellSize;
          finalPos[n].y = j*cells[nArray].cellSize;
          n++;
        }
      }
    }
    println("finalPos[]\u306e\u500b\u6570\uff1an="+n);
  }
}


//\u30de\u30b9\u3092\u7ba1\u7406\u3059\u308b\u30af\u30e9\u30b9
//\u30de\u30b9\u306e\u30b5\u30a4\u30ba\u3001\u8272\u3001\u30d5\u30e9\u30b0\u3092\u7ba1\u7406

class Cells {
  int cellSize;
  int sum;
  float br;
  int cCell;
  int count_one;
  int cell[][];
  int temp[][] = new int[(width-200)/ArraySize*10][height/ArraySize*10];

  Cells(int cellSize_) {
    cellSize = cellSize_;
    count_one = 0;
  }

  public void initCellArray() {
    cell = new int[(width-200)/cellSize][height/cellSize];
    //imgs.display();

    for (int j=0; j<height/cellSize; j++) {
      for (int i=0; i<(width-200)/cellSize; i++) {
        //\u30de\u30b9\u306e\u521d\u671f\u5316\uff08\u5168\u90e80\uff09
        cell[i][j] = 0;
      }
    }
  }

  public void colorscan(float val) {
    for (int j=0; j<height/cellSize; j++) {
      for (int i=0; i<(width-200)/cellSize; i++) {
        sum = 0;
        //\u30de\u30b9\u306e\u5de6\u4e0a\u306e\u5ea7\u6a19\u304b\u3089\u53f3\u3068\u4e0b\u306bcellSize\u30d4\u30af\u30bb\u30eb\u305a\u3064\u306e\u8272\u3092\u53d6\u5f97
        for (int a=0; a<cellSize; a++) {
          for (int b=0; b<cellSize; b++) {
            cCell = get(i*cellSize+a, j*cellSize+b);
            //\u660e\u308b\u3055\u5224\u5b9a
            br = brightness(cCell);
            sum += br;
          }
        }
        //\u305d\u306e\u30de\u30b9\u306e\u660e\u308b\u3055\u306e\u5e73\u5747\u5024\u3092\u51fa\u3059
        float ave = sum/cellSize/cellSize;
        //\u660e\u308b\u3055\u306e\u5e73\u5747\u5024\u304c5\u4ee5\u4e0b\u306a\u3089\u30d5\u30e9\u30b0\u30921\u306b\u3059\u308b
        if (ave<val) {
          cell[i][j] = 1;
        }
        //println(ave);
      }
    }
  }

  public void count() {
    count_one = 0;
    for (int j=0; j<height/cellSize; j++) {
      for (int i=0; i<(width-200)/cellSize; i++) {
        if (cell[i][j] == 1) {
          count_one++;
        }
      }
    }
    //println(nArray+"\uff1a"+count_one+"\u6587\u5b57");
  }

  public void whiterect(int c) {
    for (int j=0; j<height/cellSize; j++) {
      for (int i=0; i<(width-200)/cellSize; i++) {
        if (cell[i][j]==1) {
          fill(c);
          noStroke();
          rect(i*cellSize, j*cellSize, cellSize, cellSize);
        }
      }
    }
  }
}
class FileIO {
  String filename = "01.txt";
  String[] olines = new String[height/cells[ArraySize-1].cellSize];
  String[] ilines = loadStrings("01.txt");
  String[] words = new String[(width-200)/cells[ArraySize-1].cellSize];

  public void output() {
    for (int j=0; j<height/cells[ArraySize-1].cellSize; j++) {
      for (int i=0; i<(width-200)/cells[ArraySize-1].cellSize; i++) {
        words[j] = words[j] + str(cells[ArraySize-1].temp[i][j])+",";
      }
      words[j] = words[j].replace("null", "");
      olines[j] = words[j];
      println(olines[j]);
    }
    saveStrings(filename, olines);
    outputFlag = false;
  }

  public void input() {
    fill(255);
    rect(0, 0, width-200, height);
    for (int j=0; j<ilines.length; j++) {
      String[] data = split(ilines[j], ',');
      for (int i=0; i<(width-200)/cells[ArraySize-1].cellSize; i++) {
        cells[ArraySize-1].temp[i][j] = PApplet.parseInt(data[i]);
      }
      //println(olines[j]);
    }
    cells[ArraySize-1].whiterect(color(0));
  }

  public void fileopen() {
    selectInput("Select a file to process:", "fileSelected");
  }
}
class GetTimeLine {
  String consumerKey;
  String consumerSecret;
  String accessToken;
  String accessSecret;

  String Tweet;
  String finalStr[] = new String[ArraySize];

  String[][] url;
  String[][] at;
  String[][] symbol;
  String[][] symbol_;
  String[][] hashtag;

  int twcount;

  Twitter twitter;
  ResponseList<Status> statuses = null;

  GetTimeLine() {

    //***** Typographitter *****\u306e\u30a2\u30af\u30bb\u30b9\u30c8\u30fc\u30af\u30f3
    consumerKey = "nomLQJ7rwuALB0vfP6lIVhao5";
    consumerSecret = "gxJTCzvWyqijbatvfsUv2mRhsiSlpCeU1VHgCh7XoO769dw1T2";
    accessToken = "313885494-eO2KSc1Vp3jYyOE79Em00btnvyG2hYjsZtilV3gI";
    accessSecret = "yAjVax2HxfmDhOUuUtDYUzr5pte4Jxvhe7EFuqMfp8NRB";

    twcount = 100;
  }

  public void getTimeLine(int a) {
    //OAuth\u8a8d\u8a3c  
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setOAuthConsumerKey(consumerKey);
    cb.setOAuthConsumerSecret(consumerSecret);
    cb.setOAuthAccessToken(accessToken);
    cb.setOAuthAccessTokenSecret(accessSecret);
    //TwitterAPI\u30a4\u30f3\u30b9\u30bf\u30f3\u30b9\u53d6\u5f97
    twitter = new TwitterFactory(cb.build()).getInstance();

    try {
      //\u30bf\u30a4\u30e0\u30e9\u30a4\u30f3\u3092\u53d6\u5f97
      for (int i=1; i<a; i++) {
        statuses = twitter.getHomeTimeline(new Paging(i, twcount));
        for (Status status : statuses) {
          //\u30c4\u30a4\u30fc\u30c8\u3092\u53d6\u5f97
          Tweet = status.getText();
          scan_tweet();
          str = str + Tweet;
          str = str.replace("null", "");
          str = str.replace("RT", "");
          str = str.replace(" ", "");
          str = str.replace("\u3000", "");
          str = str.replace("\u3001", "");
          str = str.replace("\u3002", "");
          //str = str.replace("#P\u6f14", "");
          if (str.length() >= Tweetlength)break;
        }
      }
      net = true;
      divide();
    }

    catch(TwitterException e) {
      for (int i=0; i<Tweetlength; i++) {
        str = str + "FMS";
        if (str.length() >= Tweetlength) {
          str = str.replace("null", "");
          break;
        }
      }
      divide();

      println("1***"+"Get timeline:" + e + "Status code:" + e.getStatusCode());
      println("2***"+e.getMessage());
      println("3***"+e.isCausedByNetworkIssue());
      println("4***"+e.getRateLimitStatus());
    }
  }

  public void scan_tweet() {
    url = matchAll(Tweet, "(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+");
    at = matchAll(Tweet, "@(\\w)+");
    symbol = matchAll(Tweet, "\u300c|\u300d|%|\u2026|'|:|\uff1a|\uff1b|,|(|)|\uff08|\uff09");
    symbol_ = matchAll(Tweet, "\\s");
    hashtag = matchAll(Tweet, "#(\\w|\u3042-\u3093)+");

    if (at != null) {
      for (int i=0; i<at.length; i++) {
        Tweet = Tweet.replace(at[i][0], "");
      }
    }
    if (hashtag != null) {
      for (int i=0; i<hashtag.length; i++) {
        Tweet = Tweet.replace(hashtag[i][0], "");
      }
    }
    if (url != null) {
      for (int i=0; i<url.length; i++) {
        Tweet = Tweet.replace(url[i][0], "");
      }
    }
    if (symbol != null) {
      for (int i=0; i<symbol.length; i++) {
        Tweet = Tweet.replace(symbol[i][0], "");
      }
    }
    if (symbol_ != null) {
      for (int i=0; i<symbol_.length; i++) {
        Tweet = Tweet.replace(symbol_[i][0], "");
      }
    } else {
    }
  }

  public void divide() {
    finalStr[0] = str.substring(0, cells[0].count_one);
    for (nArray=1; nArray<ArraySize; nArray++) {
      finalStr[nArray] = str.substring(cells[nArray-1].count_one, cells[nArray-1].count_one+cells[nArray].count_one);
    }
  }
}
//\u753b\u50cf\u3092\u7ba1\u7406\u3059\u308b\u30af\u30e9\u30b9

class Images {
  int sum;
  float br;
  int cCell;
  PImage img;

  public void display() {
    //translate(100, 0);
    img = loadImage(openfilepath);
    imageMode(CENTER);

    //\u753b\u50cf\u304c\u30a6\u30a3\u30f3\u30c9\u30a6\u3088\u308a\u5927\u304d\u3044\u5834\u5408\u306e\u51e6\u7406
    if (img.width>(width-200) || img.height>height) {
      //\u753b\u50cf\u304c\u6a2a\u9577\u3060\u3063\u305f\u5834\u5408
      if (img.width>(width-200)) {
        img.resize((width-200), (width-200)/img.width*img.height);
      }
      //\u753b\u50cf\u304c\u7e26\u9577\u3060\u3063\u305f\u5834\u5408
      if (img.height>height) {
        img.resize(height/img.height*img.width, height);
      }
      //\u753b\u50cf\u304c\u6b63\u65b9\u5f62\u3060\u3063\u305f\u5834\u5408
      if (img.width==img.height) {
        img.resize(height, height);
      }
    }
    image(img, width/2-100, height/2, img.width, img.height);
  }
}
class Initialize {

  public void initialize() {
    for (int i=0; i<ArraySize; i++) {
      cells[i] = new Cells((ArraySize-i)*10);
      arrays[i] = new Arrays();
      motion[i] = new Motion();
      textSize[i] = cells[i].cellSize-2*(1-0.33f*i);
    }

    //\u30bb\u30eb\u306e\u30b5\u30a4\u30ba\u3054\u3068\u306b\u660e\u308b\u3055\u306e\u95be\u5024\u3092\u8a2d\u5b9a
    brVal[0] = 0.5f;
    brVal[1] = 20;
    brVal[2] = 40;
    brVal[3] = 150;

    if (inputFlag) {
      //\u30d5\u30a1\u30a4\u30eb\u66f8\u304d\u51fa\u3057\u7528\u306e\u6700\u5c0f\u30bb\u30eb\u7528\u306e01\u30c7\u30fc\u30bf\u3092\u4f5c\u6210
      imgs.display();          //\u753b\u50cf\u306e\u8868\u793a
    }

    cells[ArraySize-1].initCellArray();   //\u30de\u30b9\u306e\u521d\u671f\u5316
    cells[ArraySize-1].colorscan(brVal[ArraySize-1]);
    cells[ArraySize-1].count();           //\u9ed2\u3044\u30de\u30b9\u306e\u30ab\u30a6\u30f3\u30c8

    if (outputFlag) {
      for (int j=0; j<height/cells[ArraySize-1].cellSize; j++) {
        for (int i=0; i<(width-200)/cells[ArraySize-1].cellSize; i++) {
          cells[ArraySize-1].temp[i][j] = cells[ArraySize-1].cell[i][j];
        }
      }
    }

    fio = new FileIO();
    if (intextFlag)fio.input();
    //fio.output();

    if (inputFlag) {
      if (intextFlag) {
        fill(255);
        rect(0, 0, width-200, height);
      }
      imgs.display();          //\u753b\u50cf\u306e\u8868\u793a
    }


    for (nArray=0; nArray<ArraySize; nArray++) {
      cells[nArray].initCellArray();   //\u30de\u30b9\u306e\u521d\u671f\u5316
      cells[nArray].colorscan(brVal[nArray]);
      cells[nArray].count();           //\u9ed2\u3044\u30de\u30b9\u306e\u30ab\u30a6\u30f3\u30c8
      arrays[nArray].initArray();      //\u914d\u5217\u306e\u5b9a\u7fa9\u30fb\u521d\u671f\u5316
      arrays[nArray].newArray();       //2\u6b21\u5143\u914d\u5217\u306e1\u6b21\u5143\u5316
      cells[nArray].whiterect(color(255));
      strs[nArray] = new Str();   //strArray\u306e\u5b9a\u7fa9\uff08count_one\u306e\u5024\u304c\u6c7a\u307e\u3063\u3066\u3044\u306a\u3044\u3068\u5b9a\u7fa9\u3067\u304d\u306a\u3044\u306e\u3067\u3053\u3053\u306b\u914d\u7f6e\uff09
      motion[nArray].initPos();
      Tweetlength += cells[nArray].count_one;
    }

    println("Tweetlength;"+ Tweetlength);  
    //\u30bf\u30a4\u30e0\u30e9\u30a4\u30f3\u306e\u8aad\u307f\u8fbc\u307f
    tweet.getTimeLine(loadPage);

    if (net) {
      for (nArray = 0; nArray<ArraySize; nArray++) {
        strs[nArray].textSet();
        println("finalStr"+nArray+"\u306e\u9577\u3055:"+tweet.finalStr[nArray].length());
        println("finalStr:"+tweet.finalStr[nArray]);
      }
    } else {
      for (nArray = 0; nArray<ArraySize; nArray++) {
        strs[nArray].textSetoffline();
        println("finalStr"+nArray+"\u306e\u9577\u3055:"+tweet.finalStr[nArray].length());
        println("finalStr:"+tweet.finalStr[nArray]);
      }
    }

    //test.check();    //\u3053\u306e\u6642\u70b9\u3067\u306e\u5404\u5927\u304d\u3055\u306e\u6b63\u65b9\u5f62\u3054\u3068\u306ecellSize, count_one, finalPos[0]\u3092\u30c1\u30a7\u30c3\u30af

    initFlag = false;
    inputFlag = false;
    intextFlag = false;
    loopFlag = true;
    mouseFlag = true;
    outputFlag = true;
  }
}
class Motion {
  float px = 0;
  float py = 0;
  float texth=0;

  public void update() {
    for (int i=0; i<cells[nArray].count_one; i++) {
      arrays[nArray].pos_[i].x = arrays[nArray].finalPos[i].x;
      arrays[nArray].pos_[i].y = arrays[nArray].finalPos[i].y;
      //\u901f\u5ea6\u751f\u6210
      arrays[nArray].vel[i] = arrays[nArray].pos_[i].sub(arrays[nArray].pos[i]).div(frame);
    }
  }

  public void randomize() {
    for (int i=0; i<cells[nArray].count_one; i++) {
      arrays[nArray].pos_[i] = new PVector (random(0, width-200), random(0, height-textSize[nArray]));
      //\u901f\u5ea6\u751f\u6210
      arrays[nArray].vel[i] = arrays[nArray].pos_[i].sub(arrays[nArray].pos[i]).div(frame);
    }
  }

  public void initPos() {
    int countpos=0;
    for (int i=0; i<cells[nArray].count_one; i++) {
      px = 30 + (i%((width-200)/cells[nArray].cellSize))*textSize[nArray];
      py = 20 + height/4*nArray+texth;
      if (countpos>=(width-200)/cells[nArray].cellSize-1) {
        texth = texth+textSize[nArray];
        countpos=0;
      }
      arrays[nArray].pos[i] = new PVector (px, py);
      countpos++;
    }
  }

  public void mainloop() {
    //background(40);
    fill(255);
    rect(0, 0, width-200, height);

    for (nArray=0; nArray<ArraySize; nArray++) {
      if (count<frame) {
        int j = 0;
        while (j<cells[nArray].count_one) {
          arrays[nArray].pos[j] = arrays[nArray].pos[j].add(arrays[nArray].vel[j]);
          j++;
        }
      }
    }

    for (nArray=0; nArray<ArraySize; nArray++) {
      for (int i=0; i<cells[nArray].count_one; i++) {
        fill(textc );
        strokeWeight(20);
        textSize(textSize[nArray]);
        text(strs[nArray].strArray[i], arrays[nArray].pos[i].x, arrays[nArray].pos[i].y+textSize[nArray]);
      }
    }
    count++;
  }
}
class Str {
  String[] strArray = new String[cells[nArray].count_one];

  public void textSet() {
    //strArray[i]\u306bstr(tweet)\u3092\u683c\u7d0d
    for (int i = 0; i < cells[nArray].count_one; i++) {
      strArray[i] = String.valueOf(tweet.finalStr[nArray].charAt(i));
    }
  }
  public void textSetoffline() {
    //\u30bf\u30a4\u30e0\u30e9\u30a4\u30f3\u304c\u53d6\u5f97\u51fa\u6765\u306a\u3044\u5834\u5408\u3001strArray[i]\u306b\u5225\u306e\u6587\u5b57\u3092\u683c\u7d0d
    for (int i = 0; i < cells[nArray].count_one; i++) {
      strArray[i] = String.valueOf(tweet.finalStr[nArray].charAt(i));
    }
  }
}
//\u5185\u5bb9\u3068\u306f\u76f4\u63a5\u95a2\u4fc2\u306e\u306a\u3044\u30af\u30e9\u30b9\u306e\u7ba1\u7406
//\u8996\u899a\u7684\u306b\u52a9\u3051\u306b\u306a\u308b\u3082\u306e\u3084\u914d\u5217\u30fb\u5909\u6570\u306e\u5024\u306e\u30c1\u30a7\u30c3\u30af\u306a\u3069\u3092\u884c\u3046

class Test {
  public void drawline() {
    stroke(120);
    for (int i=0; i<(width-200)/cells[nArray].cellSize; i++) {
      line(i*cells[nArray].cellSize, 0, i*cells[nArray].cellSize, height);
    }
    for (int j=0; j<height/cells[nArray].cellSize; j++) {
      line(0, j*cells[nArray].cellSize, (width-200), j*cells[nArray].cellSize);
    }
  }

  public void check() {
    for (int i=0; i<ArraySize; i++) {
      println("\u2605\uff1aArraySize:"+i);
      println("cellSize:"+cells[i].cellSize);
      println("count_one:"+cells[i].count_one);
      println("finalPos:"+arrays[i].finalPos[0]);
      println("******");
    }
  }
}
class UI {
  int w;

  public void frame() {
    noStroke();
    fill(200);
    rect(0, 0, width-200, 5);
    rect(0, height-5, width-200, 5);
    rect(0, 0, 5, height);
    rect(width-205, 0, 10, height);
  }

  public void button() {
    fill(74, 191, 49);
    rect(width-200+35, 10, 150, 50);
    fill(255);
    textSize(30);
    text("OPEN", width-200+70, 46);


    fill(93, 178, 213);
    rect(width-200+35, 110, 150, 50);
    fill(255);
    textSize(23);
    text("FROM TEXT", width-200+43, 143);

    fill(181, 54, 63);
    rect(width-200+35, 210, 150, 50);
    fill(255);
    textSize(23);
    text("WRITE 01", width-200+63, 243);
  }

  public void colorpalette() {
    fill(40);
    rect(width-200+35, 520, 150, 50);
    colorMode(HSB);
    for (float h=0; h<255; h++) {
      for (float s=0; s<255; s++) {
        fill(h, s, 180);
        rect(width-165+h/255*150, 280+s/255*200, 1, 2);
      }
    }
    fill(textc);
    rect(width-100, 520, 85, 50);
    textSize(20);
    textFont(fontA);
    text("A", width-150, 560);
    textFont(font);
    colorMode(RGB);
  }
}
  public void settings() {  size(1200, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Typographyfinal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
