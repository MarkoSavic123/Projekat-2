package Projekat2;


public class Player {
    private String name;
    private String type;
    private int x, y;
    private int width, height;
    private int health; // 0–100

    public Player(String name, String type, int x, int y, int width, int height, int health) {
        setName(name);
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = Math.min(100, Math.max(0, health));
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown";
        } else {
            name = name.trim().replaceAll("\\s+", " ");
            String[] words = name.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String w : words) {
                if (w.length() > 0) {
                    sb.append(Character.toUpperCase(w.charAt(0)))
                      .append(w.substring(1).toLowerCase())
                      .append(" ");
                }
            }
            this.name = sb.toString().trim();
        }
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = Math.max(0, Math.min(100, health)); }

    @Override
    public String toString() {
        return "Player [" + name + "] (" + type + ") HP=" + health + " Pos=(" + x + "," + y + ")";
    }
}

public class Enemy {
	
    private String type;
    private int x, y;
    private int width, height;
    private int damage; 

    public Enemy(String type, int x, int y, int width, int height, int damage) {
        setType(type);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setDamage(damage);
    }

    public String getType() { return type; }
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            this.type = "Unknown";
        } else {
            this.type = type.trim().toLowerCase();
        }
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) {
        if (damage < 0) this.damage = 0;
        else if (damage > 100) this.damage = 100;
        else this.damage = damage;
    }

    @Override
    public String toString() {
        return "Enemy(" + type + ") @ (" + x + "," + y + ") " +
               width + "x" + height + " DMG=" + damage;
    }
}

public class Game {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<String> eventLog;

    public Game(Player player) {
        this.player = player;
        this.enemies = new ArrayList<>();
        this.eventLog = new ArrayList<>();
    }

    public boolean checkCollision(Player p, Enemy e) {
        return p.getX() < e.getX() + e.getWidth() &&
               p.getX() + p.getWidth() > e.getX() &&
               p.getY() < e.getY() + e.getHeight() &&
               p.getY() + p.getHeight() > e.getY();
    }

    public void decreaseHealth(Player p, Enemy e) {
        int oldHP = p.getHealth();
        int newHP = oldHP - e.getDamage();
        if (newHP < 0) newHP = 0;
        p.setHealth(newHP);
        String msg = "HIT: " + p.getName() + " by " + e.getType() +
                     " for " + e.getDamage() + " → HP " + oldHP + " -> " + newHP;
        eventLog.add(msg);
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
        eventLog.add("ADDED: " + e.toString());
    }

    public ArrayList<Enemy> findByType(String query) {
        ArrayList<Enemy> found = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.getType().toLowerCase().contains(query.toLowerCase().trim())) {
                found.add(e);
            }
        }
        return found;
    }

    public ArrayList<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> hits = new ArrayList<>();
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) hits.add(e);
        }
        return hits;
    }

    public void resolveCollisions() {
        for (Enemy e : collidingWithPlayer()) {
            decreaseHealth(player, e);
        }
    }

    public void printEventLog() {
        System.out.println("\n=== EVENT LOG ===");
        for (String s : eventLog) System.out.println(s);
    }

    public static void main(String[] args) {

    	Player p1 = new Player("   player   one ", null, 10, 5, 32, 32, 85);


    	Game game = new Game(p1);


    	Enemy e1 = new Enemy("Orc", 12, 6, 16, 16, 20);
        game.addEnemy(e1);


        String enemyString = "Goblin: 12,5,16x16;20";
        String[] parts = enemyString.split(":");
        String type = parts[0].trim();
        String[] rest = parts[1].trim().split("[,x;]");
        int ex = Integer.parseInt(rest[0]);
        int ey = Integer.parseInt(rest[1]);
        int ew = Integer.parseInt(rest[2]);
        int eh = Integer.parseInt(rest[3]);
        int dmg = Integer.parseInt(rest[4]);
        Enemy e2 = new Enemy(type, ex, ey, ew, eh, dmg);
        game.addEnemy(e2);


        System.out.println("=== SVI NEPRIJATELJI ===");
        for (Enemy e : game.enemies) System.out.println(e);


        System.out.println("\n=== PRETRAGA: 'gob' ===");
        for (Enemy e : game.findByType("gob")) System.out.println(e);


        System.out.println("\n=== STANJE IGRACA PRIJE KOLIZIJE ===");
        System.out.println(p1);


        game.resolveCollisions();


        System.out.println("\n=== STANJE IGRACA POSLIJE KOLIZIJE ===");
        System.out.println(p1);

        // Ispis event loga
        game.printEventLog();
    }
}
/*
Ana Vukicevic 24/136
Marko Savic 23/120
Ivan Marvucic 21/054
*/