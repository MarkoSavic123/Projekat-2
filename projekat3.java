package projekat3;

import java.util.ArrayList;
import java.util.Scanner;

public class projekat3 {

    static class GameObject {
        int x, y;
        int width, height;

        GameObject(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        boolean intersects(GameObject other) {
            return x < other.x + other.width &&
                   x + width > other.x &&
                   y < other.y + other.height &&
                   y + height > other.y;
        }
    }

    static class Player extends GameObject {
        String name;
        int health;

        Player(String name, int x, int y, int width, int height, int health) {
            super(x, y, width, height);
            this.name = capitalize(name);
            this.health = health;
        }

        static String capitalize(String text) {
            text = text.trim();
            if (text.isEmpty()) return text;
            return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
        }

        void takeDamage(int dmg) {
            health -= dmg;
            if (health < 0) health = 0;
        }

        void printInfo() {
            System.out.println("Player: " + name + " | HP: " + health);
        }
    }

    static class Enemy extends GameObject {
        String type;
        int damage;

        Enemy(String type, int x, int y, int width, int height, int damage) {
            super(x, y, width, height);
            this.type = type;
            this.damage = damage;
        }

        void printInfo() {
            System.out.println("Enemy: " + type + " | Damage: " + damage);
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== SIMPLE GAME ===");
        System.out.print("Enter your player name: ");
        String name = input.nextLine();

        System.out.print("Enter starting health (0–100): ");
        int health = input.nextInt();

        Player player = new Player(name, 10, 5, 20, 20, health);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("orc", 12, 6, 10, 10, 20));
        enemies.add(new Enemy("goblin", 50, 20, 10, 10, 10));

        System.out.println("\n=== GAME START ===");
        player.printInfo();
        System.out.println("Enemies:");
        for (Enemy e : enemies) e.printInfo();

        System.out.println("\n=== CHECKING COLLISIONS ===");
        for (Enemy e : enemies) {
            if (player.intersects(e)) {
                System.out.println(player.name + " was hit by " + e.type + "!");
                player.takeDamage(e.damage);
            }
        }

        System.out.println("\n=== FINAL PLAYER STATE ===");
        player.printInfo();

        input.close();
    }
}
/* Grupa 5
Clanovi grupe:
Almir Pelinkovic 23/133
Marko Savic 23/120
*/