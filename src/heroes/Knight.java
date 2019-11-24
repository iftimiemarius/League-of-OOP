package heroes;

import abilities.BackstabAbility;
import abilities.DeflectAbility;
import abilities.DrainAbility;
import abilities.ExecuteAbility;
import abilities.FireblastAbility;
import abilities.IgniteAbility;
import abilities.ParalysisAbility;
import abilities.SlamAbility;
import sites.DesertSite;
import sites.LandSite;
import sites.Site;
import sites.VolcanicSite;
import sites.WoodsSite;
import utils.Constants;

public class Knight extends Hero {
  private ExecuteAbility execute = new ExecuteAbility();
  private SlamAbility slam = new SlamAbility();

  @Override
  public final void fight(final Hero hero, final Site site) {
    execute.acceptRaceAmplifier(hero);
    float raceAmplifier = hero.getAmplifier();
    site.acceptSiteAmplifier(this);
    float siteAmplifier = this.getAmplifier();
  }

  @Override
  public final void visitRaceAmplifier(final ExecuteAbility executeAbility) {
    this.setAmplifier(Constants.EXECUTE_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitRaceAmplifier(final SlamAbility slamAbility) {
    this.setAmplifier(Constants.SLAM_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitRaceAmplifier(final FireblastAbility fireblastAbility) {
    this.setAmplifier(Constants.FIREBLAST_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitRaceAmplifier(final IgniteAbility igniteAbility) {
    this.setAmplifier(Constants.IGNITE_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitRaceAmplifier(final BackstabAbility backstabAbility) {
    this.setAmplifier(Constants.BACKSTAB_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitRaceAmplifier(final ParalysisAbility paralysisAbility) {
    this.setAmplifier(Constants.PARALYSIS_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitRaceAmplifier(final DrainAbility drainAbility) {
    this.setAmplifier(Constants.DRAIN_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitRaceAmplifier(final DeflectAbility deflectAbility) {
    this.setAmplifier(Constants.DEFLECT_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitSiteAmplifier(final DesertSite desertSite) {
    this.setAmplifier(Constants.NO_AMPLIFIER);
  }

  @Override
  public final void visitSiteAmplifier(final LandSite landSite) {
    this.setAmplifier(Constants.LAND_SITE_KNIGHT_AMPLIFIER);
  }

  @Override
  public final void visitSiteAmplifier(final VolcanicSite volcanicSite) {
    this.setAmplifier(Constants.NO_AMPLIFIER);
  }

  @Override
  public final void visitSiteAmplifier(final WoodsSite woodsSite) {
    this.setAmplifier(Constants.NO_AMPLIFIER);
  }
}
