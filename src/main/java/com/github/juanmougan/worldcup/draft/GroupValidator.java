package com.github.juanmougan.worldcup.draft;

import java.util.function.Function;

@FunctionalInterface
public interface GroupValidator {

  Function<Group, Boolean> isValidGroup(final Group group);
}
