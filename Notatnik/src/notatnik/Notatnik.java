/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notatnik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;


public class Notatnik extends JFrame implements ActionListener{

    JTextArea textArea;
    public Notatnik() {

       
    setTitle("Notatnik");

    Toolkit zestaw = Toolkit.getDefaultToolkit();
    Dimension rozmiarEkranu = zestaw.getScreenSize();
    int szerEkranu = rozmiarEkranu.width;
    int wysEkranu = rozmiarEkranu.height;
    setBounds(szerEkranu / 4, wysEkranu / 4, szerEkranu / 2, wysEkranu / 2);

    setResizable(false);
    JMenuBar pasekMenu = new JMenuBar();
    
    JMenu mPlik = new JMenu("Plik");
        
    JMenuItem otworz = new JMenuItem("Otworz");
	JMenuItem zapisz = new JMenuItem("Zapisz");
	JMenuItem zakoncz = new JMenuItem("Zakoncz");
	otworz.addActionListener(this);
	otworz.setActionCommand("11");
	zapisz.addActionListener(this);
	zapisz.setActionCommand("12");
	zakoncz.addActionListener(this);
	zakoncz.setActionCommand("13");
        
    otworz.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
	zapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        
    mPlik.add(otworz);
    mPlik.add(zapisz);
    mPlik.addSeparator();
    mPlik.add(zakoncz);
    
    mPlik.setMnemonic('P');
    
    //Edycja
    
    JMenu mEdycja = new JMenu("Edycja");
        
    JRadioButtonMenuItem powiekszC = new JRadioButtonMenuItem("Powieksz",true);
	JRadioButtonMenuItem pomniejszC = new JRadioButtonMenuItem("Pomniejsz");
    JMenuItem wyczysc = new JMenuItem("Wyczysc");
        
	ButtonGroup bg = new ButtonGroup();

	bg.add(powiekszC);
	mEdycja.add(powiekszC);
	powiekszC.addActionListener(this);
	powiekszC.setActionCommand("21");
	
	bg.add(pomniejszC);  
    mEdycja.add(pomniejszC);
    pomniejszC.addActionListener(this);
    pomniejszC.setActionCommand("22");
    mEdycja.addSeparator();
    mEdycja.add(wyczysc);
    wyczysc.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
    wyczysc.addActionListener(this);
    wyczysc.setActionCommand("23");
        
        
    mEdycja.setMnemonic('E');
    JMenu mPomoc = new JMenu("Pomoc");
    JMenuItem o_autorze = new JMenuItem("O Autorze");
    mPomoc.add(o_autorze);
    o_autorze.addActionListener(this);
    o_autorze.setActionCommand("31");
    pasekMenu.add(mPlik);
	pasekMenu.add(mEdycja);
    pasekMenu.add(mPomoc);
	
        

    setJMenuBar(pasekMenu);
    textArea = new JTextArea();
    JScrollPane sp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setLayout(new BorderLayout());
    add(sp,BorderLayout.CENTER);
        
    JPanel panelLewy = new JPanel(new FlowLayout(FlowLayout.CENTER));
    Border obramowanieLewy = BorderFactory.createEtchedBorder();
    Border leftBorderTitle = BorderFactory.createTitledBorder(obramowanieLewy,"Wstawki");
    panelLewy.setBorder(leftBorderTitle);
    JButton tytul = new JButton("tytul");
   	JButton podpis = new JButton("podpis");
   	panelLewy.add(tytul);
   	panelLewy.add(podpis);
   	tytul.addActionListener(this);
   	tytul.setActionCommand("41");
   	podpis.addActionListener(this);
   	podpis.setActionCommand("42");
   	
   	JPanel panelSrodkowy = new JPanel(new FlowLayout(FlowLayout.CENTER));
   	Border obramowanieSrodek = BorderFactory.createLineBorder(Color.BLACK);
   	Border centerBorderTittle = BorderFactory.createTitledBorder(obramowanieSrodek, "Kolory czcionki");
   	panelSrodkowy.setBorder(obramowanieSrodek);
   	String[] czKolory = {"czerwona","zielona","niebieska","czarna","biala"};
   	JLabel etyKolory = new JLabel("Kolory:  ");
	JComboBox kolorList = new JComboBox(czKolory);
	kolorList.setSelectedIndex(3);
	panelSrodkowy.add(etyKolory);
	panelSrodkowy.add(kolorList);
	
   	
   	
	JPanel panelPrawy = new JPanel(new FlowLayout(FlowLayout.CENTER));
	Border obramowaniePrawy = BorderFactory.createEtchedBorder();
	Border rightBorderTittle = BorderFactory.createTitledBorder(obramowaniePrawy,"Kolory tła");
	panelPrawy.setBorder(rightBorderTittle);
    JPanel panelPrzyciski = new JPanel(new GridLayout(1,2));
    panelPrzyciski.add(panelLewy);
    panelPrzyciski.add(panelSrodkowy);
	panelPrzyciski.add(panelPrawy);
	
    add(panelPrzyciski,BorderLayout.SOUTH);
   
        
	JRadioButton bi = new JRadioButton("bialy",true);
	JRadioButton zo = new JRadioButton("zolty");
	JRadioButton zi = new JRadioButton("zielony");
        
    bi.addActionListener(this);
	zo.addActionListener(this);
	zi.addActionListener(this);
        
    bi.setActionCommand("61");
	zo.setActionCommand("62");
	zi.setActionCommand("63");

	ButtonGroup bg1 = new ButtonGroup();
        
	bg1.add(bi);
	bg1.add(zo);
	bg1.add(zi);
        
	panelPrawy.add(bi);
	panelPrawy.add(zo);
	panelPrawy.add(zi);
	
	//PPM
	JPopupMenu menuPopUP = new JPopupMenu();
	JMenuItem metal = new JMenuItem("metal look-and-feel");
	menuPopUP.add(metal);
	JMenuItem windows = new JMenuItem("windows look-and-feel");
	menuPopUP.add(windows);
	JMenuItem motif = new JMenuItem("motif look-and-feel");
	menuPopUP.add(motif);
	
        

    }

