public class Planet{
    static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    /** constructor of 4 parameters. */
    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
	xxPos = xP; yyPos = yP;
	xxVel = xV; yyVel = yV;
	mass = m; imgFileName = img;
    }
    /** Constructor of an instance. */
    public Planet(Planet p){
	xxPos = p.xxPos; yyPos = p.yyPos; 
        xxVel = p.xxVel; yyVel = p.yyVel;
	mass = p.mass; imgFileName = p.imgFileName;
    }
    /** calculate the distance between two Planets. */
    public double calcDistance(Planet p){
	double xSq = Math.pow(this.xxPos - p.xxPos, 2);
	double ySq = Math.pow(this.yyPos - p.yyPos, 2);
	return Math.sqrt(xSq + ySq);
    }
    /** describe the force exerted on this planet by the given planet.*/
    public double calcForceExertedBy(Planet p){
	return G * this.mass * p.mass / Math.pow(calcDistance(p), 2);
    }
    /** describe the force exerted in the X and Y directions */
    public double calcForceExertedByX(Planet p){
	return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }
    /** Compare two planet. */
    public boolean equals(Planet p){
	boolean result = true;
	if(this.xxPos != p.xxPos) { result = false;}
	else if (this.yyPos != p.yyPos) { result = false;}
	else if (this.xxVel != p.xxVel) { result = false;}
	else if (this.yyVel != p.yyVel) { result = false;}
	return result;
    }
   /** calculate the net X and net Y force exerted by all planets. */
    public double calcNetForceExertedByX(Planet[] allPlanets){
	double sum = 0;
	for(int i=0; i < allPlanets.length; i++){
	    if (! this.equals(allPlanets[i])){
		sum += this.calcForceExertedByX(allPlanets[i]);
	    }
	}
	return sum; 
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double sum = 0;
        for(int i=0; i < allPlanets.length; i++){
            if (! this.equals(allPlanets[i])){
                sum += this.calcForceExertedByY(allPlanets[i]);
            }
        }
        return sum;
    }
    public void update(double dt, double fX, double fY){
	double aX = fX / this.mass;
	double aY = fY / this.mass;
	double xV = this.xxVel + dt * aX;
	double yV = this.yyVel + dt * aY;
	this.xxPos = this.xxPos + dt * xV;
	this.yyPos = this.yyPos + dt * yV;
	this.xxVel = xV; this.yyVel = yV;
    }
    /** Drwaing One Planet */
    public void draw(){
	StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}
