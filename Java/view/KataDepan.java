package view;

import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.*;
import java.util.Random;
import java.applet.Applet;
import java.applet.AudioClip;

import controller.ControllerDB;

public class KataDepan implements ActionListener{//Class KataDepan inheritance dari kelas Actionlistener java
	private JFrame frameAwal;//frame untuk menampikan window java

	private JLabel highScore;//label untuk menampung data highscore dari user
	private JLabel lblhighScore;//title saja
	private JLabel lblusername;//title saja
	
	private int nKata;//byknya kata untuk menampung count dr DB table tkata

	private JTextField tName;//input type untuk masukan username

	private JButton cmdNew;//button untuk memulai permain/masuk ke frameMain
	private JButton cmdExit;//button untuk keluar dari sistem

	private AudioClip sStart;//AudioClip menyimpan audio
	
	String username;//String menyimpan username masukkan user

	public KataDepan(){//panggil user interface frame
		sStart=Applet.newAudioClip(getClass().getResource("../aset/camera1.wav"));//audio
		
		System.out.println("Terimakasih \n oleh : ^_^ FerdilaRahmi(1202315)");
		//inisialisasi frame dengan memberi nilai dari beberapa atribunya yang dibutuhkan
		frameAwal=new JFrame("PERMAINAN TEPAT DEPAN KATA");
		frameAwal.setResizable(false);
		frameAwal.setSize(400,500);
		frameAwal.setVisible(true);
		frameAwal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAwal.setLocationRelativeTo(null);
		frameAwal.setLayout(null);
		
		addAwal();//fungsi mengisi komponen pada frame
		addListenerAwal();//menambahkan aksi pada komponen agar dpt menerima aksi user
	}
	
	public void addAwal(){//masukkkan komponen dalam interface yang akan tampil
		ControllerDB tDatabase = new ControllerDB();//instansiasi class ControlerDB
		tDatabase.getSkor();//dapatkan nilai tertinggi
		
		lblhighScore=new JLabel("High Score", SwingConstants.CENTER);//label menampilkan kata "High Score"
		lblhighScore.setFont(new Font("", Font.BOLD, 25));
		lblhighScore.setOpaque(true);
		frameAwal.add(lblhighScore);
		lblhighScore.setBounds(125,30,150,50);
		
		highScore=new JLabel(tDatabase.getHasil(), SwingConstants.CENTER);//label menampilkan hasil data skor tertinggi
		highScore.setForeground(Color.GRAY);
		highScore.setBackground(Color.ORANGE);
		highScore.setOpaque(true);
		frameAwal.add(highScore);
		highScore.setBounds(100,100,200,200);
		
		lblusername=new JLabel("Username : ");//label menampilkan kata "High Score"
		lblusername.setOpaque(true);
		frameAwal.add(lblusername);
		lblusername.setBounds(100,320,100,30);
		
		tName = new JTextField();//textfield menerima data masukan username dari user
		frameAwal.add(tName);
		tName.setBounds(200,320,100,30);
		
		cmdNew=new JButton("New Game");//button untuk memulai permainan
		frameAwal.add(cmdNew);
		cmdNew.setBounds(50,400,100,30);
		
		cmdExit=new JButton("Exit");//button untuk system exit
		frameAwal.add(cmdExit);
		cmdExit.setBounds(250,400,100,30);
	}
	
	public void addListenerAwal(){//inisialisasi komponen yang dapat menerima aksi
		cmdNew.addActionListener(this);
		cmdExit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){//bekerja ketika menerima aksi
		if(e.getSource()==cmdNew){//jika button new game di klik
			username=tName.getText();//simpan nilai textfield untuk dilempar ke frame selanjutkan, karena frame ini akan di dispose
			if(username.equals("")){//eitzz tapi cek dulu ga boleh kosong textfilednya
				JOptionPane.showMessageDialog(null,"Isikan Nama Pemain dulu!");
			}else{
				sStart.play();//ada bunyi pembuka
				frameAwal.dispose();//dispose frame ini
				new KataDepanMain(username);//masuk ke frameMain dan lempar username
			}
		}else if(e.getSource()==cmdExit){//jika button exit untuk keluar
			System.exit(0);
		}
	}
	
}


