package Builders;

import Placement.Enemy;

import java.util.Random;

/**
 * Created by evanputnam on 8/18/17.
 */
public class EnemyBuilder {

    private static Random random = new Random();


    private enum EnemyName{
        Skeleton, Goblin, Thief, Human, Elf, Dwarf;

        public static EnemyName getRandomEnemy() {
            return values()[random.nextInt(values().length)];
        }

    }

    private enum BossName{
        Medusa, Dragon, Giant, Homer_Simpson, Potato_Head;

        public static BossName getRandomBoss() {
            return values()[random.nextInt(values().length)];
        }
    }

    private static int getDamageReg(int level){
        return 1+random.nextInt(level+1);
    }

    private static int getDamageBoss(int level){
        return 2+random.nextInt(level+2);
    }


    public Enemy getEnemy(int x, int y, boolean isBoss, int level){
        if(isBoss){
            return new Enemy(BossName.getRandomBoss().toString(), x, y, 0, getDamageBoss(level), 10, true);
        }
        return new Enemy(EnemyName.getRandomEnemy().toString(), x, y, 0, getDamageReg(level), 10, false);
    }



}
