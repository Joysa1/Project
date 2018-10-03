package entity;
import java.util.ArrayList ;
public class Concert extends Event {
    ArrayList <Performer> performers;

    public ArrayList<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(ArrayList<Performer> performers) {
        this.performers = performers;
    }

}
