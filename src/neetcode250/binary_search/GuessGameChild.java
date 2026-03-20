package neetcode250.binary_search;

public  abstract class GuessGameChild {
    static int secretNumber; // The number we're trying to find

    // Simulates the LeetCode API
    int guess(int num) {
        if (num > secretNumber) return -1; // Guess too HIGH
        if (num < secretNumber) return  1; // Guess too LOW
        return 0;                          // Correct!
    }
}