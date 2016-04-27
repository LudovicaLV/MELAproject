/* MELAprog5.java */
/* Generated By:JavaCC: Do not edit this line. MELAprog5.java */
package ParserProg5;

import Actions.Action;
import Actions.EnvAction;
import Actions.InfAction;
import Actions.NoInfAction;
import Actions.PassAction;

import Model.Agent;
import Model.AgentManager;
import Model.ExpEvaluator;
import Model.GlobalManager;
import Model.Location;
import Model.LocationManager;
import Model.ParamManager;


public class MELAprog5 implements MELAprog5Constants {

    public MELAprog5() {
        this(new java.io.StringReader(""));
    }

    public void parseFromString( String modelDef ) throws ParseException, TokenMgrError, NumberFormatException {
        this.ReInit(new java.io.StringReader(modelDef));
        this.Input();
    }

    public void parseFromFile( String filename ) throws ParseException, TokenMgrError, NumberFormatException {
        try { this.ReInit(new java.io.FileReader(filename)); }
        catch(java.io.IOException e) {throw new ParseException("Error while opening file " + filename + ": " + e); }
        this.Input();
    }

/***********************************************
GRAMMAR RULES
***********************************************/
  final public 
void Input() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token space;
    boolean spaceboolean = false, agents = false, init = false;
    jj_consume_token(SECTION_SPACE);
spaceboolean = true;
    jj_consume_token(SEMICOLON);
    space = SpaceStructure();
LocationManager.SpatialSt = space.image;
    jj_consume_token(SECTION_PARAM);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IDENTIFIER:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      Param();
    }
    jj_consume_token(SECTION_AGENTS);
agents = true;
    label_2:
    while (true) {
      Agent();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IDENTIFIER:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
    }
    jj_consume_token(SECTION_ENV);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IDENTIFIER:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      EnvAgent();
    }
AgentManager.GlobalMatrixCreation();
    jj_consume_token(0);
  }

  final public void Param() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token t1,t2;
    t1 = jj_consume_token(IDENTIFIER);
    jj_consume_token(ASSIGN);
    t2 = jj_consume_token(DOUBLE);
    jj_consume_token(EOL);
double value = Double.parseDouble(t2.image);
        if(value == Utility.Utility.ERROR_NUM) {
            {if (true) throw new ParseException("line " + t2.beginLine + " colum " + t2.beginColumn +": "+t1.image +" not correctly defined!");}
        }
        GlobalManager.getParamManager().addParam(t1.image, value);
  }

  final public void Agent() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token t;
    Agent agent;
    t = jj_consume_token(IDENTIFIER);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LR:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
      jj_consume_token(LR);
      jj_consume_token(KEYWORD_LOC);
      jj_consume_token(RR);
    }
AgentManager.addAgentName(t.image);
     agent = new Agent(t.image);
    jj_consume_token(DEFINE);
    Action(agent);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_5;
      }
      jj_consume_token(PLUS);
      Action(agent);
    }
    jj_consume_token(EOL);
  }

  final public void EnvAgent() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token t;
    Agent agent;
    t = jj_consume_token(IDENTIFIER);
AgentManager.addAgentName(t.image);
     agent = new Agent(t.image);
    jj_consume_token(DEFINE);
    EnvAction(agent);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_6;
      }
      jj_consume_token(PLUS);
      EnvAction(agent);
    }
    jj_consume_token(EOL);
  }

/*  So far problem solved without using this part

void Env() throws NumberFormatException, RuntimeException, ParseException :
{
    Token t;
    Env agent;
}
{
    t = <IDENTIFIER>
    {AgentManager.addAgentName(t.image);
     agent = new Agent(t.image);}
    <DEFINE>
    EnvAction(agent) 
    (
        <PLUS> 
        EnvAction(agent) 
    )* 
    <EOL>
}
*/
  final public 
