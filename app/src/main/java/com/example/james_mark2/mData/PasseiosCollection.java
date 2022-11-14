package com.example.james_mark2.mData;

import java.util.ArrayList;

public class PasseiosCollection {
    public static ArrayList<Passeios> getPasseios() {
        ArrayList<Passeios> passeios = new ArrayList<>();
        Passeios ps = new Passeios();

        //add data

        ps.setNome("Passeio 1");
        ps.setUrl("https://cdn.culturagenial.com/imagens/cristo-redentor-3-cke.jpg");
        passeios.add(ps);

        ps = new Passeios();
        ps.setNome("Passeio 2");
        ps.setUrl("https://h8f7z4t2.stackpathcdn.com/wp-content/uploads/2015/10/pontos-turisticos-em-roma-640x449.jpg");
        passeios.add(ps);

        ps = new Passeios();
        ps.setNome("Passeio 3");
        ps.setUrl("https://classic.exame.com/wp-content/uploads/2016/09/size_960_16_9_taj-mahal7.jpg?quality=70&strip=info&w=960");
        passeios.add(ps);

        ps = new Passeios();
        ps.setNome("Passeio 4");
        ps.setUrl("https://www.remessaonline.com.br/blog/wp-content/uploads/2022/06/pontos-turisticos-mais-visitados-do-mundo-1170x780.jpg.optimal.jpg");
        passeios.add(ps);

        return passeios;
    }
}
