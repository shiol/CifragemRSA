import java.util.Random;
import java.lang.Math;

import com.sun.source.tree.WhileLoopTree;

class Main {
  public static void main(String[] args) {
    int p = 23, q = 11; // primos privados escolhidos
    int n = p * q; // público calculado
    int fi = (p - 1) * (q - 1);

    Random r = new Random();
    int e = 17;
    while (!firstCondition(e, fi))
      e = r.nextInt(fi); // público escolhido

    System.out.println("Chave pública: (" + n + ", " + e + ")");

    int d = 13;
    //while (!secondCondition(e, d, fi))
      //d = r.nextInt(100); // privado calculado

    System.out.printf("Chave privada: (%d, %d, %d) \n", p, q, d);

    double m = 2;
    double enc = Math.pow(m, e) % n;
    System.out.printf("Encriptar: %f => %f \n", m, enc);

    double dec = Math.pow(enc, d) % n;
    System.out.printf("Decriptar: %f => %f \n", enc, dec);
  }

  private static boolean isPrimo(int numero) {
    for (int j = 2; j < numero; j++) {
      if (numero % j == 0)
        return false;
    }
    return true;
  }

  private static int mdc(int a, int b) {
    while (b != 0) {
      int r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  private static int mmc(int a, int b) {
    return a * (b / mdc(a, b));
  }

  private static boolean firstCondition(int e, int fi) {
    boolean coprimo = mdc(e, fi) == 1;
    return e > 1 && e < fi && coprimo;
  }

  private static boolean secondCondition(int e, int d, int fi) {
    return (e * d) % fi == 1;
  }
}