Token SpaceStructure() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token spaceKey=null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case KEYWORD_GRAPH:
    case KEYWORD_ONED:
    case KEYWORD_TWOD:
    case KEYWORD_THREED:{
      if (jj_2_1(2)) {
        spaceKey = jj_consume_token(KEYWORD_GRAPH);
setGraph();
      } else if (jj_2_2(2)) {
        spaceKey = jj_consume_token(KEYWORD_ONED);
setOneD();
      } else if (jj_2_3(2)) {
        spaceKey = jj_consume_token(KEYWORD_TWOD);
setTwoD();
      } else if (jj_2_4(2)) {
        spaceKey = jj_consume_token(KEYWORD_THREED);
setThreeD();
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
{if ("" != null) return spaceKey;}
    throw new Error("Missing return statement in function");
  }

  final public Token InfSet() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token set=null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case KEYWORD_LOC:
    case KEYWORD_NEIGH:
    case KEYWORD_ALL:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case KEYWORD_LOC:{
        set = jj_consume_token(KEYWORD_LOC);
        break;
        }
      case KEYWORD_NEIGH:{
        set = jj_consume_token(KEYWORD_NEIGH);
        break;
        }
      case KEYWORD_ALL:{
        set = jj_consume_token(KEYWORD_ALL);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      ;
    }
{if ("" != null) return set;}
    throw new Error("Missing return statement in function");
  }

  final public Token MelaSymbol() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token sym=null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DOT:
    case UP:
    case DOWN:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DOT:{
        sym = jj_consume_token(DOT);
        break;
        }
      case UP:{
        sym = jj_consume_token(UP);
        break;
        }
      case DOWN:{
        sym = jj_consume_token(DOWN);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      ;
    }
{if ("" != null) return sym;}
    throw new Error("Missing return statement in function");
  }

  final public Token UpdateLoc() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token update=null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case KEYWORD_LOC:
    case IDENTIFIER:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case KEYWORD_LOC:{
        update = jj_consume_token(KEYWORD_LOC);
        break;
        }
      case IDENTIFIER:{
        update = jj_consume_token(IDENTIFIER);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      ;
    }
{if ("" != null) return update;}
    throw new Error("Missing return statement in function");
  }

  final public void Action(Agent agent) throws ParseException, NumberFormatException, RuntimeException, ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LR:
    case LANG:
    case DASH:{
      if (jj_2_5(2)) {
        NoInfAction(agent);
      } else if (jj_2_6(2)) {
        InfAction(agent);
      } else if (jj_2_7(2)) {
        PassAction(agent);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      ;
    }
  }

  final public void NoInfAction(Agent agent) throws ParseException, NumberFormatException, RuntimeException, ParseException {Token name = null, rateName = null, symbol=null, update, updateloc=null;
    jj_consume_token(LR);
    name = jj_consume_token(IDENTIFIER);
    jj_consume_token(COMMA);
    rateName = jj_consume_token(IDENTIFIER);
    jj_consume_token(RR);
    symbol = MelaSymbol();
    update = jj_consume_token(IDENTIFIER);
    jj_consume_token(LR);
    updateloc = UpdateLoc();
    jj_consume_token(RR);
Double rate = ParamManager.getParamValue(rateName.image);
      NoInfAction ac = new NoInfAction(name.image, rate, symbol.image, update.image + "(" + updateloc.image + ")");
      agent.addAction(ac);
  }

  final public void InfAction(Agent agent) throws ParseException, NumberFormatException, RuntimeException, ParseException {Token infset=null, name = null, rate = null, symbolInf =null, update, updatelocInf=null;
    jj_consume_token(DASH);
    jj_consume_token(RANG);
    jj_consume_token(LBRAC);
    infset = InfSet();
    jj_consume_token(RBRAC);
    jj_consume_token(LR);
    name = jj_consume_token(IDENTIFIER);
    jj_consume_token(COMMA);
    rate = jj_consume_token(IDENTIFIER);
    jj_consume_token(RR);
    symbolInf = MelaSymbol();
    update = jj_consume_token(IDENTIFIER);
    jj_consume_token(LR);
    updatelocInf = UpdateLoc();
    jj_consume_token(RR);
Double rateValue = ParamManager.getParamValue(rate.image);
     InfAction ac = new InfAction(name.image, rateValue, infset.image, symbolInf.image, update.image + "(" + updatelocInf.image + ")");
     agent.addAction(ac);
  }

  final public void PassAction(Agent agent) throws ParseException, NumberFormatException, RuntimeException, ParseException {Token name = null, probName = null, update, updatelocPass=null, symbolPass=null;
    jj_consume_token(LANG);
    jj_consume_token(DASH);
    jj_consume_token(LR);
    name = jj_consume_token(IDENTIFIER);
    jj_consume_token(COMMA);
    probName = jj_consume_token(IDENTIFIER);
    jj_consume_token(RR);
    symbolPass = MelaSymbol();
    update = jj_consume_token(IDENTIFIER);
    jj_consume_token(LR);
    updatelocPass = UpdateLoc();
    jj_consume_token(RR);
Double prob = ParamManager.getParamValue(probName.image);
     PassAction ac = new PassAction(name.image, prob, symbolPass.image, update.image + "(" + updatelocPass.image + ")");
     agent.addAction(ac);
  }

  final public void EnvAction(Agent agent) throws ParseException, NumberFormatException, RuntimeException, ParseException {Token infsetE=null, name=null, rateName=null, update;
    jj_consume_token(DASH);
    jj_consume_token(RANG);
    jj_consume_token(LBRAC);
    infsetE = InfSet();
    jj_consume_token(RBRAC);
    jj_consume_token(LR);
    name = jj_consume_token(IDENTIFIER);
    jj_consume_token(COMMA);
    rateName = jj_consume_token(IDENTIFIER);
    jj_consume_token(RR);
    jj_consume_token(DOT);
    update = jj_consume_token(IDENTIFIER);
Double rate = ParamManager.getParamValue(rateName.image);
    EnvAction ac = new EnvAction(name.image, rate, infsetE.image, update.image);
    agent.addAction(ac);
  }

  final public void Init() throws ParseException, NumberFormatException, RuntimeException, ParseException {
    InitAgent();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PARALLEL:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_7;
      }
      jj_consume_token(PARALLEL);
      InitAgent();
    }
  }

  final public void InitAgent() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token t,x=null,y=null,z=null,n =null;
    t = jj_consume_token(IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LR:{
      jj_consume_token(LR);
      x = jj_consume_token(INT);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        jj_consume_token(COMMA);
        y = jj_consume_token(INT);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          jj_consume_token(COMMA);
          z = jj_consume_token(INT);
          break;
          }
        default:
          jj_la1[15] = jj_gen;
          ;
        }
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        ;
      }
      jj_consume_token(RR);
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      ;
    }
    jj_consume_token(LSQ);
    n = jj_consume_token(INT);
    jj_consume_token(RSQ);
