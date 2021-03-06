public class Enemy {
    private String name;
    private int health;
    private int damage;
    private final double CHANCE_TO_HIT = 0.55;
    private String weaponDrop; //none, low, med, or high
    private static final Enemy[] allBasicEnemies =
            {
                    new Enemy("civilian zombie", 70, 10, "none"),
                    new Enemy("salesperson zombie", 80, 9, "none"),
                    new Enemy("construction worker zombie", 90, 10, "none")
            };
    private static final Enemy[] allMedEnemies =
            {
                    new Enemy("firefighter zombie", 100, 15, "low"),
                    new Enemy("police zombie", 90, 17, "low"),
                    new Enemy("gangster zombie", 85, 20, "low")
            };
    private static final Enemy[] allAdvancedEnemies =
            {
                    new Enemy("soldier zombie", 150, 20, "med"),
                    new Enemy("mutated zombie", 200, 20, "high"),
                    new Enemy("giant zombie", 300, 15, "high")
            };
    private static final Enemy[] humanEnemies =
            {
                    new Enemy("bandit", 100, 20, "low"),
                    new Enemy("scavenger", 125, 10, "low"),
                    new Enemy("rogue", 90, 22, "med")
            };

    public Enemy(String name, int health, int damage, String weaponDrop){
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.weaponDrop = weaponDrop; //none low med high
    }

    public int attack() {
        if(Math.random() > CHANCE_TO_HIT) {
            return 0;
        }
        else{
            int random = (int) (Math.random() * 3) - 2;
            return damage + random;
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health < 0) {
            health = 0;
        }
    }

    public Item dropItem() { //this method is called when the enemy dies and a weapon drops
        if(Math.random() < 0.5 || weaponDrop.equals("none")) { //all enemies that drop weapons have a chance to drop a consumable instead
            return Consumable.generateRandomConsumable();
        }
        else if(weaponDrop.equals("low")) {
            return Weapon.generateRandomLowTierWeapon();
        }
        else if(weaponDrop.equals("med")) {
            return Weapon.generateRandomMedTierWeapon();
        }
        else if(weaponDrop.equals("high")) {
            return Weapon.generateRandomHighTierWeapon();
        }
        return null;
    }

    public static Enemy generateRandomBasicEnemy() {
        Enemy e = allBasicEnemies[(int) (Math.random() * allBasicEnemies.length)];
        return new Enemy(e.name, e.health, e.damage, e.weaponDrop);
    }

    public static Enemy generateRandomMedEnemy() {
        Enemy e = allMedEnemies[(int) (Math.random() * allMedEnemies.length)];
        return new Enemy(e.name, e.health, e.damage, e.weaponDrop);
    }

    public static Enemy generateRandomAdvancedEnemy() {
        Enemy e = allAdvancedEnemies[(int) (Math.random() * allAdvancedEnemies.length)];
        return new Enemy(e.name, e.health, e.damage, e.weaponDrop);
    }

    public static Enemy generateRandomHumanEnemy() {
        return humanEnemies[(int) (Math.random() * humanEnemies.length)];
    }

    public static Enemy generateRandomEnemy(double b, double m, double a) {
        double random = Math.random();
        if(random < b) {
            return generateRandomBasicEnemy();
        }
        if(random < m + b) {
            return generateRandomMedEnemy();
        }
        if(random < m + b + a) {
            return generateRandomAdvancedEnemy();
        }
        return null;
    }

    public void scaleEnemy(double factor) {
        health = (int) (health * factor);
        damage = (int) (damage * factor);

    }
}
