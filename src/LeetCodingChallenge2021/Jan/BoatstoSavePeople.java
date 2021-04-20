package LeetCodingChallenge2021.Jan;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// AC
public class BoatstoSavePeople {
    @Test
    public void t0() {
        int[] people = {1};
        int limit = 3;
        int expected = 1;
        assertThat(Solution8.numRescueBoats(people, limit), is(expected));
    }

    @Test
    public void t1() {
        int[] people = {1, 2};
        int limit = 3;
        int expected = 1;
        assertThat(Solution8.numRescueBoats(people, limit), is(expected));
    }

    @Test
    public void t2() {
        // 即使最大和最小不够一条船，可以继续向小找到最小和较小一条船。
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        int expected = 3;
        assertThat(Solution8.numRescueBoats(people, limit), is(expected));
    }

    @Test
    public void t3() {
        int[] people = {3, 5, 3, 4};
        int limit = 5;
        int expected = 4;
        assertThat(Solution8.numRescueBoats(people, limit), is(expected));
    }
}

class Solution8 {
    public static int numRescueBoats(int[] people, int limit) {
        int boatNumber = 0;
        int minPointer = 0;
        int bigPointer = people.length - 1;
        int notMatchedFatPeopleCounter = 0;
        Arrays.sort(people);

        boolean isCanFit, twoPointerHasMeet;
        while (1 == 1) {
            twoPointerHasMeet = isTwoPointerHasMeet(minPointer, bigPointer);
            if (twoPointerHasMeet) {
                break;
            } else {
                isCanFit = isLightestAndAFatPeopleCanFitIntoOneBoat(people, minPointer, bigPointer, limit);
                if (isCanFit) {
                    boatNumber += 1;
                    minPointer += 1;
                    bigPointer -= 1;
                    continue;
                } else {
                    // try to pair next fattest people with lightest people.
                    notMatchedFatPeopleCounter += 1;
                    bigPointer -= 1;
                }
            }
        }
        boatNumber += notMatchedFatPeopleCounter;
        boolean remainOnePeople = minPointer == bigPointer;
        if (remainOnePeople) {
            boatNumber += 1;
        }
        return boatNumber;
    }

    private static boolean isLightestAndAFatPeopleCanFitIntoOneBoat(int[] people, int minPointer, int bigPointer, int limit) {
        return people[minPointer] + people[bigPointer] <= limit;
    }

    private static boolean isTwoPointerHasMeet(int minPointer, int bigPointer) {
        return minPointer >= bigPointer;
    }
}