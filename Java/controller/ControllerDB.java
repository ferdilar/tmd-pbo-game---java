package controller;

import java.io.*;
import model.DB;
import model.ModelSkor;
import model.ModelKata;

public class ControllerDB{
	private String hasil;//var untuk hasil simpanan database skor tertinggi
	private String error;//var untuk menyimpan hasil/pesan error yang didapat dari thread bila eksekusi database erro
	
	private int strKata;//var untuk menampung indeks dari inisial kunci permainan(huruf yg bergerak)
	private int nKata;//var menampung byknya data dalam tkata
	private char inisial;//var menampung karakter inisial kunci permainan
	
	private String[] kata = new String[50];//array menampung database, value field kata dari table tkata
	private char[] hurufDepan = new char[50];//array menampung database, value field hrfDepan  dari table tkata
	private int[] len = new int[50];//array untuk menampung banyaknya panjang string per kata
	
	public ControllerDB(){//kontruktor
		//inisialisasi
		nKata=0;
		strKata=0;
	}
	
	public void getSkor(){
		try{
			ModelSkor Tskor = new ModelSkor();//instansiasi objek dari class ModelSkor
			Tskor.getTskor();//eksekusi query
			
			int count= 0;//var untuk membatasi banyak nilai tertinggi yang diambil
			hasil="<html><h2>";
			while(Tskor.getResult().next() && count<3){//lakukan pengulangan
				String username = Tskor.getResult().getString(2);
				String skor = Tskor.getResult().getString(3);
				//simpan hasil data dalam satu string
				hasil = hasil+username+"&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;"+skor+"<br/>";
				
				count++;
			}	
			hasil=hasil + "</html>";
			Tskor.closeResult();
			Tskor.closeConnection();
		}
		catch(Exception e){
				error = e.toString();//jika proses dalam try gagal
		}
	}
	
	public String getHasil(){
		return this.hasil;//lempar nilai hasil
	}
	
	public String getError(){
		return this.error;//lempar pesan error jika masuk catch
	}
	
	public void addSkorBaru(String username, int skor){//fungsi masuk jika game diFrameMain Game Over
		try{
			ModelSkor Tskor = new ModelSkor();
			Tskor.getTskor();
				boolean status=Tskor.insertTskor(username,skor);//dapatkan status jika eksekusi insert database gagal
				if(status==false){//jika gagal berari username sudah ada sebelumnya krn usrename diatur uniqe
					Tskor.updateTskor(username,skor);//masukkan data melalu update username sebelumnya
				}
			Tskor.closeResult();
			Tskor.closeConnection();
		}
		catch(Exception e){
				error = e.toString();
		}
	}

////////////////////////////////////////////////////////////////////////////////////
		
	public void countKata(){//hitung banyak data, nanti dipakai untuk membatasi nilai random
			int count = 0;
		try{
			ModelKata Tkata = new ModelKata();
			Tkata.getTkata();
			while(Tkata.getResult().next()){
				count++;
			}	
			this.nKata=count;//simpan nilai
			Tkata.closeResult();
			Tkata.closeConnection();
		}
		catch(Exception e){
				error = e.toString();
		}
	}
	
	public int retCountKata(){//kemalikan nilai hasil count jumlah data
		return this.nKata;
	}

	public void getInisial(int id_kata){//dapatkan inisial dari id_kata secara acak
		try{
			ModelKata Tkata = new ModelKata();
			Tkata.getInisial(id_kata);
			while(Tkata.getResult().next()){
				this.inisial = Tkata.getResult().getString("hurufDepan").charAt(0);
			}
			
			Tkata.closeResult();
			Tkata.closeConnection();
		}
		catch(Exception e){
				error = e.toString();
		}
	}
	
	public char retInisial(){
		return this.inisial;
	}

	public void getKata(char inisi){//dapatkan semua data kata dr database table tkata+dapatkan indeks dari inisial(huruf yang bergerak)
		//tujuannya agar ada kata yang memiliki inisial sama dengan huruf yang bergerak
			int count= 0;
		try{
			ModelKata Tkata = new ModelKata();//instansiasi objek dari class ModelKata
			Tkata.getTkata();//dapatkan semua data
			
			while(Tkata.getResult().next()){
				this.hurufDepan[count] = Tkata.getResult().getString(2).charAt(0);//simpan data hurufDepan berdasarkan indeks
				this.kata[count] = Tkata.getResult().getString(3);//simpan data kata berdasarkan indeks
				this.len[count] = this.kata[count].length();//simpan data int berdasarkan indeks
				
				if(this.hurufDepan[count] == inisi){//inisi adalah huruf yang bergerak
					this.strKata=count;//simpan indeks
				}
				count++;
			}	
			Tkata.closeResult();
			Tkata.closeConnection();
		}
		catch(Exception e){
				error = e.toString();
		}
	}
	
	public String retKata(int i){//kembalikan nilai string kata indeks ke-i yang didapat secara random
		return this.kata[i];
	}
	public char retHurufDepan(int i){//kembalikan nilai karakter hurufDepan indeks ke-i yang didapat secara random
		return this.hurufDepan[i];
	}
	public int retLenghtKata(int i){//kembalikan nilai panjang karakter string kata indeks ke-i yang didapat secara random
		return this.len[i];
	}
	
	public int retStrKata(){//kembalikan indeks dari kata huruf yang bergerak
		return this.strKata;
	}

}


				
			