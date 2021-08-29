package nextstep.ladder.domain;

public class Result {

    private static final String VALID_LENGTH = "입력값은 공백을 제외한 %s~%s글자 여야 합니다 -> %s";
    private static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 5;

    private final String name;

    public Result(final String name) {
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

}
