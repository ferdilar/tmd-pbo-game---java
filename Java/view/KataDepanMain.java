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

public class KataDepanMain implements Runnable{
	Thread th = new Thread (this);
	private JFrame frameMain;
	
	private JLabel arena;
	private JLabel uScore;
	private JLabel lbluScore;
	private JLabel lblhrfDepan;
	
	private int skor;
	private int nKata;//count untuk membatasi random
	private char hrfDepan;//menyimpan inisial
	
	//kata yang akan tampil sebanyak 6 fix.
	private JLabel kata1;//menampung kata
	private JLabel kata2;
	private JLabel kata3;
	private JLabel kata4;
	private JLabel kata5;
	private JLabel kata6;
	private int lkata1;//menampung panjang karakter
	private int lkata2;
	private int lkata3;
	private int lkata4;
	private int lkata5;
	private int lkata6;
	private char ikata1;//menampung inisial
	private char ikata2;
	private char ikata3;
	private char ikata4;
	private char ikata5;
	private char ikata6;
	
	private AudioClip sMove;
	private AudioClip sPoint;
	private AudioClip sEnd;
	
	Random r = new Random();
	String username;//var untuk menyimpan user untuk msk ke DB
	int aksi=0;//var untu getSource input keyboard
	int kec=1000;//var untuk mengatur kecepatan bila press down, kec awal 1000
	boolean keluar=false;//var status input keyboard spasi untuk keluar dr while run
	
	public KataDepanMain(String tname){//menerima nilai username
		username=tname;//simpan di varible nanti dimasukkan ke database
		sMove=Applet.newAudioClip(getClass().getResource("../aset/click.wav"));
		sPoint=Applet.newAudioClip(getClass().getResource("../aset/coin.wav"));
		sEnd=Applet.newAudioClip(getClass().getResource("../aset/charge.wav"));
		
		frameMain=new JFrame(username+" on Duty");
		frameMain.setResizable(false);
		frameMain.setSize(500,500);
		frameMain.setVisible(true);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setLocationRelativeTo(null);
		frameMain.setLayout(null);

		skor=0;//inisialisasi nilai skor awal permainan
		addEks();
	}
	
