package oscarblancarte.ipb.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import oscarblancarte.ipb.flyweight.impl.PlayItemFactory;
import oscarblancarte.ipb.flyweight.impl.PlayList;

/**
 * @author Oscar Javier Blancarte Iturralde.
 * @see http://www.oscarblancarteblog.com
 */
public class FlyweightMain {

    private static final String[] songNames = new String[1000];
    private static final String[] playListNames = new String[4100000];
    private static final List<PlayList> playLists = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(""
            + "Proceso de creación de playlist iniciado,este proceso puede\n"
            + "ser muy tardado por la gran cantidad de objetos que se \n"
            + "crearan, por favor espere un momento hasta que se le \n"
            + "notifique que el proceso ha terminado.");
        
        Runtime runtime = Runtime.getRuntime();
        System.out.println("MaxMemory > " + (runtime.maxMemory()/1000000));
        PlayItemFactory.enableFlyweight = true;
        initArrays();
        createRandonPlayList();

        System.out.println("total playlist > " + playLists.size());
        
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory Used => " + (memoryUsed / 1000000));//
    }

    private static void createRandonPlayList() {
        Random random = new Random();
        int p = 0;
        for (int c = 0; c < playListNames.length; c++) {
            PlayList playList = new PlayList(playListNames[c]);
            for (int i = 0; i < 10; i++) {
                int song = random.nextInt(songNames.length);
                playList.addPlayItem(songNames[song]);
            }
            playLists.add(playList);
            if(c!=0 && (c+1)%(playListNames.length/10)==0){
                p+=10;
                System.out.println("Compleado "+ p +"%");
                System.out.println("Listas creadas " + playLists.size());
            }
        }
    }

    private static void initArrays() {
        for (int c = 0; c < songNames.length; c++) {
            songNames[c] = "Song " + (c + 1);
        }

        for (int c = 0; c < playListNames.length; c++) {
            playListNames[c] = "PlayList " + (c + 1);
        }
    }
}