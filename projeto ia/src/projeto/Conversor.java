package projeto;

public class Conversor {

	public static int binToDec(int[] numBin) {
		int resultado = 0;
		for(int i = numBin.length-1; i >= 0; i-- ) {
			if(numBin[i] == 1) {
				resultado+= Math.pow(2, i);
			}
		}
		return resultado;
	}
	
}
