package ro.teamnet.hello2;

import ro.teamnet.hello.HelloWorld;

/**
 * Created by Juvie on 29.10.2014.
 */
public class HelloWorldExtend {

    public HelloWorldExtend() {
    }

    public void extendSayHello(){
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
        System.out.println("The new Hello World");
    }

    public static void main(String[] args) {
        HelloWorldExtend hwe = new HelloWorldExtend();
        hwe.extendSayHello();
    }
}