import cs2030s.fp.Constant;
import cs2030s.fp.Memo;

class And implements Cond {

  private Memo<Cond> lVal;
  private Memo<Cond> rVal;
  
  public And(Cond lVal, Cond rVal) {
    this.lVal = Memo.from(lVal);
    this.rVal = Memo.from(rVal);
  }
  
  @Override
  public boolean eval() {
    Boolean leftEval = this.lVal.get().eval();
    if (!leftEval) {
      return false;
    } else {
      return leftEval && this.rVal.get().eval();
    }
  }
  
  @Override
  public String toString() {
    return "(" + this.lVal + " & " + this.rVal + ")";
  }
  
  @Override
  public Cond neg() {
    return new Or(lVal.get().neg(), rVal.get().neg());
  }
}
