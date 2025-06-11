
Before

```java
List<Car> filteredCars = new ArrayList<>();

for (Car car : cars) {
    for (Wheel wheel : wheels) {
        if (car.getColor() == wheel.getColor() &&
                wheel.isWorking() == true ) {
            filteredCars.add(car);
            break;
        }
    }
}

return filteredCars;
```

after

```java
List<Car> filteredCars =
    cars.stream()
        .filter (
            car -> wheels.stream()
                         .anyMatch(wheel -> wheel.getColor() == car.getColor() &&
                                            wheel.isWorking()))
        .collect(Collectors.toList());
```