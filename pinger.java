import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import org.omg.CORBA.portable.UnknownException;

public class pinger
{
    static int op;

    public static void main(String[] args) throws IOException {
        //Menu Principal
        Menu();

        // loop + condicional
        while (op != 0){
            if (op == 01){
                Pingar_HS();
                Menu();

            } else if (op == 02){
                Pingar_IP();
                Menu();
            } else if (op == 03){
                Pingar_Csv();
                Menu();
            }else if (op == 04) {
                Pingar_CsvN();
            }else if(op == 05){
                Pingar_AP();
            } else{
                JOptionPane.showMessageDialog(null, "Insira uma opicao valida\n");
                Menu();
            }
        }
    }

    // Menu Principal
    static void Menu(){

        op = Integer.parseInt(JOptionPane.showInputDialog("Escolha a opcao desejada\n"
                +"01 - Pingar por Host name\n"
                +"02 - Pingar por Ip\n"
                +"03 - Pingar por Lista\n"
                +"04 - Pingar por Lista 'por nome'\n"
                +"05 - Pingar AP\n"
                +"\n**** Para fechar digite '0' ****\n"));
    }

    // Modulo Pingar por Nome
    static void Pingar_HS(){
        try{
            String host;
            host = JOptionPane.showInputDialog("Insira o HostName");
            boolean resp = InetAddress.getByName("vpn."+host+".vexwifi.com.br").isReachable(3600);
            JOptionPane.showMessageDialog(null,"HS "+resp);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
        }

    }

    // Modulo Pinga por IP
    static void Pingar_IP(){

        String ip;
        ip = JOptionPane.showInputDialog("Insira o Ip");
        try {
            boolean resp = InetAddress.getByName(""+ip).isReachable(3000);
            JOptionPane.showMessageDialog(null, "HS "+resp);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Pinga por Lista
    static void Pingar_Csv() throws FileNotFoundException, IOException{

        try{
       /*JFileChooser chooser = new JFileChooser();
       chooser.showOpenDialog(chooser);*/
            Scanner file = new Scanner (new FileReader("D:\\IP.txt"));
            while (file.hasNext()){
                String ip = file.next();
                boolean resp = InetAddress.getByName(""+ip).isReachable(3600);


                if (resp == false)  {
                    try (FileWriter ResultadoPing = new FileWriter("D:\\Resultado Ping.csv", true)){
                        ResultadoPing.write(ip+";Host Down"+"\r\n");
                    }

                } else
                {
                    try (FileWriter ResultadoPing = new FileWriter("D:\\Resultado Ping.csv", true)) {
                        ResultadoPing.write(ip+";Host Up"+"\r\n");
                    }
                }
            }

        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Pinga Antena
    static void Pingar_AP() throws FileNotFoundException, UnknownHostException, IOException
    {
        int qnt_ap = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade de APs"));

        while (qnt_ap > 0){
            int port = 40001;
            String ip = "10.100.9.41" ;
            InetSocketAddress byhost = new InetSocketAddress(InetAddress.getByName(ip), port);
            System.out.println("Fuck Off");
            qnt_ap --;
        }
    }
    static void Pingar_CsvN() throws FileNotFoundException, UnknownException, IOException
    {
        try{
            /*JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(chooser);*/
            Scanner file = new Scanner (new FileReader("D:\\IP.txt"));
            while (file.hasNext()){
                String hsn = file.next();
                boolean resp = InetAddress.getByName("vpn."+hsn+".vexwifi.com.br").isReachable(3600);


                if (resp == false)  {
                    try (FileWriter ResultadoPing = new FileWriter("D:\\Resultado Ping.csv", true)){
                        ResultadoPing.write(hsn+";Host Down"+"\r\n");
                    }

                } else
                {
                    try (FileWriter ResultadoPing = new FileWriter("D:\\Resultado Ping.csv", true)) {
                        ResultadoPing.write(hsn+";Host Up"+"\r\n");
                    }
                }
            }

        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
