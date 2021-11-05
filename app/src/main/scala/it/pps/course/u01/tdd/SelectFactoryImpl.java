package it.pps.course.u01.tdd;

public class SelectFactoryImpl implements SelectFactory{

    @Override
    public SelectStrategy createEvenStrategy() {
        return elem -> elem % 2 == 0;
    }

    @Override
    public SelectStrategy createMultipleStrategy(int multiple) {
        return elem -> multiple % elem == 0;
    }
}
