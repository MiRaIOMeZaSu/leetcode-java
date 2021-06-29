package leetcode.editor.contest._5783_MovieRentingSystem;

import java.util.*;

class Movie implements Comparable {
    int shop;
    int movie;
    int price;
    boolean rend = false;

    Movie(int x, int y, int z) {
        shop = x;
        movie = y;
        price = z;
    }

    @Override
    public int compareTo(Object o) {
        Movie other = (Movie) o;
        if (shop == other.shop && movie == other.movie) {
            return 0;
        }
        if (rend != other.rend) {
            return rend ? 1 : -1;
        }
        if (price != other.price) {
            return price - other.price;
        }
        if (shop != other.shop) {
            return shop - other.shop;
        }
        return movie - other.movie;
    }
}

class MovieRentingSystem {
    HashMap<Integer, TreeSet<Movie>> table;
    HashMap<Integer, HashMap<Integer, Movie>> reflect;
    TreeSet<Movie> rend = new TreeSet<>(new Comparator<Movie>() {
        @Override
        public int compare(Movie o1, Movie o2) {
            Movie O1 = (Movie) o1;
            Movie O2 = (Movie) o2;
            if (O1.shop == O2.shop && O1.movie == O2.movie) {
                return 0;
            }
            if (O1.rend != O2.rend) {
                return O1.rend ? -1 : 1;
            }
            if (O1.price != O2.price) {
                return O1.price - O2.price;
            }
            if (O1.shop != O2.shop) {
                return O1.shop - O2.shop;
            }
            return O1.movie - O2.movie;
        }
    });

    public MovieRentingSystem(int n, int[][] entries) {
        table = new HashMap<>(n);
        reflect = new HashMap<>(n);
        for (int i = 0; i < entries.length; i++) {
            Movie movie = new Movie(entries[i][0], entries[i][1], entries[i][2]);
            if (!table.containsKey(entries[i][1])) {
                TreeSet<Movie> set = new TreeSet<>();
                set.add(movie);
                table.put(entries[i][1], set);
                HashMap<Integer, Movie> map = new HashMap<>();
                map.put(entries[i][0], movie);
                reflect.put(entries[i][1], map);
                continue;
            }
            table.get(entries[i][1]).add(movie);
            reflect.get(entries[i][1]).put(entries[i][0], movie);
        }
    }

    public List<Integer> search(int movie) {
        // 需要获取前5个
        List<Integer> res = new ArrayList<>();
        TreeSet<Movie> movies = table.get(movie);
        if (movies == null || movies.isEmpty()) {
            return res;
        }
        Movie pivot = movies.first();
        Movie curr = pivot;
        while (curr != null && !curr.rend && res.size() < 5) {
            curr = movies.higher(curr);
        }
        return res;
    }

    public void rent(int shop, int movie) {
        Movie target = reflect.get(movie).get(shop);
        rend.remove(target);
        table.get(target.movie).remove(target);
        target.rend = true;
        rend.add(target);
        table.get(target.movie).add(target);
    }

    public void drop(int shop, int movie) {
        Movie target = reflect.get(movie).get(shop);
        rend.remove(target);
        table.get(target.movie).remove(target);
        target.rend = false;
        table.get(target.movie).add(target);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        if (rend.isEmpty()) {
            return res;
        }
        Movie pivot = rend.first();
        Movie curr = pivot;
        while (curr != null && res.size() < 5) {
            if (curr.rend) {
                List<Integer> arr = new ArrayList<>();
                arr.add(curr.shop);
                arr.add(curr.movie);
                res.add(arr);
            } else {
                rend.remove(curr);
            }
            curr = rend.higher(curr);
        }
        return res;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */