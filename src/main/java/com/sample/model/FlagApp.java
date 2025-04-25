package com.sample.model;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.Scanner;

public class FlagApp {
    public static void main(String[] args) {
        try {
            // initializare Drools
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();

            Scanner scanner = new Scanner(System.in);
            String input;

            while (true) {
                System.out.print("Introdu numele unei țări (sau 'exit' pentru a ieși): ");
                input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                KieSession kSession = kContainer.newKieSession("ksession-rules");

                CountryFlag flag = new CountryFlag();
                flag.setCountry(input);

                kSession.insert(flag);
                kSession.fireAllRules();

                System.out.println("Culorile tarii " + input + ": " + flag.getColors());

                kSession.dispose();
            }

            System.out.println("end");

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
