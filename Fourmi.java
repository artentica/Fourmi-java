import java.util.*;

/**
   *Cette classe permer de définir les fourmies, on leur donne une vie, une position en x et une piosition en y
   Une variable nous donne la charges de nourritures portés par la fourmi
   Et sur sourtout cette class permet de gérer les déplacements de la fourmis.
   
   *@author Riouallon vincent
   *@version 1
   */

public class Fourmi{

   private int x, y, charge, vie, width, height;
   private char[][] plateau;
   
 
   public Fourmi(int width, int height, char[][] plateau){
      Random rand = new Random();
      do{
         this.x = rand.nextInt(width);
         this.y = rand.nextInt(height);
      } while(Conflict());
      this.charge =0;
      this.vie = 60;
      this.width = width;
      this.height = height;
      this.plateau=plateau;
   }
   
   /** Cette méthode permet de déplacer ma fourmis dans mon tableau
   
      *@author Riouallon vincent
   */
   
   void Move(){
      Random rand = new Random();
      int val = rand.nextInt(8);
      int x=this.x;
      int y=this.y;
      
      switch (val){
         case 0: this.x--;                    // 0 1 2
                 this.y--;                    // 7 F 3
                 break;                       // 6 5 4
          
         case 1: this.y--;
                 break;
          
         case 2: this.x++;
                 this.y--;
                 break;
                           
         case 3: this.x++;
                 break;
                           
         case 4: this.x++;
                 this.y++;
                 break;
                           
         case 5: this.y++;
                 break;
                           
         case 6: this.x--;
                 this.y++;
                 break;
                           
         case 7: this.x--;
                 break;
                 
         default: break;
      }
      if (Conflict()){
         this.x=x;
         this.y=y;
      }
      Overrun();
      LoseLife();
      OnBoard();
   }
   
    /** Cette méthode permet de replacer les fourmis qui sont sortie de l'écran
       
      *@author Riouallon vincent
   */
   void Overrun(){
      if(this.x > this.width) this.x-=this.width;
      if(this.x < this.width) this.x+=this.width;
      if(this.y > this.height) this.y-=this.height;
      if(this.y < this.height) this.y+=this.height;
      
   }
   
   /** On détecte ici les conflits entre les fourmis
       
      *@author Riouallon vincent
   */
   boolean Conflict(){
    if (this.plateau[this.x][this.y]=='F')return false;
    else return true;
   }
   
   /** Il s'agit de la fonction qui permet aux fourmis de prendre la nurriture
       
      *@author Riouallon vincent
   */
   void TakeFood(){
    if (plateau[this.x][this.y]!='0' && this.charge=='0'){
      this.charge+=this.plateau[this.x][this.y];
      this.plateau[this.x][this.y]='0';
    }
   }
   
   /** La fonction permet d'entacer la nourriture
       
      *@author Riouallon vincent
   */
   void DropFood(){
    if (plateau[this.x][this.y]!='0' && this.charge!='0'){
     this.plateau[this.x][this.y]+=this.charge;
     this.charge=0;
     
    } 
   }
   
   /** La fonction décrémente la vie de la fourmi quand elle trasporte rien
       
      *@author Riouallon vincent
   */
   void LoseLife(){
      if(this.charge==0)this.vie--;
      if(this.vie==0) Death();
   }
   
   /** La fonction tu la fourmi en la remplacant par un 0
       
      *@author Riouallon vincent
   */
   void Death(){
      if (vie==0){
         this.plateau[this.x][this.y]='0';
      }
   }
   
   void OnBoard(){
      this.plateau[this.x][this.y]='F';
   }
}