if (x.image == null)
  {int InitNum = Integer.parseInt(n.image);
    AgentManager.GlobalMatrix[AgentManager.MatrixAgent.get(t.image)][LocationManager.MatrixLoc.get(LocationManager.createListZero())] = InitNum;
  }else{
    if (LocationManager.SpatialSt == "Graph") {
     int InitNum = Integer.parseInt(n.image);
     int xValue = Integer.parseInt(x.image);
         AgentManager.GlobalMatrix[AgentManager.MatrixAgent.get(t.image)][LocationManager.MatrixLoc.get(LocationManager.createListOneD(xValue))]= InitNum;
        }
        else if (LocationManager.SpatialSt.matches("(?i).*OneD*")) {
    int InitNum = Integer.parseInt(n.image);
    int xValue = Integer.parseInt(x.image);
        AgentManager.GlobalMatrix[AgentManager.MatrixAgent.get(t.image)][LocationManager.MatrixLoc.get(LocationManager.createListOneD(xValue))]= InitNum;
        }
        else if (LocationManager.SpatialSt.matches("(?i).*TwoD*")) {
    int InitNum = Integer.parseInt(n.image);
    int xValue = Integer.parseInt(x.image);
    int yValue = Integer.parseInt(y.image);
    AgentManager.GlobalMatrix[AgentManager.MatrixAgent.get(t.image)][LocationManager.MatrixLoc.get(LocationManager.createListTwoD(xValue, yValue))]= InitNum;
        }
        else if (LocationManager.SpatialSt.matches("(?i).*ThreeD*")) {
    int InitNum = Integer.parseInt(n.image);
    int xValue = Integer.parseInt(x.image);
    int yValue = Integer.parseInt(y.image);
    int zValue = Integer.parseInt(z.image);
    AgentManager.GlobalMatrix[AgentManager.MatrixAgent.get(t.image)][LocationManager.MatrixLoc.get(LocationManager.createListThreeD(xValue,yValue,zValue))]= InitNum;
    }
  }
  }

  final public void setGraph() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token v, v1, e, e1;
    int BracketsCounter = 0;
    jj_consume_token(VERTICES);
    jj_consume_token(ASSIGN);
    jj_consume_token(LBRAC);
    v = jj_consume_token(INT);
int vertexName = Integer.parseInt(v.image);
    LocationManager.AllLoc.add(Location.createListName(vertexName));
    LocationManager.prepareMap(Location.createListName(vertexName));
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[18] = jj_gen;
        break label_8;
      }
      jj_consume_token(COMMA);
      v1 = jj_consume_token(INT);
int vertexName1 = Integer.parseInt(v.image);
    LocationManager.AllLoc.add(Location.createListName(vertexName1));
    LocationManager.prepareMap(Location.createListName(vertexName1));
    }
    jj_consume_token(RBRAC);
    jj_consume_token(EOL);
    jj_consume_token(EDGES);
    jj_consume_token(ASSIGN);
    jj_consume_token(LBRAC);
