package com.stormpz.radial.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.stormpz.radial.components.Name;

/**
 * Manages a list of {@link Name}s by name.
 */
public class NameSystem extends EntityProcessingSystem {

  @Wire
  private ComponentMapper<Name> nm;

  private final BiMap<String, Integer> nameToEntityId = HashBiMap.create();

  public NameSystem() {
    super(Aspect.getAspectForAll(Name.class));
  }

  @Override
  protected void inserted(Entity e) {
    Name name = nm.getSafe(e);
    if (name != null) {
      nameToEntityId.put(name.getName(), e.getId());
    }
  }

  @Override
  protected void removed(Entity e) {
    if (nameToEntityId.containsValue(e.getId())) {
      nameToEntityId.inverse().remove(e.getId());
    }
  }

  protected void process(Entity e) {
    // NO-OP
  }

  // @Nullable
  public Integer getEntityId(String name) {
    return nameToEntityId.get(name);
  }
}