	public void addEks(){//masukkkan componen dalan interface yang akan tampil
		int ran = 0;//menyimpan nilai random
		boolean status = false;//status apakah kata yang samas dengan hrfDepan sudah ada/belum
		ControllerDB tDatabase = new ControllerDB();
		tDatabase.countKata();
		nKata=tDatabase.retCountKata();//simpan banyak data kata
		ran=r.nextInt(nKata)+1;//lakukan random sebanyak data kata +1 karena mulai dari 0 di database

		tDatabase.getInisial(ran);//dapatkan karakter dari indeks ran
		hrfDepan=tDatabase.retInisial();//ambil nilai karakter

		
		lblhrfDepan=new JLabel("" + hrfDepan + "", SwingConstants.CENTER);
		lblhrfDepan.setFont(new Font("Courier New", Font.BOLD, 30));
		lblhrfDepan.setForeground(Color.GREEN);
		lblhrfDepan.setBackground(Color.BLUE);
		lblhrfDepan.setOpaque(true);
		frameMain.add(lblhrfDepan);
		lblhrfDepan.setBounds(230,20,30,30);

		tDatabase.getKata(hrfDepan);//dapatkan kata semua dari database
		
		ran=r.nextInt(nKata);
		
		
		kata1=new JLabel(tDatabase.retKata(ran), SwingConstants.CENTER);//ambil kata dengan indeks random
		kata1.setFont(new Font("Courier New", Font.BOLD, 30));
		kata1.setForeground(Color.GREEN);
		kata1.setBackground(Color.BLUE);
		kata1.setOpaque(true);
		frameMain.add(kata1);
		kata1.setBounds(r.nextInt(260-20)+20,80,tDatabase.retLenghtKata(ran)*20,30);
		lkata1=tDatabase.retLenghtKata(ran);
		ikata1=tDatabase.retHurufDepan(ran);
		
		//pengecekan untuk memastikan terdapat kata dengan inisial sama dengan huruf bergerak, dengan cek status
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		
		kata2=new JLabel(tDatabase.retKata(ran), SwingConstants.CENTER);//ambil kata dengan indeks random
		kata2.setFont(new Font("Courier New", Font.BOLD, 30));
		kata2.setForeground(Color.GREEN);
		kata2.setBackground(Color.BLUE);
		kata2.setOpaque(true);
		frameMain.add(kata2);
		kata2.setBounds(r.nextInt(260-20)+20,140,tDatabase.retLenghtKata(ran)*20,30);
		lkata2=tDatabase.retLenghtKata(ran);
		ikata2=tDatabase.retHurufDepan(ran);
		
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		
		kata3=new JLabel(tDatabase.retKata(ran), SwingConstants.CENTER);//ambil kata dengan indeks random
		kata3.setFont(new Font("Courier New", Font.BOLD, 30));
		kata3.setForeground(Color.GREEN);
		kata3.setBackground(Color.BLUE);
		kata3.setOpaque(true);
		frameMain.add(kata3);
		kata3.setBounds(r.nextInt(260-20)+20,200,tDatabase.retLenghtKata(ran)*20,30);
		lkata3=tDatabase.retLenghtKata(ran);
		ikata3=tDatabase.retHurufDepan(ran);
		
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		
		kata4=new JLabel(tDatabase.retKata(ran), SwingConstants.CENTER);//ambil kata dengan indeks random
		kata4.setFont(new Font("Courier New", Font.BOLD, 30));
		kata4.setForeground(Color.GREEN);
		kata4.setBackground(Color.BLUE);
		kata4.setOpaque(true);
		frameMain.add(kata4);
		kata4.setBounds(r.nextInt(260-20)+20,260,tDatabase.retLenghtKata(ran)*20,30);
		lkata4=tDatabase.retLenghtKata(ran);
		ikata4=tDatabase.retHurufDepan(ran);
		
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		
		kata5=new JLabel(tDatabase.retKata(ran), SwingConstants.CENTER);//ambil kata dengan indeks random
		kata5.setFont(new Font("Courier New", Font.BOLD, 30));
		kata5.setForeground(Color.GREEN);
		kata5.setBackground(Color.BLUE);
		kata5.setOpaque(true);
		frameMain.add(kata5);
		kata5.setBounds(r.nextInt(260-20)+20,320,tDatabase.retLenghtKata(ran)*20,30);
		lkata5=tDatabase.retLenghtKata(ran);
		ikata5=tDatabase.retHurufDepan(ran);
		
		if(status==false){
			ran=tDatabase.retStrKata();
			status=true;
		}else{
			ran=r.nextInt(nKata);
		}
		
		
		kata6=new JLabel(tDatabase.retKata(ran), SwingConstants.CENTER);//ambil kata dengan indeks random
		kata6.setFont(new Font("Courier New", Font.BOLD, 30));
		kata6.setForeground(Color.GREEN);
		kata6.setBackground(Color.BLUE);
		kata6.setOpaque(true);
		frameMain.add(kata6);
		kata6.setBounds(r.nextInt(260-20)+20,380,tDatabase.retLenghtKata(ran)*20,30);
		lkata6=tDatabase.retLenghtKata(ran);
		ikata6=tDatabase.retHurufDepan(ran);
		
		
		arena=new JLabel();//luas arena hruf bergerak boleh ada, kalau lebih maka game over
		arena.setBackground(Color.GRAY);
		arena.setOpaque(true);
		frameMain.add(arena);
		arena.setBounds(10,10,470,420);
		
		
		lbluScore=new JLabel("Score : ");
		lbluScore.setFont(new Font("", Font.BOLD, 25));
		lbluScore.setOpaque(true);
		frameMain.add(lbluScore);
		lbluScore.setBounds(30,420,150,50);
		
		uScore=new JLabel("" + skor + "");//tampilkan skor
		uScore.setFont(new Font("", Font.BOLD, 25));
		uScore.setOpaque(true);
		frameMain.add(uScore);
		uScore.setBounds(200,420,150,50);
		
		addListener();//tambahkan lisner untuk beberapa komponen dapat menerima aksi user
		th.start();//jalankan 
	}
	
