package exercise.exercise0;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Radu.Hoaghe on 10/29/2014.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it

        ArrayList<Integer> a = new ArrayList<>();

        a.add(1);
        a.add(10);
        a.add(7);
        a.add(13);
        a.add(27);


        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements

        System.out.println("Iterare folosind ListIterator: ");
        for(ListIterator<Integer> i = a.listIterator();i.hasNext();)
        {
            System.out.print(i.next() + " ");
        }


        // TODO Exercise #0 c) Iterate through the list using for loop and print all its elements

        System.out.println("\n + Iterare folosind for clasic");
        for(int i=0;i<a.size();i++)
        {
            System.out.print(a.get(i) + " " );}

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        System.out.println("\n Iterare folosind foreach");
        for(Integer p : a){
            System.out.print(p + " ");
        }
    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method

    new Exercise0().iterateThroughList();
    }
}
