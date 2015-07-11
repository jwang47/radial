package com.stormpz.radial.components.physics;

import com.artemis.Component;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class Joints extends Component {

  private final List<Joint> joints;
  private final Multimap<Class<? extends JointConfig>, Joint> jointsByClass;

  public Joints(Joint... joints) {
    this.joints = ImmutableList.copyOf(joints);
    ImmutableMultimap.Builder<Class<? extends JointConfig>, Joint> builder = ImmutableMultimap.builder();
    for (Joint joint : joints) {
      builder.put(joint.getConfig().getClass(), joint);
    }
    this.jointsByClass = builder.build();
  }

  public Collection<Joint> getJoints(Class<? extends JointConfig> type) {
    // TODO: no generic due to probably needing to copy list
    return jointsByClass.get(type);
  }

  public List<Joint> getJoints() {
    return joints;
  }
}
