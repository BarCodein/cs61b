import javax.xml.stream.XMLInputFactory;
import java.awt.*;
import edu.princeton.cs.algs4.Picture;
public class SeamCarver {
    Picture picture;
    public SeamCarver(Picture picture){
        this.picture = picture;
    }
    public Picture picture()
    {
        return picture;
    }
    public int width(){
        return picture.width();
    }
    public int height(){
        return picture.height();
    }
    private int[] posHelper(int x,int y){
        int[] pos = new int[]{x-1,x+1,y-1,y+1};
        for (int i=0;i<=1;i++)
            pos[i] = (pos[i]+width())%width();
        for (int i=2;i<=3;i++)
            pos[i] = (pos[i]+height())%height();
        return pos;
    }
    private int gradient(int x1,int y1,int x2,int y2){
        Color pix1 = picture.get(x1,y1);
        Color pix2 = picture.get(x2,y2);
        int r,g,b;
        r = pix1.getRed()-pix2.getRed();
        r = r*r;
        g = pix1.getGreen()-pix2.getGreen();
        g = g*g;
        b = pix1.getBlue()-pix2.getBlue();
        b = b*b;
        return r+g+b;

    }
    public double energy(int x,int y){
        if (x<0 || x>=width() || y<0 || y>=height())
            throw new IndexOutOfBoundsException();
        int x1,x2;
        int y1,y2;
        int[] pos = posHelper(x,y);
        x1 = pos[0];x2 = pos[1];
        y1 = pos[2];y2 = pos[3];
        int dx,dy;
        dx = gradient(x1,y,x2,y);
        dy = gradient(x,y1,x,y2);
        return dx+dy;
    }
    private void costCal(int[][] cost,int index,boolean Horizon){
        if (Horizon){
            cost[index][0] = Math.min(cost[index-1][0],cost[index-1][1])
                    +(int)energy(index,0);
            cost[index][height()-1] = Math.min(cost[index-1][height()-1],cost[index-1][height()-2])
                    +(int)energy(index,height()-1);
            for (int i=1;i<height()-1;i++){
                int energy = (int)energy(index,i);
                int min = Math.min(cost[index-1][i-1],cost[index-1][i]);
                min = Math.min(min,cost[index-1][i+1]);
                cost[index][i] = energy+min;
            }
        }
        else{
            cost[0][index] = Math.min(cost[0][index-1],cost[1][index-1])
                    +(int)energy(0,index);
            cost[width()-1][index] = Math.min(cost[width()-1][index-1],cost[width()-2][index-1])
                    +(int)energy(width()-1,index);
            for (int i=1;i<width()-1;i++){
                int energy = (int)energy(i,index);
                int min = Math.min(cost[i-1][index-1],cost[i][index-1]);
                min = Math.min(min,cost[i+1][index-1]);
                cost[i][index] = energy+min;
            }
        }
    }
    private int findHelper(int[][] cost,int x,int y,Boolean Horizon){
        if (Horizon){
            if (y==0) {
                if (cost[x][y] > cost[x][y + 1])
                    return y+1;
                return y;
            }
            if (y==height()-1){
                if (cost[x][y] > cost[x][y-1])
                    return y-1;
                return y;
            }
            int pos = y;
            if (cost[x][y-1]<cost[x][pos])
                pos = y-1;
            if (cost[x][y+1]<cost[x][pos])
                pos = y+1;
            return pos;
        }
        else{
            if (x==0) {
                if (cost[x][y] > cost[x+1][y])
                    return x+1;
                return x;
            }
            if (x==width()-1){
                if (cost[x][y] > cost[x-1][y])
                    return x-1;
                return x;
            }
            int pos = x;
            if (cost[x-1][y]<cost[pos][y])
                pos = x-1;
            if (cost[x+1][y]<cost[pos][y])
                pos = x+1;
            return pos;
        }
    }
    public int[] findHorizontalSeam(){
        int[][] cost = new int[width()][height()];
        for (int i=0;i<height();i++)
            cost[0][i] = (int)energy(0,i);
        for (int i=1;i<width();i++)
            costCal(cost,i,true);
        int min=cost[width()-1][0],pos=0;
        for (int i=0;i<height();i++){
            if (cost[width()-1][i]<min){
                pos=i;
                min = cost[width()-1][i];
            }
        }
        int[] Seam = new int[width()];
        Seam[width()-1] = pos;
        for (int i=width()-2;i>=0;i--){
            Seam[i] = findHelper(cost,i,Seam[i+1],true);
        }
        return Seam;
    }
    public int[] findVerticalSeam(){
        int[][] cost = new int[width()][height()];
        for (int i=0;i<width();i++)
            cost[i][0] = (int)energy(i,0);
        for (int i=1;i<height();i++)
            costCal(cost,i,false);
        int min=cost[0][height()-1],pos=0;
        for (int i=0;i<width();i++){
            if (cost[i][height()-1]<min){
                pos=i;
                min = cost[i][height()-1];
            }
        }
        int[] Seam = new int[height()];
        Seam[height()-1] = pos;
        for (int i=height()-2;i>=0;i--){
            Seam[i] = findHelper(cost,Seam[i+1],i,false);
        }
        return Seam;
    }
    private Boolean valid(int[] seam){
        for (int i=1;i<seam.length;i++)
            if (Math.abs(seam[i]-seam[i-1])>1)
                return false;
        return true;
    }
    public void removeHorizontalSeam(int[] seam){
        if (!valid(seam) || seam.length!=width())
            throw new IllegalArgumentException();
        this.picture = SeamRemover.removeHorizontalSeam(picture,seam);
    }

    public void removeVerticalSeam(int[] seam){
        if (!valid(seam) || seam.length!=height())
            throw new IllegalArgumentException();
        picture = SeamRemover.removeVerticalSeam(picture,seam);
    }

}
