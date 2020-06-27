/** NBody!
 *  @author Shangheng Chen
 */
public class NBody{
    /** Given a file name, it should return a double of the radius. */
   public static double readRadius(String fileName){
	In in = new In(fileName);
	int N = in.readInt();
	double R = in.readDouble();
	return R;
    } 
    /** Given a file name, it should return an array of Planets.*/
    public static Planet[] readPlanets(String fileName){
	In in = new In(fileName);
	int N = in.readInt();
	in.readDouble();
	Planet[] p = new Planet[N]; 
	for(int i = 0; i < N; i++){
	    double xP = in.readDouble();
	    double yP = in.readDouble();
	    double xV = in.readDouble();
	    double yV = in.readDouble();
	    double m = in.readDouble();
	    String fN = in.readString();
	    p[i] = new Planet(xP, yP, xV, yV, m, fN);
	}
	return p;
    }

/** Drawing the Initial Universe State (main)*/
    public static void main(String[] args){
	/** Collecting All Needed Input. */
	double T = Double.parseDouble(args[0]);
	double dt = Double.parseDouble(args[1]);
	String filename = args[2];
	double R = readRadius(filename);
	
	/** Drawing the Background. */
	StdDraw.setScale(-R, R);
	StdDraw.picture(0, 0, "./images/starfield.jpg");

	/**Drawing All the Planets */
	Planet[] P = readPlanets(filename);
	for(int i = 0; i < P.length; i++){
	    P[i].draw();
	}
	
	/** Creating an Animation */
	StdDraw.enableDoubleBuffering();
	double t = 0.0;
	while(true){
	    In in = new In(filename);
	    int N = in.readInt();
	    double[] xForces = new double[N];
	    double[] yForces = new double[N];
	    for(int i = 0; i < N; i++){
		xForces[i] =  P[i].calcNetForceExertedByX(P);
		yForces[i] =  P[i].calcNetForceExertedByY(P);
	    }
	    for(int i = 0; i < N; i++){
		 P[i].update(dt, xForces[i], yForces[i]);
	    }
	    StdDraw.picture(0, 0, "./images/starfield.jpg");
	    for(int i = 0; i < P.length; i++){
                P[i].draw();
            }
	    StdDraw.show();
	    StdDraw.pause(10);
	    t += dt;
	}
	System.Out.printf("%d\n", P.length);
	System.Out.printf("%.2e\n", R);
	for (int i = 0; i < P.length; i++) {
   	    System.Out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  P[i].xxPos, P[i].yyPos, P[i].xxVel,
                  P[i].yyVel, P[i].mass, P[i].imgFileName);   
}
    }
}

