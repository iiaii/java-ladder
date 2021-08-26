package nextstep.ladder.domain;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Line {

    private static final String LESS_THAN_ZERO = "playerCount 값은 0보다 작을 수 없습니다 -> %d";
    private static final int ZERO = 0;
    private static final Random RANDOM = new Random();

    private final List<Point> points;

    public Line(final int playerCount) {
        validatePlayerCount(playerCount);
        this.points = createPoints(playerCount);
    }

    public List<Boolean> getLineBarStatus() {
        return points.stream()
                .map(Point::isRightDirection)
                .limit(points.size() - 1)
                .collect(Collectors.toList());
    }

    private List<Point> createPoints(final int playerCount) {
        AtomicBoolean prevBarInstalled = new AtomicBoolean(false);
        return IntStream.range(0, playerCount)
                .mapToObj(i -> new Point(i, Direction.decide(prevBarInstalled.get(), RANDOM.nextBoolean())))
                .peek(point -> prevBarInstalled.set(point.isRightDirection()))
                .collect(Collectors.toList());
    }

    private void validatePlayerCount(final int playerCount) {
        if (playerCount < ZERO) {
            throw new IllegalArgumentException(String.format(LESS_THAN_ZERO, playerCount));
        }
    }

}
