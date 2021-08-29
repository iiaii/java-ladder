package nextstep.ladder.domain;

import java.util.Objects;

public class Player {

    private static final String VALID_LENGTH = "입력값은 공백을 제외한 %s~%s글자 여야 합니다 -> %s";
    private static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 5;

    private final String name;

    public Player(final String name) {
        requireValidLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void requireValidLength(final String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(VALID_LENGTH, MIN_LENGTH, MAX_LENGTH, name));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
