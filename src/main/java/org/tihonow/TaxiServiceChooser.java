package org.tihonow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaxiServiceChooser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> taxiServices = new ArrayList<>();
        System.out.println("Введите информацию о сервисах такси (пустая строка для завершения):");
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            taxiServices.add(line);
        }

        System.out.println("Введите требуемые параметры поездки:");
        System.out.print("Время подачи: ");
        int requiredArrivalTime = scanner.nextInt();
        System.out.print("Стоимость: ");
        int requiredCost = scanner.nextInt();
        System.out.print("Время поездки: ");
        int requiredTravelTime = scanner.nextInt();
        System.out.print("Способ оплаты (cash, card, any): ");
        String requiredPaymentMethod = scanner.next();

        try {
            List<String> suitableServices = findSuitableServices(taxiServices, requiredArrivalTime, requiredCost, requiredTravelTime, requiredPaymentMethod);
            System.out.println("Подходящие сервисы: " + suitableServices);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static List<String> findSuitableServices(List<String> taxiServices, int requiredArrivalTime, int requiredCost, int requiredTravelTime, String requiredPaymentMethod) throws Exception {
        List<String> suitableServices = new ArrayList<>();

        for (String service : taxiServices) {
            String[] parts = service.split(", ");
            if (parts.length != 5) {
                throw new Exception("Неверный формат данных");
            }

            String serviceName = parts[0];
            String[] arrivalTimeRange = parts[1].split("-");
            String[] costRange = parts[2].split("-");
            String[] travelTimeRange = parts[3].split("-");
            String paymentMethod = parts[4];

            if (arrivalTimeRange.length != 2 || costRange.length != 2 || travelTimeRange.length != 2) {
                throw new Exception("Неверный формат данных");
            }

            int minArrivalTime = Integer.parseInt(arrivalTimeRange[0]);
            int maxArrivalTime = Integer.parseInt(arrivalTimeRange[1]);
            int minCost = Integer.parseInt(costRange[0]);
            int maxCost = Integer.parseInt(costRange[1]);
            int minTravelTime = Integer.parseInt(travelTimeRange[0]);
            int maxTravelTime = Integer.parseInt(travelTimeRange[1]);

            if (requiredArrivalTime >= minArrivalTime && requiredArrivalTime <= maxArrivalTime &&
                    requiredCost >= minCost && requiredCost <= maxCost &&
                    requiredTravelTime >= minTravelTime && requiredTravelTime <= maxTravelTime &&
                    (paymentMethod.equals("any") || paymentMethod.equals(requiredPaymentMethod) || "any".equals(requiredPaymentMethod))) {
                suitableServices.add(serviceName);
            }

        }

        return suitableServices;
    }
}
