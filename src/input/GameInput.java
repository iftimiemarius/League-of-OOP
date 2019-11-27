package input;

import heroes.Hero;
import moves.Move;
import sites.Site;

public final class GameInput {
  private int siteHeight;
  private int siteWidth;
  private Site[][] siteMap;
  private int heroesNumber;
  private Hero[] heroes;
  private int roundsNumber;

  public GameInput(final int siteHeight, final int siteWidth, final Site[][] siteMap,
                   final int heroesNumber, final Hero[] heroes, final int roundsNumber) {
    this.siteHeight = siteHeight;
    this.siteWidth = siteWidth;
    this.siteMap = siteMap;
    this.heroesNumber = heroesNumber;
    this.heroes = heroes;
    this.roundsNumber = roundsNumber;
  }

  public int getSiteHeight() {
    return siteHeight;
  }

  public void setSiteHeight(final int siteHeight) {
    this.siteHeight = siteHeight;
  }

  public int getSiteWidth() {
    return siteWidth;
  }

  public void setSiteWidth(final int siteWidth) {
    this.siteWidth = siteWidth;
  }

  public Site[][] getSiteMap() {
    return siteMap;
  }

  public void setSiteMap(final Site[][] siteMap) {
    this.siteMap = siteMap;
  }

  public int getHeroesNumber() {
    return heroesNumber;
  }

  public void setHeroesNumber(final int heroesNumber) {
    this.heroesNumber = heroesNumber;
  }

  public Hero[] getHeroes() {
    return heroes;
  }

  public void setHeroes(final Hero[] heroes) {
    this.heroes = heroes;
  }

  public int getRoundsNumber() {
    return roundsNumber;
  }

  public void setRoundsNumber(final int roundsNumber) {
    this.roundsNumber = roundsNumber;
  }

  public void run() {
    for (int i = 0; i < this.roundsNumber; i++) {
      for (Hero currentHero : this.heroes) {
        Move currentMove = currentHero.getMoves()[i];
        currentMove.acceptMove(currentHero);
        currentHero.setDamage(0);
        currentHero.setDamageWithoutAmplifier(0);
        if (currentHero.getRoundsLeft() > 0) {
          if (currentHero.getCurrentHp() > 0) {
            currentHero.setCurrentHp(currentHero.getCurrentHp() - currentHero.getOvertimeDamage());
            currentHero.setRoundsLeft(currentHero.getRoundsLeft() - 1);
          }
          if (currentHero.getCurrentHp() < 0) {
            currentHero.setCurrentHp(0);
          }
        }
      }
      for (int j = 0; j < this.heroes.length - 1; j++) {
        Hero firstHero = this.heroes[j];
        if (firstHero.getCurrentHp() > 0) {
          for (int k = j + 1; k < this.heroes.length; k++) {
            Hero secondHero = this.heroes[k];
            if (secondHero.getCurrentHp() > 0) {
              if (firstHero.getPosition().equals(secondHero.getPosition())) {
                int currentRow = firstHero.getPosition().getCurrentRow();
                int currentColumn = firstHero.getPosition().getCurrentColumn();
                Site currentSite = this.siteMap[currentRow][currentColumn];
                firstHero.fight(secondHero, currentSite, i);
                secondHero.fight(firstHero, currentSite, i);
                firstHero.setCurrentHp(firstHero.getCurrentHp() - firstHero.getDamage());
                secondHero.setCurrentHp(secondHero.getCurrentHp() - secondHero.getDamage());
                if (firstHero.getCurrentHp() < 0 && secondHero.getCurrentHp() < 0) {
                  firstHero.setCurrentHp(0);
                  secondHero.setCurrentHp(0);
                }
                if (firstHero.getCurrentHp() > 0 || secondHero.getCurrentHp() > 0) {
                  if (firstHero.getCurrentHp() < 0) {
                    firstHero.setCurrentHp(0);
                    secondHero.setXp(secondHero.getXp() + Math.max(0,
                            200 - (secondHero.getLevel() - firstHero.getLevel()) * 40));
                    while (secondHero.levelUp()) {
                      secondHero.setCurrentHp(secondHero.getMaxHp());
                      // After level up, hp will be 100%
                    }
                  }
                  if (secondHero.getCurrentHp() < 0) {
                    secondHero.setCurrentHp(0);
                    firstHero.setXp(firstHero.getXp() + Math.max(0,
                            200 - (firstHero.getLevel() - secondHero.getLevel()) * 40));
                    while (firstHero.levelUp()) {
                      firstHero.setCurrentHp(firstHero.getMaxHp());
                      // After level up, hp will be 100%
                    }
                  }
                }
              }
//              System.out.println(firstHero.getDamageWithoutRaceAmplifier()[secondHero.getId()]);
//              System.out.println(firstHero.getDamage());
//              System.out.println(secondHero.getDamageWithoutRaceAmplifier()[firstHero.getId()]);
//              System.out.println(secondHero.getDamage());
            }
          }
        }
      }
      System.out.println();
      System.out.println("ROUND " + i);
      for (Hero currentHero : this.heroes) {
        currentHero.setDamage(0);
        currentHero.setDamageWithoutAmplifier(0);
        System.out.println(currentHero.getId() + " " + currentHero.toString());
      }
      System.out.println("ENDROUND");
      System.out.println();
    }
  }
}
