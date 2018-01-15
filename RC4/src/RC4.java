
public class RC4 {
	
	public static void main (String[] args) {
		int [] k = new int [] {1,2,3};
		int [] si = new int [256];
		int sj [] = new int [256];
		int i = 0;
		int j = 0;
		int output;
		
		while (i < 256) {
			si [i] =  i;
			i++;
		}
		
		i = 0;
		while (i <256) {
			j = ((j + i + (k[i % k.length])) % 256);
			si[i] = j;
			sj[i] = i;
			i ++;
		}
		
		i = 0;
		j = 0;
		while (i < 256) {
			i = (i + 1) % 256;
			j = (j + si [i]) % 256;
			si[i] = j;
			sj[j] = i;
			output = (si[i] + sj[j] % 256);
			System.out.println(i + ": " + output);
			i ++;
		}
	}


}
