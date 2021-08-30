package nextstep.ladder;

import nextstep.ladder.domain.*;
import nextstep.ladder.dto.LadderElementsDto;
import nextstep.ladder.view.*;

import java.util.stream.Collectors;

public class LadderGame {

    public static void main(String[] args) {
        Players players = new Players(PlayerConsoleInput.askPlayers());
        Results results = new Results(players, ResultConsoleInput.askResults());
        Height height = new Height(HeightConsoleInput.askHeight());
        Ladder ladder = new Ladder(players, height);
        LadderElementsDto playersDto = new LadderElementsDto(players.getPlayerNames());
        LadderElementConsoleOutput.print(playersDto, Player.MAX_LENGTH);
        LadderConsoleOutput.print(ladder.getLadderBarStatus());
        LadderElementsDto resultsDto = new LadderElementsDto(results.getResultNames());
        LadderElementConsoleOutput.print(resultsDto, Result.MAX_LENGTH);
        WonderingPlayers wonderingPlayers;
        do {
            wonderingPlayers = new WonderingPlayers(players, PlayerConsoleInput.askWonderingPlayer());
            ResultConsoleOutput.print(playersDto, players.getPositions(wonderingPlayers)
                    .stream()
                    .map(ladder::startMoving)
                    .map(results::getName)
                    .collect(Collectors.toList()));
        } while (!wonderingPlayers.isEqualSizeTo(players));
    }

}
