public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int number = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[number];
        for (int i=0;i<number;i++){
            double x,y,vx,vy,m;
            x = in.readDouble();
            y = in.readDouble();
            vx = in.readDouble();
            vy = in.readDouble();
            m = in.readDouble();
            String img = in.readString();
            Planet p = new Planet(x,y,vx,vy,m,img);
            planets[i] = p;
        }
        return planets;
    }
    public static void main(String[] args){
        /**init*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        double[] x_force = new double[planets.length];
        double[] y_force = new double[planets.length];
        double t = 0;
        StdDraw.enableDoubleBuffering();
        while (t<=T){

            for (int i=0;i<planets.length;i++){
                x_force[i] = planets[i].calcNetForceExertedByX(planets);
                y_force[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i=0;i<planets.length;i++){
                planets[i].update(t,x_force[i],y_force[i]);
            }
            t = t+dt;
            /**drawing background*/

            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");

            //***/
            for (Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
