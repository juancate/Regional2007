import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;


public class galou {
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
        //input = new BufferedReader(new FileReader("galou.in"));
        
        while(true) {        	
        	readln();
        	R = nextInt(); C = nextInt();
        	if(R == 0 && C == 0)
        		break;
        	
        	char[][] engine = new char[R][C]; // initial engine
        	char[][] rengine = new char[R][C]; // resulting engine
        	
        	starts = new LinkedList<Point>();        	
        	
        	for(int i = 0; i < R; i++) {
        		String line = readln();
        		for(int j = 0; j < C; j++) {
        			char rch = '.', ch = line.charAt(j);
        			
        			engine[i][j] = ch;
        			
        			// Assign initial resulting char to resulting engine
        			if(ch == 'I') { 
        				rch = '(';
        				starts.addLast(new Point(j, i));
        			}
        			else if(ch == '*') rch = 'F';
        			
        			rengine[i][j] = rch;		
        		}
        	}
        	
        	if(starts.isEmpty()) {
        		System.out.println();
            	for(char[] str : rengine) {
            		System.out.println(str);
            	}
            	continue;
        	}
        	
        	Point s = starts.removeFirst();
        	Node start = new Node(rengine, s.y, s.x);
        	char[][] ans = dfs(start);
        	
        	boolean[][] visited = new boolean[R][C];
        	LinkedList<Point> blocks = new LinkedList<Point>();
        	
        	for(int i = 0; i < R; i++) {
        		for(int j = 0; j < C; j++) {
        			visited[i][j] = true;
        			if(ans[i][j] == '.' || ans[i][j] == 'F')
        				continue;
        			for(int k = 0; k < 6; k++) {
        				int xx = j + dx[k];
        				int yy = i + dy[k];
        				if(valid(xx, yy) && !visited[yy][xx] && ans[yy][xx] != '.' && ans[yy][xx] != 'F' && !validturn(ans[i][j], ans[yy][xx])) {
        					ans[i][j] = 'B';
        					blocks.addLast(new Point(j, i));
        					break;
        				}
        			}
        		}
        	}       	
        	if(!blocks.isEmpty()) {
        		Point p = blocks.removeFirst();
        		start.i = p.y;
        		start.j = p.x;
        		start.engine = ans;
        		
    			ans = dfsBlock(start, blocks);       		        	
        	}
        	System.out.println();
        	for(char[] str : ans) {
        		System.out.println(str);
        	}
        }
    }
    
    static boolean valid(int x, int y) {
    	return ((x >= 0 && x < C) && (y >= 0 && y < R));
    }
    
    static boolean validturn(char a, char b) {
    	if(a == '(' && b == ')')
    		return true;
    	if(a == ')' && b == '(')
    		return true;
    	return false;
    }
    
    static char[][] dfsBlock(Node start, LinkedList<Point> blocks) {
    	LinkedList<Node> g = new LinkedList<Node>();
    	g.addFirst(start);
    	char[][] retengine = start.engine;
    	
    	boolean[][] visited = new boolean[R][C]; // Visited positions
    	LinkedList<Point> cstarts = new LinkedList<Point>(blocks);
    	
    	while(!g.isEmpty()) {
    		Node e = g.removeFirst();
    		    		
    		visited[e.i][e.j] = true;
    		
    		retengine = e.engine;
    		    		
    		for(int i = 0; i < 6; i++) {
    			int xx = e.j + dx[i];
    			int yy = e.i + dy[i];
    			
    			char[][] newengine = e.engine;
    			char mov = newengine[e.i][e.j];
    			
    			if(valid(xx, yy) && !visited[yy][xx]) {
    				if(newengine[yy][xx] != '.' && newengine[yy][xx] != 'F' && mov == 'B') {
    					newengine[yy][xx] = 'B';
        				Node ns = new Node(newengine, yy, xx);
        				g.addFirst(ns);
    				}
    			}
    		}
    		
    		if(g.isEmpty() && !cstarts.isEmpty()) {
    			Point p = cstarts.removeFirst();
    			Node ns = new Node(retengine, p.y, p.x);
    			g.addFirst(ns);
    		}
    	}
    	
    	return retengine;
    }
    
    static char[][] dfs(Node start) {
    	LinkedList<Node> g = new LinkedList<Node>();
    	g.addFirst(start);
    	char[][] retengine = null;
    	boolean[][] visited = new boolean[R][C]; // Visited positions
    	LinkedList<Point> cstarts = new LinkedList<Point>(starts);
    	
    	while(!g.isEmpty()) {
    		Node e = g.removeFirst();
    		
    		visited[e.i][e.j] = true;
    		
    		retengine = e.engine;
    		    		
    		for(int i = 0; i < 6; i++) {
    			int xx = e.j + dx[i];
    			int yy = e.i + dy[i];
    			
    			char[][] newengine = e.engine;
    			char mov = newengine[e.i][e.j];
    			
    			if(valid(xx, yy) && !visited[yy][xx] && newengine[yy][xx] == 'F') {    				
    				char nmov = (mov == ')')? '(' : ')';    		
    				newengine[yy][xx] = nmov;
    				Node ns = new Node(newengine, yy, xx);
    				g.addFirst(ns);
    			}
    		}
    		
    		if(g.isEmpty() && !cstarts.isEmpty()) {
    			Point p = cstarts.removeFirst();
    			Node ns = new Node(retengine, p.y, p.x);
    			g.addFirst(ns);
    		}
    	}
    	
    	return retengine;
    }
       
    static class Node {
    	char[][] engine;
    	int i, j;

		public Node(char[][] engine, int i, int j) {
			this.engine = engine;
			this.i = i;
			this.j = j;
		}
    }
       
    static int R, C;
    static LinkedList<Point> starts;
    static int[] dx = {0, 1, -1, 1, -1, 0};
    static int[] dy = {-1, -1, 0, 0, 1, 1};
}
