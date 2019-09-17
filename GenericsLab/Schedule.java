import java.util.ArrayList;

public class Schedule {
    private ArrayList<Pair<Integer, String>> mySchedule;

    public Schedule() {
        mySchedule = new ArrayList<Pair<Integer, String>>();
    }

    public void addClass(int period, String course) {
        // checking if period has been filled
        boolean periodNotFilled = true;
        for (Pair<Integer, String> each : mySchedule) {
            if (each.getKey() == period) {
                periodNotFilled = false;
            }
        }

        // adding course to period if period available
        if (periodNotFilled) {
            mySchedule.add(new Pair<Integer, String>(period, course));
        }

        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < mySchedule.size()-1; i++) {
                for (int j = i+1; i < mySchedule.size(); j++) {
                    int p1 = mySchedule.get(i).getKey();
                    int p2 = mySchedule.get(j).getKey();

                    if (p1 > p2) {
                        mySchedule.set(i, new Pair<Integer, String>(p2, mySchedule.get(i).getValue()));
                        mySchedule.set(j, new Pair<Integer, String>(p1, mySchedule.get(j).getValue()));
                        sorted = false;
                    }
                }
            }
        }
    }

    public String toString() {
        return "";
    }
}