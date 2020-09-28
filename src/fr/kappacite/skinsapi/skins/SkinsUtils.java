package fr.kappacite.skinsapi.skins;

import fr.kappacite.skinsapi.SkinsAPI;
import fr.kappacite.skinsapi.exceptions.InvalidMojangPlayerException;
import org.bukkit.Bukkit;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SkinsUtils {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_REQUEST = "https://api.mineskin.org/generate/upload";
    private static final String MOJANG_REQUEST = "https://api.ashcon.app/mojang/v2/user/%s";

    protected static void loadSkins(String player, File image, SkinType skinType){


        long start;

        switch(skinType){

            case IMAGE:

                start = System.currentTimeMillis();

                Bukkit.getScheduler().runTaskAsynchronously(SkinsAPI.getInstance().getPlugin(), (() -> {

                        Connection connection = null;

                        try {
                            connection = Jsoup
                                    .connect(POST_REQUEST)
                                    .userAgent(USER_AGENT)
                                    .method(Connection.Method.POST)
                                    .data("file", image.getName(), new FileInputStream(image))
                                    .ignoreContentType(true)
                                    .ignoreHttpErrors(true)
                                    .timeout(40000);

                            String body = connection.execute().body();
                            JSONObject json = new JSONObject(body);

                            String value = json.getJSONObject("data").getJSONObject("texture").getString("value");
                            String signature = json.getJSONObject("data").getJSONObject("texture").getString("signature");

                            SkinsAPI.getInstance().getSkinsManager().loadedImageSkin.put(image, new Skin(value, signature));
                            SkinsAPI.getInstance().getPlugin().getLogger().info("Image skin load takes " + (System.currentTimeMillis()-start) + " ms!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }));

                break;

            case MOJANG:

                start = System.currentTimeMillis();

                Bukkit.getScheduler().runTaskAsynchronously(SkinsAPI.getInstance().getPlugin(), (() -> {
                    Connection connection = null;

                    try{
                        connection = Jsoup
                                .connect(MOJANG_REQUEST.replace("%s", player))
                                .userAgent(USER_AGENT)
                                .method(Connection.Method.GET)
                                .ignoreContentType(true)
                                .ignoreHttpErrors(true)
                                .timeout(40000);

                        String body = connection.execute().body();
                        JSONObject json = new JSONObject(body);

                        if(json.isNull("textures")){
                            throw new InvalidMojangPlayerException("Player not valid!");
                        }

                        String value = json.getJSONObject("textures").getJSONObject("raw").getString("value");
                        String signature = json.getJSONObject("textures").getJSONObject("raw").getString("signature");

                        SkinsAPI.getInstance().getSkinsManager().loadedMojangSkin.put(player, new Skin(value, signature));
                        SkinsAPI.getInstance().getPlugin().getLogger().info("Mojang skin load takes " + (System.currentTimeMillis()-start) + " ms!");
                    } catch (IOException | InvalidMojangPlayerException e) {
                        e.printStackTrace();
                    }

                }));
                break;


        }



    }

}
