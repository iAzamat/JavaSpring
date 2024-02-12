package com.example.aop1;

import org.springframework.stereotype.Service;

@Service
public class AopService {
    @LoggedExecution
    public void someMethod() {
        System.out.println(" someMethod ");
    }

    public void anotherMethod() {
        System.out.println(" anotherMethod ");
    }

    @LoggedExecution
    public void yetAnotherMethod() {
        System.out.println(" yetAnotherMethod ");
    }

    @LogException
    public void potentiallyFailingMethod() {
        // Some logic that might throw an exception
        if (true) { // example condition
            throw new RuntimeException("Oops, something went wrong!");
        }
    }


}
