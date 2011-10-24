import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class template {
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
        //input = new BufferedReader(new FileReader("template.in"));
        readln();
    }
}
