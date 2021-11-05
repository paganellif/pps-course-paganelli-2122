package it.pps.course.u01.tdd;

public interface SelectFactory {

    SelectStrategy createEvenStrategy();

    SelectStrategy createMultipleStrategy(final int multiple);
}