	public void run(){
		while(cekGameOver()==false && keluar==false){//setiap langkah periksa game over, status keluar jika VK_SPACE ditekan
			try {
					if(kec>=200){//berikan batas minimal kecepatan
						th.sleep(kec);
					}else{
						th.sleep(200);
					}
					sMove.play();
				if(aksi==37){//getSource dari left, pindah ke kiri
					//x--
					lblhrfDepan.setBounds(lblhrfDepan.getX()-20,lblhrfDepan.getY(),lblhrfDepan.getWidth(),lblhrfDepan.getHeight());
					aksi=0;
				}else if(aksi==39){//getSource dari right, pindah ke kiri
					//x++
					lblhrfDepan.setBounds(lblhrfDepan.getX()+20,lblhrfDepan.getY(),lblhrfDepan.getWidth(),lblhrfDepan.getHeight());
					aksi=0;
				}else if(aksi==40){//getSource dari down, pindah ke kiri
					//kec
					kec=kec-150;
					aksi=0;
					lblhrfDepan.setBounds(lblhrfDepan.getX(),lblhrfDepan.getY()+20,lblhrfDepan.getWidth(),lblhrfDepan.getHeight());
				}else{//jika tidak ada aksi turun otomatis
					lblhrfDepan.setBounds(lblhrfDepan.getX(),lblhrfDepan.getY()+20,lblhrfDepan.getWidth(),lblhrfDepan.getHeight());
				}
					
			}
			catch (Exception e) {
			}
		}
	}
	
	
	public boolean cekGameOver(){
		//inisialisasi status
		boolean cek=false;//untuk nilai lemparan dari fungsi ini
		boolean draw=false;//cek bila mendapat point
			kata1.setBackground(Color.BLUE);
			kata2.setBackground(Color.BLUE);
			kata3.setBackground(Color.BLUE);
			kata4.setBackground(Color.BLUE);
			kata5.setBackground(Color.BLUE);
			kata6.setBackground(Color.BLUE);
			
			cekIntersects();//cek singgungan untuk merubah warna
			draw=cekDraw();//cek huruf bergerak sepenuhnya dalam kata
		
		if(lblhrfDepan.getY()>380 || lblhrfDepan.getX()<20 || lblhrfDepan.getX()>440){//jika menyetuh dinding "Game Over"
			cek = true;//berhenti pada while di thread, nilai dilempar dari fungsi ini
			lblhrfDepan.setBackground(Color.RED);
			sEnd.play();
			JOptionPane.showMessageDialog(null, "Your Score " + skor + " ","Game Over", JOptionPane.PLAIN_MESSAGE);
			
			//masuk database nilai skor
			inSkorBaru();
			frameMain.dispose();
			new KataDepan();//kembali ke FrameAwal
		}else{//jika tidak menyentuh dinding arena berarti
			if(draw==true){//mungkin berada dalam arena kata maka poin tambah
				//skor tambah
				sPoint.play();
				uScore.setText(String.valueOf(skor));//tambah skor
				newArena();//arena baru
			}else{
				cek = false;//jika tidak berada dalam kata
			}
		}
		
	return cek;//kembalikan nilai cek agar permainan mulai
	}
	
	public boolean cekDraw(){//jika huruf bergerak menyentuh kata dan sepenuhnya berada didalam
		boolean irisan=false;
		irisan=cekKontain(kata1);
		if(irisan==false){
			irisan=cekKontain(kata2);
			if(irisan==false){
				irisan=cekKontain(kata3);
				if(irisan==false){
					irisan=cekKontain(kata4);
					if(irisan==false){
						irisan=cekKontain(kata5);
						if(irisan==false){
							irisan=cekKontain(kata6);
							if(irisan==false){
								irisan=cekKontain(kata6);
							}else{
								if(hrfDepan==ikata6){
									skor = skor + lkata6;
								}else{
									skor = skor - lkata6;
								}
							}
						}else{
							if(hrfDepan==ikata5){
									skor = skor + lkata5;
							}else{
									skor = skor - lkata5;
							}
						}
					}else{
						if(hrfDepan==ikata4){
							skor = skor + lkata4;
						}else{
							skor = skor - lkata4;
						}
					}
				}else{
					if(hrfDepan==ikata3){
						skor = skor + lkata3;
					}else{
						skor = skor - lkata3;
					}
				}
			}else{
				if(hrfDepan==ikata2){
					skor = skor + lkata2;
				}else{
					skor = skor - lkata2;
				}
			}
		}else{
			if(hrfDepan==ikata1){
				skor = skor + lkata1;
			}else{
				skor = skor - lkata1;
			}
		}
		return irisan;
	}
	
	public boolean cekKontain(JLabel kat){
		boolean cek =false;
		//simpan bound dr label dalam ractangel agar bisa menggunakan fungsi contains
		Rectangle rt = kat.getBounds();
		Rectangle rs = lblhrfDepan.getBounds();
		if(rt.contains(rs)){
				cek=true;
				kat.setBackground(Color.MAGENTA);
		}
		return cek;
	}

	public void cekIntersects(){//jika huruf bergerak menyentuh kata tapi tidak sepenuhnya berada didalam
		boolean irisan=false;
		irisan=cekIrisan(kata1);
		if(irisan==true){
			kata1.setBackground(Color.ORANGE);
		}
			irisan=cekIrisan(kata2);
		if(irisan==true){
			kata2.setBackground(Color.ORANGE);
		}
			irisan=cekIrisan(kata3);
		if(irisan==true){
			kata3.setBackground(Color.ORANGE);
		}
			irisan=cekIrisan(kata4);
		if(irisan==true){
			kata4.setBackground(Color.ORANGE);
		}
			irisan=cekIrisan(kata5);
		if(irisan==true){
			kata5.setBackground(Color.ORANGE);
		}	
			irisan=cekIrisan(kata6);
		if(irisan==true){
			kata6.setBackground(Color.ORANGE);
		}
	}
	
