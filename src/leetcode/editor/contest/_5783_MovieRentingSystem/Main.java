package leetcode.editor.contest._5783_MovieRentingSystem;

public class Main {
    public static void main(String[] args) {
        MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, new int[][]{{0, 1, 5}, {0, 2, 6}, {0, 3, 7}, {1, 1, 4}, {1, 2, 7}, {2, 1, 5}});
        movieRentingSystem.search(1);
        movieRentingSystem.rent(0, 1);
        movieRentingSystem.rent(1, 2);
        movieRentingSystem.report();
        movieRentingSystem.drop(1, 2);
        movieRentingSystem.search(2);
    }
}
