package chebanenko;

import lombok.Data;

import java.util.*;

@Data
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

}
