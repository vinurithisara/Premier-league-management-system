import java.util.Comparator;

public class Comparator3 implements Comparator<PlayedMatch> {

    public int compare(PlayedMatch o1, PlayedMatch o2) {
        if(o1.getDate().compareTo(o2.getDate())<0) {
            return -1;

        }else
        if(o1.getDate().compareTo(o2.getDate())>0){
            return 1;
        }
        return 0;

    }


}

