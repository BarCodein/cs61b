public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel,yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double distance = Math.sqrt(dx*dx+dy*dy);
        return distance;
    }
    public double calcForceExertedBy(Planet p){
        double distance = this.calcDistance(p);
        double g_const = 6.67e-11;
        double force = g_const*this.mass*p.mass/distance/distance;
        return force;
    }
    public double calcForceExertedByX(Planet p){
        double force = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        double x_force = force *dx/distance;
        return x_force;
    }
    public double calcForceExertedByY(Planet p){
        double force = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        double y_force = force *dy/distance;
        return y_force;
    }
    public double calcNetForceExertedByX(Planet[] planets){
        double sum = 0;
        for (Planet p : planets){

            if (p==this){
                continue;
            }
            double force = this.calcForceExertedByX(p);
            sum+=force;
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double sum = 0;
        for (Planet p : planets) {
            if (p == this) {
                continue;
            }
            double force = this.calcForceExertedByY(p);
            sum += force;
        }
        return sum;
    }
    public void update(double time,double fx,double fy){
        double ax = fx/this.mass ,ay = fy/this.mass;
        this.xxVel = time * ax + this.xxVel;
        this.yyVel = time * ay + this.yyVel;
        this.xxPos = time * this.xxVel + this.xxPos;
        this.yyPos+= time * this.yyVel;

    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
        StdDraw.show();
    }
}