    public static void main(String[] args) throws Exception {
       
        Notatnik nt = new Notatnik();
        
        nt.setVisible(true);
        nt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Integer.parseInt(e.getActionCommand()))
		{
			case 11:
			{
				JFileChooser pliki = new JFileChooser(".");
				if (JFileChooser.APPROVE_OPTION==pliki.showOpenDialog(this))
					try
					{
						File f=pliki.getSelectedFile();
						setTitle(f.getAbsolutePath()+" Notatnik");
						BufferedReader br = new BufferedReader(new FileReader(f));
						String temp="";
						while (br.ready())
						{
							temp+=br.readLine()+"\n";
						}
						textArea.setText(temp);
					}
					catch (IOException ex)
					{
						System.out.println("Brak pliku");
					}
				break;
			}
			case 12:
			{
				JFileChooser pliki = new JFileChooser(".");
				if (JFileChooser.APPROVE_OPTION==pliki.showSaveDialog(this))
					try
					{
						File f=pliki.getSelectedFile();
						BufferedWriter bw = new BufferedWriter(new FileWriter(f));
						bw.write(textArea.getText());
						bw.flush();
						bw.close();
					}
					catch (IOException ee)
					{
						System.out.println("Problemy z zapisem");
					}
				break;
			}
			case 13:
			{
				break;
			}
			case 22:
			{
				Font f = new Font("Arial",Font.PLAIN,10);
				textArea.setFont(f);
				break;
			}
			case 21:
			{
				Font f = new Font("Arial",Font.PLAIN,18);
				textArea.setFont(f);
				break;
			}
			case 23:
			{
				textArea.setText("");
				break;
			}
			case 31:
			{
				JOptionPane.showMessageDialog(this,"Autor: Michał Mikła");
				break;
			}
			case 41:
			{
				textArea.setText("Szanowny Panie \n\n"+textArea.getText());
				break;
			}		
			case 42:
			{
				textArea.setText(textArea.getText()+"\n\n Z powazaniem");
				break;
			}			
			case 61:
			{
				textArea.setBackground(Color.WHITE);
				break;
			}
			case 62:
			{
				textArea.setBackground(Color.YELLOW);
				break;
			}
			case 63:
			{
				textArea.setBackground(Color.GREEN);
				break;
			}
			case 73:
			{
				
			}
		}
    }
} 
