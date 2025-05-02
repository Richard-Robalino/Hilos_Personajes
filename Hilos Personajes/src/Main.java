import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaCarrera();
            }
        });


    }
}

class Hilo implements Runnable{

    Thread t;
    String nombre;
    JLabel personaje;
    JLabel labeFinal;

    public static int lugar;

    public Hilo(String nombre ,JLabel personaje, JLabel labeFinal){
        this.nombre = nombre;
        this.labeFinal=labeFinal;
        this.personaje = personaje;
        t=new Thread(this,nombre);
        t.start();
    }

    @Override
    public void run(){
        int retardo;
        try{
            lugar = 1;
            retardo =(int) (Math.random() * 15)+1;
            labeFinal.setVisible(false);
            personaje.setVisible(true);

            for (int i=50; i<= 500 ;i++){
                personaje.setLocation(i,personaje.getY());
                Thread.sleep(retardo);
            }
            personaje.setVisible(false);
            labeFinal.setVisible(true);
            labeFinal.setText(nombre + " Ha llegado en la pos :" +lugar);
            lugar++;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

class VentanaCarrera extends JFrame{
    public VentanaCarrera(){
        super("Carrera de leyendas");
        JLabel alien, dog, emu, alien_pos, dog_pos, emu_pos;
        JButton botonIniciarCarrera;

        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel =new JPanel();
        panel.setLayout(null);

        Image imagen_alien = new ImageIcon("src/Image/alien-19391_128.gif").getImage();
        ImageIcon Icon_alien =new ImageIcon(imagen_alien.getScaledInstance(50,50,Image.SCALE_DEFAULT));
        alien = new JLabel();
        alien.setIcon(Icon_alien);
        alien.setBounds(50,50,50,50);

        Image imagen_dog = new ImageIcon("src/Image/dog-7011_128.gif").getImage();
        ImageIcon Icon_dog =new ImageIcon(imagen_dog.getScaledInstance(50,50,Image.SCALE_DEFAULT));
        dog = new JLabel();
        dog.setIcon(Icon_dog);
        dog.setBounds(50,100,50,50);

        Image imagen_emu = new ImageIcon("src/Image/emu-14760_128.gif").getImage();
        ImageIcon Icon_emu =new ImageIcon(imagen_emu.getScaledInstance(50,50,Image.SCALE_DEFAULT));
        emu = new JLabel();
        emu.setIcon(Icon_emu);
        emu.setBounds(50,150,50,50);

        alien_pos = new JLabel();
        alien_pos.setBounds(50,50,350,50);

        dog_pos = new JLabel();
        dog_pos.setBounds(50,100,350,50);

        emu_pos = new JLabel();
        emu_pos.setBounds(50,150,350,50);

        botonIniciarCarrera = new JButton("Iniciar Carrera");
        botonIniciarCarrera.setBounds(150,200,150,50);


        botonIniciarCarrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hilo talien = new Hilo("Alien",alien,alien_pos);
                Hilo tdog = new Hilo("Dog",dog,dog_pos);
                Hilo temu = new Hilo("Avestruz",emu,emu_pos);

            }
        });

        panel.add(alien);
        panel.add(alien_pos);
        panel.add(dog);
        panel.add(dog_pos);
        panel.add(emu);
        panel.add(emu_pos);
        panel.add(botonIniciarCarrera);

        add(panel);
        setVisible(true);
    }
}