BracketsCounter++;
    System.out.println(BracketsCounter);
    jj_consume_token(LBRAC);
BracketsCounter++;
    System.out.println(BracketsCounter);
    e = jj_consume_token(INT);
int edgeName = Integer.parseInt(e.image);
     LocationManager.addNeighNode(Location.createListName(edgeName), BracketsCounter);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        break label_9;
      }
      jj_consume_token(COMMA);
      e1 = jj_consume_token(INT);
int edgeName1 = Integer.parseInt(e1.image);
     LocationManager.addNeighNode(Location.createListName(edgeName1), BracketsCounter);
    }
    jj_consume_token(RBRAC);
BracketsCounter++;
     System.out.println(BracketsCounter);
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[20] = jj_gen;
        break label_10;
      }
      jj_consume_token(COMMA);
      jj_consume_token(LBRAC);
BracketsCounter++;
     System.out.println(BracketsCounter);
      e = jj_consume_token(INT);
int edgeName2 = Integer.parseInt(e.image);
     LocationManager.addNeighNode(Location.createListName(edgeName2), BracketsCounter);
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[21] = jj_gen;
          break label_11;
        }
        jj_consume_token(COMMA);
        e1 = jj_consume_token(INT);
int edgeName3 = Integer.parseInt(e1.image);
     LocationManager.addNeighNode(Location.createListName(edgeName3), BracketsCounter);
      }
      jj_consume_token(RBRAC);
BracketsCounter++;
     System.out.println(BracketsCounter);
    }
    jj_consume_token(RBRAC);
BracketsCounter++;
     System.out.println(BracketsCounter);
    jj_consume_token(EOL);
  }

  final public void setOneD() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token x;
    jj_consume_token(LR);
    x = jj_consume_token(INT);
int xValue = Integer.parseInt(x.image);
   Location.createAllLocOneD(xValue);
    jj_consume_token(RR);
  }

  final public void setTwoD() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token x, y;
    jj_consume_token(LR);
    x = jj_consume_token(INT);
    jj_consume_token(COMMA);
    y = jj_consume_token(INT);
    jj_consume_token(RR);
int xValue = Integer.parseInt(x.image);
    int yValue = Integer.parseInt(y.image);
   Location.createAllLocTwoD(xValue, yValue);
  }

  final public void setThreeD() throws ParseException, NumberFormatException, RuntimeException, ParseException {Token x, y, z;
    jj_consume_token(LR);
    x = jj_consume_token(INT);
    jj_consume_token(COMMA);
    y = jj_consume_token(INT);
    jj_consume_token(COMMA);
    z = jj_consume_token(INT);
    jj_consume_token(RR);
int xValue = Integer.parseInt(x.image);
    int yValue = Integer.parseInt(y.image);
    int zValue = Integer.parseInt(z.image);
    Location.createAllLocThreeD(xValue, yValue, zValue);
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_3R_14()
 {
    if (jj_scan_token(LANG)) return true;
    if (jj_scan_token(DASH)) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_scan_token(KEYWORD_ONED)) return true;
    return false;
  }

  private boolean jj_3R_12()
 {
    if (jj_scan_token(LR)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_13()
 {
    if (jj_scan_token(DASH)) return true;
    if (jj_scan_token(RANG)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_scan_token(KEYWORD_GRAPH)) return true;
    return false;
  }

  private boolean jj_3_7()
 {
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3_6()
 {
    if (jj_3R_13()) return true;
    return false;
  }

  private boolean jj_3_4()
 {
    if (jj_scan_token(KEYWORD_THREED)) return true;
    return false;
  }

  private boolean jj_3_5()
 {
    if (jj_3R_12()) return true;
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_scan_token(KEYWORD_TWOD)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public MELAprog5TokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[22];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,0x0,0x20000000,0x20000000,0x1e00000,0xe000000,0xe000000,0x40000000,0x40000000,0x2000000,0x2000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x100000,0x100000,0x100000,0x10,0x0,0x0,0x0,0x0,0x0,0x30000,0x30000,0x100000,0x100000,0x9010,0x4000,0x800,0x800,0x10,0x800,0x800,0x800,0x800,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[7];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public MELAprog5(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MELAprog5(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MELAprog5TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public MELAprog5(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MELAprog5TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public MELAprog5(MELAprog5TokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(MELAprog5TokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[61];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 22; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 61; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 7; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
