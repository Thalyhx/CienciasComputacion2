public class AlgoritmoGenerador{
	static final long m = 2147483647L;
	static final long a1 = 48271L ;
	static final long a2 = 69621L;
	
	static long seed1 = 3L;
	static long seed2 = 8L;
	public static void main (String[] args) {
		generador() ;
	}
	static void generador() {
		for ( int i = 0; i < 19; i ++) {
			long numeroAleatorio = generarValores(19) ;
			System.out.println(numeroAleatorio) ;
		}
	}
  static long generarValores (long limite) {
    seed1 = ( a1 * seed1 ) % m ;
    seed2 = ( a2 * seed2 ) % m ;
    long restaModular = (seed1 - seed2)%(m - 1);
    // Ajuste de signo para corregir resultados negativos o cero
    if ( restaModular <= 0) {
      restaModular += (m - 1) ;
    }
    return ((restaModular - 1) * limite) / (m - 1) ;
  }
}
