import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeSet;

import static java.lang.Math.*;

public class he {
	static BufferedReader input;
    static StringTokenizer _stk;
    
    static String readln() throws IOException {
        String l = input.readLine();
        if(l != null)
            _stk = new StringTokenizer(l, " ");
        return l;
    }
    
    static String next() {
        return _stk.nextToken();
    }
    
    static int nextInt() {
        return Integer.parseInt(next());
    }
    
    static void dbg(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
    
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        input = new BufferedReader(new InputStreamReader(System.in));
        //input = new BufferedReader(new FileReader("he.in"));        
        while(true) {
        	readln();
        	int A = nextInt(), B = nextInt();
        	
        	if(A == 0 && B == 0)
        		break;
        	
        	int minA = 100000000;
        	readln();
        	for(int i = 0; i < A; i++) {
        		minA = min(minA, nextInt());
        	}
        	int[] defense = new int[B];        	
        	readln();
        	for(int i = 0; i < B; i++) {
        		defense[i] = nextInt();
        	}
        	Arrays.sort(defense);
        	int first = defense[0], second = defense[1];
        	
        	if(minA >= first && minA >= second)
        		System.out.println('N');
        	else
        		System.out.println('Y');
        }
    }
}
