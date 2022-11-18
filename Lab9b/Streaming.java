import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Streaming {
  public static <T> List<Pair<Integer, T>> encode(Stream<T> stream) {
    return stream
      .map(x -> new Pair<Integer, T>(1, x))
      .collect(
          () -> {
            List<Pair<Integer, T>> res = new ArrayList<>();
            return res;
          },
          (a, b) -> {
            if (a.isEmpty()) {
              a.add(b);
            } else if (!a.get(a.size() - 1).getSnd().equals(b.getSnd())) {
              a.add(b);
            } else {
              Pair<Integer, T> tmpA = a.get(a.size() - 1);
              Pair<Integer, T> tmpB = b;
              a.remove(a.size() - 1);
              a.add(new Pair<>(tmpA.getFst() + tmpB.getFst(), tmpB.getSnd()));
            }
          },
          (c, d) -> {
            Pair<Integer, T> lastC = c.get(c.size() - 1);
            Pair<Integer, T> firstD = d.get(0);
            if (lastC.getSnd().equals(firstD.getSnd())) {
              Pair<Integer, T> newPair = 
                  new Pair<>(lastC.getFst() + firstD.getFst(), firstD.getSnd());
              c.remove(c.size() - 1); 
              c.add(newPair);
              d.remove(0); 
              c.addAll(d); 
            } else {
              c.addAll(d);
            }
          }
        );
  }
}
