package com.baeldung.jni;

public class HelloWorldJNI {

    static {
        System.loadLibrary("CHelloWorld");
    }

    public static void main(String[] args) {
        HelloWorldJNI.sayHello();
    }

    // Declare a native method sayHello() that receives no arguments and returns int
    public static native int sayHello();
}