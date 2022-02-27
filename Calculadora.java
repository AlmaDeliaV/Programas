import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class Calculadora {
   public static void main(String args []){
       CalculadoraGra cg= new CalculadoraGra();
   }
}

class CalculadoraGra extends JFrame implements ActionListener{
   JButton botones[][];
   static JTextArea pantalla1= new JTextArea();
   JTextField pantalla2= new JTextField();
   
   JPanel panel1 = new JPanel();
   JPanel panel2 = new JPanel();
   JPanel panelF = new JPanel();
   
   Operation op;
   
   public CalculadoraGra(){
       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       this.setResizable(false);
       this.setUndecorated(false);
       setLayout(null);
       panelF.setLayout(null);
       setTitle("Calculator");
       setSize(500,340);
       botones = new JButton[4][5];
       
       armar();
       boton();
              
       setContentPane(panelF);
       setVisible(true);
   }
   
   public void armar(){
   
       panel1.setBounds(10,10,460,40);
       panel2.setBounds(10,60,460,230);
       panelF.setBackground(Color.black);
       panelF.add(panel1);
       panelF.add(panel2);
       
       pantalla1.setEditable(false);
       pantalla2.setEditable(false);
       
       panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
       panel1.add(pantalla1);
       panel1.add(pantalla2);
       
       panel2.setLayout (new GridLayout (4,5,10,10)); 
        
        for(int x=0;x<4;x++){
            for(int y=0;y<5;y++){
               botones[x][y]= new JButton();
            }
        }
        
        botones[0][0].setText("0");
        botones[0][1].setText(".");
        botones[0][2].setText("ans");
        botones[0][3].setText("=");
        botones[0][4].setText("Del");
        botones[1][0].setText("1");
        botones[1][1].setText("2");
        botones[1][2].setText("3");
        botones[1][3].setText("+");
        botones[1][4].setText("-");
        botones[2][0].setText("4");
        botones[2][1].setText("5");
        botones[2][2].setText("6");
        botones[2][3].setText("X");
        botones[2][4].setText("/");
        botones[3][0].setText("7");
        botones[3][1].setText("8");
        botones[3][2].setText("9");
        botones[3][3].setText("Ac");
        botones[3][4].setText("OFF");
        
        for(int x=3;x>=0;x--){
            for(int y=0;y<5;y++){
               panel2.add(botones[x][y]);
            }
        }
   }
   
   public void boton(){
      botones[0][0].setActionCommand("0");
      botones[0][1].setActionCommand(".");
      botones[0][2].setActionCommand("ans");
      botones[0][3].setActionCommand("=");
      botones[0][4].setActionCommand("Del");
      botones[1][0].setActionCommand("1");
      botones[1][1].setActionCommand("2");
      botones[1][2].setActionCommand("3");
      botones[1][3].setActionCommand("+");
      botones[1][4].setActionCommand("-");
      botones[2][0].setActionCommand("4");
      botones[2][1].setActionCommand("5");
      botones[2][2].setActionCommand("6");
      botones[2][3].setActionCommand("X");
      botones[2][4].setActionCommand("/");
      botones[3][0].setActionCommand("7");
      botones[3][1].setActionCommand("8");
      botones[3][2].setActionCommand("9");
      botones[3][3].setActionCommand("AC");
      botones[3][4].setActionCommand("OFF");
      
      
      for(int x=0;x<4;x++){
         for(int y=0;y<5;y++){
            botones[x][y].addActionListener(this);
         }
      }
      
   }
   public void actionPerformed(ActionEvent e){
         switch((e.getActionCommand())){
         case "0":
            pantalla1.append("0");
            break;
         case "." :
            pantalla1.append(".");
            break;
         case "ans":
            pantalla1.append(pantalla2.getText());
            break;
         case "=":
            String cad= pantalla1.getText();
            op= new Operation();
            
            if(op.errorSintaxis(cad)==true){
               float res=op.operacion(cad);
               pantalla1.setText("");
               pantalla2.setText(String.valueOf(res));
            }else{
               pantalla1.setText("Syntax ERROR");
                for(int x=0;x<4;x++){
                  for(int y=0;y<5;y++){
                     botones[x][y].setEnabled(false);
                  }
               }
               botones[3][3].setEnabled(true);
               pantalla2.setText("");
            }
            break;
         case "Del":
            break;
         case "1":
            pantalla1.append("1");
            break; 
         case "2":
            pantalla1.append("2");
            break;
         case "3":
            pantalla1.append("3");
            break;
         case "+":
            pantalla1.append("+");
            break;
         case "-":
            pantalla1.append("-");
            break;
         case "4":
            pantalla1.append("4");
            break;
         case "5":
            pantalla1.append("5");
            break; 
         case "6":
            pantalla1.append("6");
            break;
         case "X":
            pantalla1.append("X");
            break;
         case "/":
            pantalla1.append("/");
            break;
         case "7":
            pantalla1.append("7");
            break;
         case "8":
            pantalla1.append("8");
            break;
         case "9":
            pantalla1.append("9");
            break; 
         case "AC":
            pantalla1.setText("");
            for(int x=0;x<4;x++){
               for(int y=0;y<5;y++){
                  botones[x][y].setEnabled(true);
               }
            }
            break;
         case "OFF":
            System.exit(0);
            break;
            
      }
   
    }
}

