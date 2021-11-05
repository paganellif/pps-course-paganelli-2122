package it.pps.course.u01.tdd;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SimpleCircularList implements CircularList {

    private List<Optional<Integer>> circularList;
    private int index;

    public SimpleCircularList(){
        this.circularList = new LinkedList<>();
        this.index = 0;
    }

    @Override
    public void add(int element) {
        circularList.add(Optional.of(element));
    }

    @Override
    public int size() {
        return this.circularList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.circularList.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if (this.isEmpty())
            return Optional.empty();
        else {
            final Optional<Integer> elem = this.circularList.get(this.index);
            this.incrementIndex();
            return elem;
        }
    }

    @Override
    public Optional<Integer> previous() {
        if (this.isEmpty())
            return Optional.empty();
        else {
            final Optional<Integer> elem = this.circularList.get(this.index);
            this.decrementIndex();
            return elem;
        }
    }

    @Override
    public void reset() {
        this.index = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        if (this.isEmpty())
            return Optional.empty();
        else
            this.reset();
            while (!strategy.apply(this.circularList.get(this.index).get())){
                if (this.index == this.size() - 1){
                    this.reset();
                    return Optional.empty();
                }
                this.incrementIndex();
            }
            return this.next();
    }

    private void incrementIndex() {
        if (this.index == (this.size() -1))
            this.index = 0;
        else
            this.index++;
    }

    private void decrementIndex() {
        if (this.index == 0)
            this.index = (this.size() - 1);
        else
            this.index--;
    }
}
