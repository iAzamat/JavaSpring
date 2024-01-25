package ru.geekbrains.myFirstProject;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdEngine implements Engine{
    public ProdEngine() {
        System.out.println("Двигатель запущен на сервере");
    }

    public void go(){
        System.out.println("Поехали быстро!");
    }
}
