package com.syrisa.player;

import com.syrisa.player.service.PlayerFunc;
import com.syrisa.player.service.PlayerService;
import com.syrisa.utility.NumberGenerate;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PlayerServiceImpl {
    public static List<Player> players = new ArrayList<>();

    private static PlayerService<Player> createPlayer = (player) ->{
        player.setPlayerId(NumberGenerate.generate.numberGenerate(5));
        return player;
    };

    public static List<Player> readPlayerFromFile(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Casper\\Desktop\\WordGame\\players.txt")))) {
            String line = null;
            while ((line = reader.readLine()) !=null) {
            collectPlayer.collect(line);
            }
            Arrays.asList(players).stream().forEach(System.out::println);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return players;
    }


    private static final Function<String,Player> splitFunc = (text) -> {
        String[] array = text.split(",");
        Player player = new Player();
        player.setPlayerName(array[0]);
        player.setPlayerNickName(array[1]);
        return player;
    };
    private static final PlayerFunc<String,List<Player>> collectPlayer = (text) ->{
        players.add(createPlayer.createPlayer(splitFunc.apply(text)));
        return players;
    };


}
