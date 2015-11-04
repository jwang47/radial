package net.faintedge.rube.components.physics;

/**
 *
 */
public class Material {

  public static Material DEFAULT = new Material(0.95f, 0.6f, 0.5f);

  /** The friction coefficient, usually in the range [0,1]. **/
  public final float friction;

  /** The restitution (elasticity) usually in the range [0,1]. **/
  public final float restitution;

  /** The density, usually in kg/m^2. **/
  public final float density;

  public Material(float friction, float restitution, float density) {
    this.friction = friction;
    this.restitution = restitution;
    this.density = density;
  }

  public float getFriction() {
    return friction;
  }

  public float getRestitution() {
    return restitution;
  }

  public float getDensity() {
    return density;
  }
}
