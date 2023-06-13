
// ЗАДАНИЕ

// 1. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды 
// (например, в миске 10 еды, а кот пытается покушать 15-20).
// 2. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). 
// Если коту удалось покушать (хватило еды), сытость = true. Считаем, что если коту мало 
// еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт 
// (это сделано для упрощения логики программы).
// 3. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки 
// и потом вывести информацию о сытости котов в консоль.
// 4. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
// Достаточно выполнить первые 2 подпункта задачи.

 public class FeedCats {

    private static int TIME = 0;

    public static void main(String[] args) {

        Cat[] cat = new Cat[3];
        cat[0] = new Cat("Пушок", 110, 3);
        cat[1] = new Cat("Панкрат", 200, 4);
        cat[2] = new Cat("Сёма", 30, 1);
        Plate plate = new Plate(500);
        System.out.println("У Вас есть три кота: " + cat[0].getName() + ", " + cat[1].getName() + " и " + cat[2].getName() + ", которые хотят есть каждые " + cat[0].getSatietyTime() + ", " + cat[1].getSatietyTime() + " и " + cat[2].getSatietyTime() + " час(а) соответственно.");
        System.out.println("Кто-то из них более прожорлив, кто-то менее. Сейчас в миске " + plate.getFood() + " грамм кошачьего корма. Посмотрим насколько его хватит, в данный момент коты очень голодны.\n");

        do {
            for (Cat i : cat) {

                //если кот голоден
                if (i.getSatiety() == 0) {

                    //если в миске не хватает еды, чтобы накормить кота, она будет добавлена
                    if (!plate.checkFood(i.getAppetite())) {
                        plate.increaseFood();
                    }

                    //кот ест
                    i.eat(plate);
                    System.out.println("Кот " + i.getName() + " съел " + i.getAppetite() + " граммов корма и проголодается через " + (i.getSatiety()) + " часа(ов)");
                }

                //декрементация показателя сытости
                i.setSatiety(i.getSatiety() - 1);
            }
            System.out.println("\nС момента начала кормежки прошел(ло) " + TIME + " час(а). В миске осталось " + plate.getFood() + " граммов корма.\n");
            TIME++;

        } while (TIME <= 24);
    }
}

class Plate {

    private int food;

    int getFood() {
        return food;
    }

    Plate(int food) {
        this.food = food;
    }

    void decreaseFood(int n) {
        food -= n;
    }

    void increaseFood() {
        this.food += 500;
        System.out.println("В миску добавили 500 грамм корма");
    }

    boolean checkFood(int n) {
        return (food - n) >= 0;
    }

}

class Cat {

    private String name;
    private int appetite;
    private int satietyTime;
    private int satiety;

    String getName() {
        return name;
    }

    int getAppetite() {
        return appetite;
    }

    int getSatietyTime() {
        return satietyTime;
    }

    int getSatiety() {
        return satiety;
    }

    void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    Cat(String name, int appetite, int satietyTime) {

        this.name = name;
        this.appetite = appetite;
        this.satietyTime = satietyTime;
        this.satiety = 0;
    }

    void eat(Plate p) {
            p.decreaseFood(appetite);
            satiety += satietyTime;
    }
}