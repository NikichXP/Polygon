package chebanenko;

import java.util.*;

public class Component {

    private int sizeX, sizeY;
    private int posX, posY;
    private int id;
    private static int idGenerator = 0;
    private ArrayList<Component> links;
    private int priority;

    public Component(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.id = ++idGenerator;
        links = new ArrayList<>();
        this.priority = 0;
    }

    public int getPriority() {
        return priority;
    }

    public void addLink(Component c) {
        this.links.add(c);
        this.priority++;
    }

    public void addLink(Component[] c) {
        this.links.addAll(Arrays.asList(c));
        this.priority +=  c.length;
    }

    public ArrayList<Component> getLinks() {
        return links;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
