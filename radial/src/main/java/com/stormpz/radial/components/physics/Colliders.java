package com.stormpz.radial.components.physics;

import com.artemis.Component;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Colliders extends Component {

  private final List<Collider> colliders;

  public Colliders(Collider... colliders) {
    this.colliders = ImmutableList.copyOf(colliders);
  }

  public Colliders(Iterable<Collider> colliders) {
    this.colliders = ImmutableList.copyOf(colliders);
  }

  public List<Collider> getColliders() {
    return colliders;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private final List<Collider> colliders = Lists.newArrayList();

    public Builder add(Collider collider) {
      this.colliders.add(collider);
      return this;
    }

    public Colliders build() {
      return new Colliders(colliders);
    }
  }
}