class Operation {
   public Operation(){ }

   public float operacion(String oper){
      int contC=0, tam,contP=0, contT=0;
      float a,b,c=0;
      String cad="";
      tam= oper.length();
      char s;
      float aux;
      char pila[] = new char[tam];
      float terminos[]= new float[tam];
      try{
         if(tam==0){
            return 0;
         
         }
         if(oper.charAt(0)=='-'){
               pila[0]='-';
               contP++;
               contC++;
            }else{
               if(oper.charAt(0)=='-'){
                  pila[0]='-';
               }
               pila[0]='+';
               contP++;
               
            }
         //método
         while(contC<tam){
            
            s = oper.charAt(contC);
            if(s =='+' ||s=='-'||s=='X' ||s=='/'){
               if(cad.length()>0){
                  terminos[contT]= Float.valueOf(cad);
                  contT++;
                  cad="";
               }
               if(s=='+'|| s=='-'){
                  pila[contP]= s;
                  contP++;
                  contC++;
               
               }else{    
                  if(s=='X'|| s=='/'){
                     pila[contP]= s;
                     contP++;
                     contC++;
                     boolean exit=true;
                     do{
                        int t=0;
                        s = oper.charAt(contC);
                        if(s=='+' ||s=='-' && t==0){
                           if(oper.charAt(contC-1)=='X' || oper.charAt(contC-1)=='/')
                              t++;
                           else
                              t=0;
                        }
                        if((s=='+' ||s=='-'||s=='X'|| s=='/')&& t==0){
                           exit=false;
                        }else{
                           cad+=s;
                           contC++;
                        }
                     
                     }while(exit==true&& contC<tam);
                   
                     aux= Float.valueOf(cad);
                     
                     if(pila[contP-1]=='X'){
                        a=terminos[contT-1]*aux;
                        contT--;
                        contP--;
                     }else{
                        a=terminos[contT-1]/aux;
                        contT--;
                        contP--;
                     }
                     terminos[contT]=a;
                     contT++; 
                     cad="";
                  }
               }
            }else{
               cad+=s; 
               contC++;
            }
          }
          if(cad.length()>0){
            terminos[contT]=Float.valueOf(cad);
            contT++;
          }
          int p=0;
          for(int x=0;x<contT;x++){
            b=terminos[x];
            if(pila[p]=='+'){
              c=c+b;
              terminos[x]=c;
               p++;
            }else{
               if(pila[p]=='-'){
                  c=c-b;
                terminos[x]=c;
                  p++;
               }
            }
          }  
          return c;
      
      }
      catch(ArithmeticException e){
        CalculadoraGra.pantalla1.setText("ERROR");
         return c;
      }
      
   }
   
   public boolean errorSintaxis(String cad){
      int tam= cad.length();
      char t,f;
      if(tam>0){
         f=cad.charAt(tam-1);
         t=cad.charAt(0);
         if(t=='X'|| t=='/'|| f=='X'||f=='/'||f=='+'||f=='-'|| f=='.'||f==' '){
            return false;
         }
         for(int x=1;x<tam;x++){
            t=cad.charAt(x);
            if(t=='X'|| t=='/'){
              
               f=cad.charAt(x-1);
               if(f=='X'|| f=='/'|| f=='-'|| f=='+'|| f=='.'){
                  return false;
               }
            
            }
            
            if(t=='.'){
               f=cad.charAt(x-1);
               if(f=='.'){
                  return false;
               }
            }
            if(t=='-'||t=='+'){
               f=cad.charAt(x-1);
               if(f=='-'|| f=='+'|| f=='.'){
                  return false;
               }
            }
         }
      }
      return true;
   
   }
  
}
