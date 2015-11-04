package net.faintedge.rube.components;

import com.artemis.Component;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 *  Local points on an entity on which things can be attached.
 */
public class Anchors extends Component {

  /**
   * Anchor name -> Anchor (name + local offset from the center of the entity)
   */
  private final Map<String, Anchor> anchors;

  public Anchors(Anchor... anchors) {
    ImmutableMap.Builder<String, Anchor> builder = ImmutableMap.builder();
    for (Anchor anchor : anchors) {
      builder.put(anchor.getName(), anchor);
    }
    this.anchors = builder.build();
  }

  public Anchor getAnchor(String name) {
    return anchors.get(name);
  }
}
