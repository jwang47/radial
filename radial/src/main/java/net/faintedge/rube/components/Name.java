package net.faintedge.rube.components;

import com.artemis.Component;

/**
 *
 */
public class Name extends Component {

  private final String name;

  public Name(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
