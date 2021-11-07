package it.pps.course.u04;

public class JLamp {

    private boolean state;
    private final java.util.Date firstUse = new java.util.Date();

    public JLamp(final boolean on){
        this.state = on;
        System.out.println("primary constr. of "+this);
    }

    public JLamp(){
        this(false);
        System.out.println("auxiliary constr. of "+this);
    }

    public boolean isOn(){
        return state;
    }

    public void on(){
        state = true;
    }

    public void off(){
        state = false;
    }

    @Override
    public String toString() {
        return "Lamp " + state + " first use " + firstUse +
                " identity " + super.toString();
    }

}