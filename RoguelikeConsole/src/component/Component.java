package component;

import util.LogManager;

import java.util.UUID;

public abstract class Component {

    UUID id = UUID.randomUUID();
    LogManager log = new LogManager(id);

}