	public boolean cekIrisan(JLabel kat){
		boolean cek =false;
		//simpan bound dr label dalam ractangel agar bisa menggunakan fungsi intersects
		Rectangle rt = kat.getBounds();
		Rectangle rs = lblhrfDepan.getBounds();
		if(rt.intersects(rs)){
				cek=true;
				kat.setBackground(Color.MAGENTA);
		}
		return cek;
	}
	
	public void newArena(){//inisialisasi kembali aren
		int ran = 0;//menyimpan nilai random
		boolean status = false;//status apakah kata yang samas dengan hrfDepan sudah ada/belum
		ControllerDB tDatabase = new ControllerDB();
		tDatabase.countKata();
		nKata=tDatabase.retCountKata();
		ran=r.nextInt(nKata)+1;

		tDatabase.getInisial(ran);
		hrfDepan=tDatabase.retInisial();

		tDatabase.getKata(hrfDepan);
		
		lblhrfDepan.setText("" + hrfDepan + "");
		// lblhrfDepan.setAlignmentX(SwingConstants.CENTER);
		lblhrfDepan.setBounds(230,20,30,30);
		
		ran=r.nextInt(nKata);
		
		kata1.setText(tDatabase.retKata(ran));
		kata1.setBounds(r.nextInt(260-20)+20,80,tDatabase.retLenghtKata(ran)*20,30);
		lkata1=tDatabase.retLenghtKata(ran);
		ikata1=tDatabase.retHurufDepan(ran);
		
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		kata2.setText(tDatabase.retKata(ran));
		kata2.setBounds(r.nextInt(260-20)+20,140,tDatabase.retLenghtKata(ran)*20,30);
		lkata2=tDatabase.retLenghtKata(ran);
		ikata2=tDatabase.retHurufDepan(ran);
		
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		kata3.setText(tDatabase.retKata(ran));
		kata3.setBounds(r.nextInt(260-20)+20,200,tDatabase.retLenghtKata(ran)*20,30);
		lkata3=tDatabase.retLenghtKata(ran);
		ikata3=tDatabase.retHurufDepan(ran);
		
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		kata4.setText(tDatabase.retKata(ran));
		kata4.setBounds(r.nextInt(260-20)+20,260,tDatabase.retLenghtKata(ran)*20,30);
		lkata4=tDatabase.retLenghtKata(ran);
		ikata4=tDatabase.retHurufDepan(ran);
		
		if(tDatabase.retHurufDepan(ran)==hrfDepan && status==false){
			status=true;
		}
		ran=r.nextInt(nKata);
		
		kata5.setText(tDatabase.retKata(ran));
		kata5.setBounds(r.nextInt(260-20)+20,320,tDatabase.retLenghtKata(ran)*20,30);
		lkata5=tDatabase.retLenghtKata(ran);
		ikata5=tDatabase.retHurufDepan(ran);
		
		if(status==false){
			ran=tDatabase.retStrKata();
			status=true;
		}else{
		ran=r.nextInt(nKata);
		}
		
		kata6.setText(tDatabase.retKata(ran));
		kata6.setBounds(r.nextInt(260-20)+20,380,tDatabase.retLenghtKata(ran)*20,30);
		lkata6=tDatabase.retLenghtKata(ran);
		ikata6=tDatabase.retHurufDepan(ran);
		
		kata1.setBackground(Color.BLUE);
		kata2.setBackground(Color.BLUE);
		kata3.setBackground(Color.BLUE);
		kata4.setBackground(Color.BLUE);
		kata5.setBackground(Color.BLUE);
		kata6.setBackground(Color.BLUE);
	}
	

	public void inSkorBaru(){//jika game over
		ControllerDB tDatabase = new ControllerDB();
		tDatabase.addSkorBaru(username,skor);
	}
	
	public void addListener(){//inisialisasi
		lblhrfDepan.setFocusable(true);
		lblhrfDepan.addKeyListener(new InputKey());//addlistener bedasarkan class InputKey
	}

class InputKey extends KeyAdapter{
	public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE ) {
            // System.out.println(e.getKeyCode());
			// aksi=e.getKeyCode();
			keluar=true;//keluar dari run
			frameMain.dispose();
			new KataDepan();//kembali ke frameAwal
        } 
        else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
			// System.out.println(e.getKeyCode());
            aksi=e.getKeyCode();
        } 
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            // System.out.println(e.getKeyCode());
			aksi=e.getKeyCode();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            // System.out.println(e.getKeyCode());
			aksi=e.getKeyCode();
			
        }
    }
}

}

