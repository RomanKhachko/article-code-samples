package com.github.romankhachko.articleCodeSamples;

/**
 * Created by roman on 6/1/18.
 */
public class Client {

    public static void main(String[] args) {
        UiInjector.injectUi(driver -> {
            driver.get("http://khachko.blogspot.com/");
            System.out.println(driver.getTitle());
            // do whatever you want with driver here:
            // instantiate page classes, interact with page, configure waits, execute js, etc.)
        });
    }
}
