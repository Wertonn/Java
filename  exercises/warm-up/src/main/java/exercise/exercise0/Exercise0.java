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

        ArrayList<String> list = new ArrayList<String>();

        list.add("andrei");
        list.add("adrian");
        list.add("adrian");
        list.add("corvin");

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements


        ListIterator<String> i = list.listIterator();

        while(i.hasNext())
            System.out.println("elemente din lista :" + i.next());




        // TODO Exercise #0 c) Iterate through the list using for loop and print all its elements

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements

    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method

        Exercise0 x = new Exercise0();
        x.iterateThroughList();

    }
}
