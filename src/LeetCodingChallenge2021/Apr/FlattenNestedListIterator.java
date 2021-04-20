package LeetCodingChallenge2021.Apr;

import java.util.*;

// AC,速度97%，内存25%，结构非常原始。
// 改就是改成状态机，很明显每次iter基于updateNext的循环，
// 三种分支，找到一个数后修改状态，退出这次循环。
public class FlattenNestedListIterator {
    public static void main(String[] args) {
        List<NestedInteger> data = Arrays.asList(
                makeList(1, 1),
                new NestedInteger(2),
                makeList(1, 1));
        print(new NestedIterator(data));
    }

    public static void print(NestedIterator iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
    }

    public static NestedInteger makeList(int... array) {
        List<NestedInteger> nestedIntegers = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            nestedIntegers.add(new NestedInteger(array[i]));
        }
        return new NestedInteger(nestedIntegers);
    }
}

class NestedIterator implements Iterator<Integer> {
    private Deque<List<NestedInteger>> elementStack;
    private Deque<Integer> counterStack;
    private Integer nextValue;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.elementStack = new ArrayDeque<>();
        this.counterStack = new ArrayDeque<>();
        this.nextValue = null;

        pushStack(nestedList);
        this.updateNext();
    }

    @Override
    public Integer next() {
        Integer current = this.nextValue;
        updateNext();
        return current;
    }

    private void updateNext() {
        Integer counter = this.counterStack.peek();
        if (counter == null) {
            this.nextValue = null;
        } else if (counter == 0) {
            popStack();
            updateNext();
        } else {
            List<NestedInteger> list = elementStack.peek();
            processOne(list.get(list.size() - minusCounter()));
        }
    }

    private void processOne(NestedInteger element) {
        if (element.isInteger()) {
            nextValue = element.getInteger();
        } else {
            pushStack(element.getList());
            updateNext();
        }
    }

    private void popStack() {
        this.counterStack.poll();
        this.elementStack.poll();
    }

    private void pushStack(List<NestedInteger> element) {
        elementStack.push(element);
        counterStack.push(element.size());
    }

    private Integer minusCounter() {
        Integer counter = counterStack.poll();
        counterStack.push(counter - 1);
        return counter;
    }

    @Override
    public boolean hasNext() {
        return this.nextValue != null;
    }
}

// You should not implement it, or speculate about its implementation
class NestedInteger {
    Integer integer;
    List<NestedInteger> list;

    public NestedInteger(List<NestedInteger> list) {
        this.integer = null;
        this.list = list;
    }

    public NestedInteger(Integer integer) {
        this.integer = integer;
        this.list = null;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return integer != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return integer;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }
}