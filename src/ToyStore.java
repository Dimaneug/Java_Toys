import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyStore {
    private PriorityQueue<Toy> toyQueue;

    public ToyStore() {
        toyQueue = new PriorityQueue<>(new Comparator<Toy>() {
            @Override
            public int compare(Toy t1, Toy t2) {
                return t2.weight - t1.weight;
            }
        });
    }

    public void put(String toyInfo) {
        String[] parts = toyInfo.split(" ");
        int id = Integer.parseInt(parts[0]);
        int weight = Integer.parseInt(parts[1]);
        String name = parts[2];

        Toy toy = new Toy(id, name, weight);
        toyQueue.offer(toy);
    }

    public int get() {
        Random rand = new Random();
        int randNum = rand.nextInt(100);

        if (randNum < 20) {
            return 1;
        } else if (randNum < 40) {
            return 2;
        } else {
            return 3;
        }
    }

    public void simulateAndGet(int numSimulations) {
        try {
            FileWriter writer = new FileWriter("results.txt");

            for (int i = 0; i < numSimulations; i++) {
                int toyId = get();
                Toy selectedToy = null;

                for (Toy toy : toyQueue) {
                    if (toy.id == toyId) {
                        selectedToy = toy;
                        break;
                    }
                }

                if (selectedToy != null) {
                    writer.write("Выпавшая игрушка: " + selectedToy.name + "\n");
                } else {
                    writer.write("Игрушка не выбрана для ID " + toyId + "\n");
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.put("1 2 конструктор");
        toyStore.put("2 2 робот");
        toyStore.put("3 6 кукла");

        toyStore.simulateAndGet(10);
    }
}