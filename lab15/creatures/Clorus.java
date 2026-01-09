package creatures;

import edu.princeton.cs.introcs.StdRandom;
import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
    private double energy;
    public Clorus(double energy){
        super("clorus");
        this.energy = energy;
    }

    @Override
    public double energy() {
        return energy;
    }

    @Override
    public String name() {
        return super.name();
    }

    @Override
    public void move() {
        energy = energy -.08;
    }

    @Override
    public void stay() {
        energy = energy - .05;
    }

    @Override
    public Creature replicate() {
        energy = energy/2;
        return new Clorus(energy);
    }

    @Override
    public void attack(Creature c) {
        move();
        move();
        energy = energy+ c.energy()*0.5;
        energy = Math.min(3,energy);
    }

    @Override
    public Color color() {
        return new Color(34,0,231);
    }
    private java.util.List<Direction> availableSpace(Map<Direction, Occupant> neighbors){
        List<Direction> list = new ArrayList<>();
        for (Direction d :neighbors.keySet()){
            Occupant o = neighbors.get(d);
            if (o.name().equals("empty"))
                list.add(d);
        }
        return list;
    }
    private java.util.List<Direction> food(Map<Direction, Occupant> neighbors){
        List<Direction> list = new ArrayList<>();
        for (Direction d :neighbors.keySet()){
            Occupant o = neighbors.get(d);
            if (o.name().equals("plip"))
                list.add(d);
        }
        return list;
    }
    /*
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> space = availableSpace(neighbors);
        List<Direction> food = food(neighbors);
        if (space.isEmpty())
            return new Action(Action.ActionType.STAY);
        else if (!food.isEmpty())
            return new Action(Action.ActionType.ATTACK,
                    food.get(StdRandom.uniform(food.size())));
        else if (energy>=1)
            return new Action(Action.ActionType.REPLICATE,
                    space.get(StdRandom.uniform(space.size())));
        return new Action(Action.ActionType.STAY);
    }
    */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> space = availableSpace(neighbors);
        List<Direction> food = food(neighbors);
        //if (energy<=0)
            //return new Action(Action.ActionType.DIE);
        if (energy>=2){
            if (!space.isEmpty())
                return new Action(Action.ActionType.REPLICATE,
                        space.get(StdRandom.uniform(space.size())));
            if (!food.isEmpty())
                return new Action(Action.ActionType.ATTACK_REPLICATE,
                        food.get(StdRandom.uniform(food.size())));
        }
        if (!food.isEmpty())
            return new Action(Action.ActionType.ATTACK,
                    food.get(StdRandom.uniform(food.size())));

        if (space.isEmpty())
            return new Action(Action.ActionType.STAY);
        if (energy>=1)
            return new Action(Action.ActionType.REPLICATE,
                    space.get(StdRandom.uniform(space.size())));
        return new Action(Action.ActionType.MOVE,
                space.get(StdRandom.uniform(space.size())));
    }
}
