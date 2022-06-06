package org.example.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
// @Data
public class Car {

    private String model;
    private int productionYear;
    private int amountOfDoors;
    private int amountOfWheels;

    public Car(String model, int productionYear) {
        this.model = model;
        this.productionYear = productionYear;
    }
}
