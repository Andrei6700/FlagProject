package com.sample.model;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlagApp {

    private static final Map<String, String> COUNTRY_NAME_MAP = new HashMap<>();
    static {
        COUNTRY_NAME_MAP.put("romania",    "Romania");
        COUNTRY_NAME_MAP.put("sweden",     "Sweden");
        COUNTRY_NAME_MAP.put("italy",      "Italy");
        COUNTRY_NAME_MAP.put("ireland",    "Ireland");
        COUNTRY_NAME_MAP.put("u.s.a.",     "U.S.A.");
        COUNTRY_NAME_MAP.put("usa",        "U.S.A.");   
        COUNTRY_NAME_MAP.put("belgium",    "Belgium");
        COUNTRY_NAME_MAP.put("poland",     "Poland");
        COUNTRY_NAME_MAP.put("monaco",     "Monaco");
        COUNTRY_NAME_MAP.put("panama",     "Panama");
        COUNTRY_NAME_MAP.put("greece",     "Greece");
    }

    public static void main(String[] args) {
        try {
            // initiere Drools
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();

            Scanner scanner = new Scanner(System.in);
            String input;

            while (true) {
                System.out.print("Introdu numele unei tari (sau 'exit' pentru a iesi): ");
                input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // case-insensitive
                String key = input.toLowerCase();
                String canonical = COUNTRY_NAME_MAP.get(key);
                if (canonical == null) {
                    System.out.println("Tara \"" + input + "\" nu e in flag-decision-table.xml");
                    continue;
                }

                KieSession kSession = kContainer.newKieSession("ksession-rules");

                CountryFlag flag = new CountryFlag();
                flag.setCountry(canonical);

                kSession.insert(flag);
                kSession.fireAllRules();
                kSession.dispose();

                System.out.println("Culorile tarii: " + canonical + ": " + flag.getColors());
                System.out.println();
            }

            System.out.println("end");
            scanner.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}